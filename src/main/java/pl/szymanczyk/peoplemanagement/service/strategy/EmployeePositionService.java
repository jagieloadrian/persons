package pl.szymanczyk.peoplemanagement.service.strategy;


import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.szymanczyk.peoplemanagement.model.employee.Employee;
import pl.szymanczyk.peoplemanagement.model.employeeposition.EmployeePosition;
import pl.szymanczyk.peoplemanagement.model.employeeposition.EmployeePositionDto;
import pl.szymanczyk.peoplemanagement.repository.EmployeePositionRepository;
import pl.szymanczyk.peoplemanagement.repository.EmployeeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeePositionService {

    private final EmployeePositionRepository employeePositionRepository;

    public EmployeePosition createEmployeePosition(EmployeePosition employeePosition) {
        return employeePositionRepository.save(employeePosition);
    }

    public List<EmployeePosition> getAllEmployeePositions() {
        return employeePositionRepository.findAll();
    }

    public EmployeePosition getEmployeePositionById(Long id) {
        return employeePositionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee Position not found with ID: " + id));
    }



    public void deleteEmployeePosition(Long id) {
        if (employeePositionRepository.existsById(id)) {
            employeePositionRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Employee Position not found with ID: " + id);
        }
    }
}