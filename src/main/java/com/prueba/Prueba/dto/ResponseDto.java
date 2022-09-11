package com.prueba.Prueba.dto;

import lombok.Data;

@Data
public class ResponseDto {
    private boolean correctProcess;
    private String error;
    private String message;
    private Object data;
}
