package pl.szymanczyk.peoplemanagement.controller;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.szymanczyk.peoplemanagement.mapper.PersonMapper;
import pl.szymanczyk.peoplemanagement.model.PersonDto;
import pl.szymanczyk.peoplemanagement.model.PersonRequestDto;
import pl.szymanczyk.peoplemanagement.model.employee.EmployeeDto;
import pl.szymanczyk.peoplemanagement.model.employeeposition.EmployeePosition;
import pl.szymanczyk.peoplemanagement.service.PersonService;
import pl.szymanczyk.peoplemanagement.service.strategy.EmployeePositionService;
import pl.szymanczyk.peoplemanagement.service.strategy.impl.EmployeeService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/people")
@RequiredArgsConstructor
@Slf4j
public class PersonController {

    private final PersonService personService;
    private final PersonMapper personMapper;
    private final EmployeeService employeeService;
    private final EmployeePositionService employeePositionService;

    @GetMapping
    public ResponseEntity<Page<PersonDto>> findAll(
            @RequestParam(required = false) String type,
            @Param("value") String value,
            @Param("minHeight") Integer minHeight,
            @RequestParam(required = false) Integer minWeight,
            @RequestParam(required = false)Integer maxHeight,
            @RequestParam(required = false) Integer maxWeight,
            Pageable pageable) {

        return new ResponseEntity<>(personService.findAllByValue
                (type, minWeight, minHeight, maxHeight, maxWeight,value, pageable).map(personMapper::mapToPersonDto), HttpStatus.OK);
    }

    @PostMapping("/{employeeId}/positions/{positionId}")
//    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<EmployeeDto> addPositionToEmployee(
            @PathVariable Long employeeId,
            @PathVariable Long positionId) {
        EmployeeDto updatedEmployee = employeeService.addPositionToEmployee(employeeId, positionId);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.CREATED);
    }

    @PostMapping("/create-employee-position")
    public ResponseEntity<EmployeePosition> createEmployeePosition(@RequestBody EmployeePosition employeePosition) {
        EmployeePosition createdEmployeePosition = employeePositionService.createEmployeePosition(employeePosition);
        return new ResponseEntity<>(createdEmployeePosition, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid PersonRequestDto personRequestDto) {
        log.info("Received request with payload: " +
                        "firstName={}, lastName={}, personalId={}, height={}, weight={}, mail={}, password={}",
                personRequestDto.getArgs().get("firstName"),
                personRequestDto.getArgs().get("lastName"),
                personRequestDto.getArgs().get("personalId"),
                personRequestDto.getArgs().get("height"),
                personRequestDto.getArgs().get("weight"),
                personRequestDto.getArgs().get("mail"),
                personRequestDto.getArgs().get("password"));
        try {
            PersonDto savedPerson = personService.save(personRequestDto);
            return new ResponseEntity<>(savedPerson, HttpStatus.CREATED);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            List<String> errorMessages = violations.stream()
                    .map(violation -> "Validation error: " + violation.getMessage())
                    .collect(Collectors.toList());
            return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{personId}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PersonDto> updatePerson(
            @PathVariable("personId") Long personId,
            @RequestBody @Valid PersonRequestDto personRequestDto) {
        PersonDto updatedPerson = personService.update(personId, personRequestDto);
        return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
    }
}