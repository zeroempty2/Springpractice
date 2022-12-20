package com.sparta.homework.responseMessageData;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Getter
@Component
public class DefaultRes {
    private int statusCode;
    private String message;
    private boolean success;

    public DefaultRes() {
        this.statusCode = StatusCode.BAD_REQUEST;
        this.message = null;
        this.success = false;
    }
    public ResponseEntity<DefaultRes> deletePostOk(){
        DefaultRes defaultRes = new DefaultRes();
        defaultRes.statusCode = StatusCode.OK;
        defaultRes.message = ResponseMessage.DELETE_SUCCESS;
        defaultRes.success = true;
        return new ResponseEntity<>(defaultRes,HttpStatus.OK);
    }
    public ResponseEntity<DefaultRes> signupOK(){
        DefaultRes defaultRes = new DefaultRes();
        defaultRes.statusCode = StatusCode.OK;
        defaultRes.message = ResponseMessage.CREATED_USER;
        defaultRes.success = true;
        return new ResponseEntity<>(defaultRes, HttpStatus.OK);
    }

    public ResponseEntity<DefaultRes> loginOK(){
        DefaultRes defaultRes = new DefaultRes();
        defaultRes.statusCode = StatusCode.OK;
        defaultRes.message = ResponseMessage.LOGIN_SUCCESS;
        defaultRes.success = true;
        return new ResponseEntity<>(defaultRes, HttpStatus.OK);
    }

    public ResponseEntity<DefaultRes> deleteOK(){
        DefaultRes defaultRes = new DefaultRes();
        defaultRes.statusCode = StatusCode.OK;
        defaultRes.message = ResponseMessage.DELETE_SUCCESS;
        defaultRes.success = true;
        return new ResponseEntity<>(defaultRes, HttpStatus.OK);
    }

}


