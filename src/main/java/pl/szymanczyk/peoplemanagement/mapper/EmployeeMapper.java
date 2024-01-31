package pl.szymanczyk.peoplemanagement.mapper;

import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.szymanczyk.peoplemanagement.model.Person;
import pl.szymanczyk.peoplemanagement.model.PersonDto;
import pl.szymanczyk.peoplemanagement.model.PersonRequestDto;
import pl.szymanczyk.peoplemanagement.model.employee.Employee;
import pl.szymanczyk.peoplemanagement.model.employee.EmployeeDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Mapper
public interface EmployeeMapper {


    EmployeeDto mapToDto(Map<String, String> args);


    @Mappings({
            @Mapping(target = "firstName", source = "args.firstName"),
            @Mapping(target = "lastName", source = "args.lastName"),
            @Mapping(target = "personalId", source = "args.personalId"),
            @Mapping(target = "height", source = "args.height"),
            @Mapping(target = "weight", source = "args.weight"),
            @Mapping(target = "mail", source = "args.mail"),
            @Mapping(target = "employeePositions", ignore = true),
            @Mapping(target = "password", source = "args.password")
    })
    Employee mapToEntity(Map<String,String> args);

    Employee fromDto(@Valid EmployeeDto employeeDto);

    EmployeeDto toDto(Employee employee);

}
