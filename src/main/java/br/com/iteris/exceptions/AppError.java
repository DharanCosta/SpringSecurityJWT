package br.com.iteris.exceptions;


import org.springframework.http.HttpStatus;

public enum AppError {

    INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, 401, "invalid.credential"),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, 401, "invalid.token"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND,404,"user.not.found"),
    INVALID_ROLE_ID(HttpStatus.BAD_REQUEST, 400, "id.not.found"),
    INVALID_FOR_NORMAL_ROLE(HttpStatus.FORBIDDEN,401,"invalid.option.for.normal.user"),
    INVALID_FOR_ADM_ROLE(HttpStatus.FORBIDDEN,401,"invalid.option.for.adm");

    private HttpStatus status;
    private Integer code;
    private String message;

    AppError(HttpStatus status, Integer code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
