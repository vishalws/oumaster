package com.gati.mdm.oumaster.service.controller;

import org.springframework.http.ResponseEntity;

import com.gati.mdm.common.error.ErrorDetails;

public class BaseController {

    protected <T> ResponseEntity<T> toResponse(T body) {
        return ResponseEntity.ok(body);
    }

    protected <T extends ErrorDetails> ResponseEntity<T> errorToResponse(T error) {
        return ResponseEntity.status(404) // TODO Check this
                             .body(error);
    }

}