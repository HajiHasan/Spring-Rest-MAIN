package az.spring.service;

import az.spring.rest.model.dto.EmployeeDto;
import az.spring.rest.model.response.EmployeeResponse;

public interface EmployeeService {
    EmployeeResponse getAllEmployees();

    EmployeeDto getEmployeeById(int id);

    EmployeeResponse getEmployeeByNameAndSurname(String name, String surname);
    void insert(EmployeeDto employeeDto);
    void update(EmployeeDto employeeDto,int id);

    void updateSome(EmployeeDto employeeDto, int id);
    void delete(int id);
}