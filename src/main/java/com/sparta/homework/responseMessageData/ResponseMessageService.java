package com.sparta.homework.responseMessageData;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ResponseMessageService {
    public ResponseEntity<DefaultRes> deleteOk(){
        DefaultRes defaultRes = DefaultRes.valueOf(StatusCode.OK,ResponseMessage.DELETE_SUCCESS,true);
        return new ResponseEntity<>(defaultRes, HttpStatus.OK);
    }

    public ResponseEntity<DefaultRes> signupOk(){
        DefaultRes defaultRes = DefaultRes.valueOf(StatusCode.OK,ResponseMessage.CREATED_USER,true);
        return new ResponseEntity<>(defaultRes, HttpStatus.OK);
    }
    public ResponseEntity<DefaultRes> loginOk(){
        DefaultRes defaultRes = DefaultRes.valueOf(StatusCode.OK,ResponseMessage.LOGIN_SUCCESS,true);
        return new ResponseEntity<>(defaultRes, HttpStatus.OK);
    }
}
