package az.spring.rest.model.response;

import az.spring.rest.model.dto.EmployeeDto;
import lombok.*;

import java.util.List;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {

    private List<EmployeeDto> employees;
}
