package com.chandraedu.api.transactions.exception.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class ApiError {

    private final int code;
    private final String type;
    private final String message;
    private final LocalDateTime timestamp;
}
