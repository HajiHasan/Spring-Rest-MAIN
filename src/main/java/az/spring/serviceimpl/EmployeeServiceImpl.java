package az.spring.serviceimpl;

import az.spring.model.Employee;
import az.spring.repository.EmployeeRepository;
import az.spring.rest.model.dto.EmployeeDto;
import az.spring.rest.model.response.EmployeeResponse;
import az.spring.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    @Override
    public EmployeeResponse getAllEmployees() {
        List<EmployeeDto> employeesDtoList = employeeRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .toList();
        return getEmployeeResponse(employeesDtoList);
    }

    @Override
    public EmployeeDto getEmployeeById(int id) {

         return employeeRepository.findById(id)
                 .map(this::convertToDto)
                  .orElseThrow(()->new RuntimeException("id not found"));

    }

    @Override
    public EmployeeResponse getEmployeeByNameAndSurname(String name, String surname) {
       List<EmployeeDto> employeeDtoList =  employeeRepository.findByNameAndSurname(name, surname)
               .stream()
               .map(this::convertToDto)
               .collect(Collectors.toList());
       return getEmployeeResponse(employeeDtoList);

    }

    @Override
    public void insert(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDto, employee);
        employeeRepository.save(employee);

    }

    @Override
    public void update(EmployeeDto employeeDto, int id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()->new RuntimeException("id not found"));
        employee.setName(employeeDto.getName());
        employee.setSurname(employeeDto.getSurname());
        employee.setSalary(employeeDto.getSalary());
        employeeRepository.save(employee);
    }

    @Override
    public void updateSome(EmployeeDto employeeDto, int id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()->new RuntimeException("id not found"));
        if(employeeDto.getName()!=null)
        employee.setName(employeeDto.getName());

        if(employeeDto.getSurname()!=null)
            employee.setSurname(employeeDto.getSurname());

        if(employeeDto.getSalary()>0)
            employee.setSalary(employeeDto.getSalary());
            employeeRepository.save(employee);

    }

    @Override
    public void delete(int id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()->new RuntimeException("id not found"));
        employeeRepository.deleteById(id);
    }

    private EmployeeDto convertToDto(Employee employee){
        EmployeeDto employeeDto = new EmployeeDto();
        BeanUtils.copyProperties(employee,employeeDto);
        return employeeDto;
    }
    private EmployeeResponse getEmployeeResponse(List<EmployeeDto> employeeDtoList){
        return EmployeeResponse.builder()
                .employees(employeeDtoList)
                .build();
    }
}
