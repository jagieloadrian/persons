package pl.szymanczyk.peoplemanagement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.szymanczyk.peoplemanagement.model.employee.Employee;
import pl.szymanczyk.peoplemanagement.model.employeeposition.EmployeePosition;

import java.time.LocalDate;

public interface EmployeePositionRepository extends JpaRepository<EmployeePosition, Long> {


    @Query("SELECT ep FROM EmployeePosition ep " +
            "JOIN ep.employee e " +
            "WHERE CONCAT(e.firstName, e.lastName, e.weight, e.height, e.personalId, " +
            "ep.startDate, ep.endDate, ep.position, ep.salary) " +
            "LIKE %:value%")
    Page<EmployeePosition> findAllByValue(String value,Pageable pageable);



    boolean existsByEmployeeAndEndDateAfterAndStartDateBefore(
            Employee employee,
            LocalDate endDate,
            LocalDate startDate
    );
}

