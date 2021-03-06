package com.gang.api.common;

/**
 * Created by iljun on 2017-03-11.
 */

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDto {
    private Status status;
    private String message;
    private Object result;

    public static ResponseDto ofSuccess(String message,Object result) {
        return ResponseDto.builder()
                .status(Status.SUCCESS)
                .message(message)
                .result(result)
                .build();
    }

    public static ResponseDto ofFail(String message) {
        return ResponseDto.builder()
                .status(Status.FAIL)
                .message(message)
                .result(null)
                .build();
    }

    public static ResponseDto ofEmpty(){
        return ResponseDto.builder()
                .status(Status.FAIL)
                .message(null)
                .build();
    }
}
