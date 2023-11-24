package az.spring.rest.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class EmployeeDto {

    private int id;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;

    private double salary;
}
