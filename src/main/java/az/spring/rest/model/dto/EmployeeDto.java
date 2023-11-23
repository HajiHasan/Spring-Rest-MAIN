package az.spring.rest.model.dto;

import lombok.Data;

@Data
public class EmployeeDto {

    private int id;

    private String name;

    private String surname;

    private double salary;
}
