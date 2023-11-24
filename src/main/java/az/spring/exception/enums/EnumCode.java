package az.spring.exception.enums;

import lombok.Getter;

@Getter
public enum EnumCode {
    EMPLOYEE_NOT_FOUND(100, "employee not found with given id"),
    VALIDATION_ERROR(200, "id type must be only numbers"),
    WRONG_TYPE_INSERT(300,"columns cannot be blank"),
    UNKNOWN_ERROR(404,"Unknown error has appeared");

    private final String message;

    private final int id;

    EnumCode(int id, String message) {
        this.message = message;
        this.id = id;
    }

}
