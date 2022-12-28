package com.sparta.homework.responseMessageData;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ResponseMessageService {
    public ResponseEntity<DefaultRes> deleteOk(){
        DefaultRes defaultRes = DefaultRes.valueOf(ResponseMessages.DELETE_SUCCESS);
        return new ResponseEntity<>(defaultRes, HttpStatus.OK);
    }

    public ResponseEntity<DefaultRes> signupOk(){
        DefaultRes defaultRes = DefaultRes.valueOf(ResponseMessages.CREATED_USER);
        return new ResponseEntity<>(defaultRes, HttpStatus.OK);
    }
    public ResponseEntity<DefaultRes> loginOk(){
        DefaultRes defaultRes = DefaultRes.valueOf(ResponseMessages.LOGIN_SUCCESS);
        return new ResponseEntity<>(defaultRes, HttpStatus.OK);
    }
}
