package com.example.xenon.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class ResponseUtils {

    public static ResponseEntity<?> notFound(String message) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", message));
    }


}
