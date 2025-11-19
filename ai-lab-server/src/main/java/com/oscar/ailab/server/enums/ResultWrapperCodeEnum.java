package com.oscar.ailab.server.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ResultWrapperCodeEnum {
    SUCCESS(200, "成功", "OK"),

    FAIL(500, "失败", "DEFAULT ERROR"),
    PARAMS_ERROR(400, "参数错误", "Params Error"),
    UNAUTHORIZED(401, "用户身份未认证", "Unauthorized"),
    FORBIDDEN(403, "请求拒绝", "Forbidden"),
    NOT_FOUND(404, "请求不存在", "Not Found"),

    SYSTEM_ERROR(500, "服务异常", "Internal Server Error"),
    BAD_GATEWAY(502, "网关异常", "	Bad Gateway"),
    GATEWAY_TIMEOUT(504, "网关超时", "Gateway Time-out"),


    ;

    private Integer code;
    private String message;
    private String message2;
}
