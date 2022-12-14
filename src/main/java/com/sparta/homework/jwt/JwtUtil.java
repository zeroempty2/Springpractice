package com.sparta.homework.jwt;

import com.sparta.homework.entity.UserRoleEnum;
import com.sparta.homework.exception.exceptions.*;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

import static com.sparta.homework.entity.UserRoleEnum.ADMIN;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtil {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String AUTHORIZATION_KEY = "auth";
    private static final String BEARER_PREFIX = "Bearer ";
    private static final long TOKEN_TIME = 60 * 60 * 1000L;

    @Value("${jwt.secret.key}")
    private String secretKey;
    private Key key;
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    @PostConstruct
    public void init() {
        byte[] bytes = Base64.getDecoder().decode(secretKey);
        key = Keys.hmacShaKeyFor(bytes);
    }

    // header 토큰을 가져오기
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    // 토큰 생성
    public String createToken(String username, UserRoleEnum role) {
        Date date = new Date();

        return BEARER_PREFIX +
                Jwts.builder()
                        .setSubject(username)
                        .claim(AUTHORIZATION_KEY, role)
                        .setExpiration(new Date(date.getTime() + TOKEN_TIME))
                        .setIssuedAt(date)
                        .signWith(key, signatureAlgorithm)
                        .compact();
    }

    // 토큰 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException | SignatureException e) {
            throw new InvalidJwtSignatureException();
        } catch (ExpiredJwtException e) {
            throw new ExpiredJwtTokenException();
        } catch (UnsupportedJwtException e) {
            throw new UnsupportedJwtTokenException();
        } catch (IllegalArgumentException e) {
            throw new JwtClaimsIsEmptyException();
        }
    }

    // 토큰에서 사용자 정보 가져오기
    public Claims getUserInfoFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }
    public String isValidToken(String token) {
        Claims claims;
        if (token != null) {
            if (validateToken(token)) {
                claims = getUserInfoFromToken(token);
                return claims.getSubject();
            } else {
                throw new InvalidTokenException();
            }
        }
        throw new NotLoginException();
    }
    public boolean isAdminToken(String token) {
        Claims claims;
        if (token != null) {
            if (validateToken(token)) {
                claims = getUserInfoFromToken(token);
                return ((claims.get("auth").toString()).equals(ADMIN.toString()));
            } else {
                throw new InvalidTokenException();
            }
        }
        throw new NotLoginException();
    }
}