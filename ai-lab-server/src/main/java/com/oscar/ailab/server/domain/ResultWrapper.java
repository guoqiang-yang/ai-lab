package com.oscar.ailab.server.domain;

import com.oscar.ailab.server.enums.ResultWrapperCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ResultWrapper<T> {
    @Builder.Default
    private Integer code = ResultWrapperCodeEnum.SUCCESS.getCode();
    private String message;
    private T data;

    public Boolean isSuccess() {
        return ResultWrapperCodeEnum.SUCCESS.getCode().equals(code);
    }

    public static <T> ResultWrapper<T> success(T data) {
        return ResultWrapper.<T>builder()
                .code(ResultWrapperCodeEnum.SUCCESS.getCode()).message("成功").data(data)
                .build();
    }

    public static <T> ResultWrapper<T> success() {
        return success(null);
    }

    public static <T> ResultWrapper<T> fail(Integer code, String message) {
        return ResultWrapper.<T>builder()
                .code(code).message(message)
                .build();
    }

    public static <T> ResultWrapper<T> fail(ResultWrapperCodeEnum resultWrapperCodeEnum, String message) {
        return fail(resultWrapperCodeEnum.getCode(), message);
    }

    public static <T> ResultWrapper<T> fail(ResultWrapperCodeEnum resultWrapperCodeEnum) {
        return fail(resultWrapperCodeEnum.getCode(), resultWrapperCodeEnum.getMessage());
    }

    public static <T> ResultWrapper<T> fail(String message) {
        return fail(ResultWrapperCodeEnum.FAIL.getCode(), message);
    }
}
