package az.spring.exception;

import az.spring.exception.enums.EnumCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Getter
public class ResourceNotFoundException extends RuntimeException{

    private final int code;

    private final String message;

    @Serial
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(EnumCode enumCode) {
        this.code=enumCode.getId();
        this.message=enumCode.getMessage();

    }
}
