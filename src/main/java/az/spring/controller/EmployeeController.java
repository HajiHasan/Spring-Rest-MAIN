package az.spring.controller;

import az.spring.rest.model.dto.EmployeeDto;
import az.spring.rest.model.response.EmployeeResponse;
import az.spring.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;



    @GetMapping("/allemployees")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public EmployeeResponse getAllEmployees(){
       return employeeService.getAllEmployees();
    }
    @GetMapping("/employee/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public EmployeeDto getEmployeeById(@PathVariable int id){
        return employeeService.getEmployeeById(id);
    }
    @GetMapping("/search")
    public EmployeeResponse getEmployeeByNameAndSurname(
            @RequestParam("name") String name,
            @RequestParam("surname") String surname){
        return employeeService.getEmployeeByNameAndSurname(name,surname);
    }
    @PostMapping("/employee")
    @ResponseStatus(HttpStatus.CREATED)
    public void insert(@Valid @RequestBody EmployeeDto employeeDto){
        employeeService.insert(employeeDto);
    }
    @PutMapping("/employee/{id}")
    public void update(@RequestBody EmployeeDto employeeDto,@PathVariable int id){
        employeeService.update(employeeDto, id);
    }
    @PatchMapping("/employee/{id}")
    public void updateSome(@RequestBody EmployeeDto employeeDto,@PathVariable int id){
        employeeService.updateSome(employeeDto, id);
    }
    @DeleteMapping("/employee/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        employeeService.delete(id);
    }


}
