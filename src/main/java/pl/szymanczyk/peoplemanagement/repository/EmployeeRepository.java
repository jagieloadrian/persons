package pl.szymanczyk.peoplemanagement.repository;

import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.szymanczyk.peoplemanagement.model.Person;
import pl.szymanczyk.peoplemanagement.model.employee.Employee;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    @Query("SELECT e FROM Employee e " +
            "WHERE CONCAT(e.firstName, e.lastName, e.weight, e.height, e.personalId, " +
            "e.startDateOfEmployment, e.position, e.salary) " +
            "LIKE %?1%")
    Page<Person> findAllEmployeesWithParam(String value, Pageable pageable);


    @Query("SELECT DISTINCT p FROM Person p WHERE TYPE(p) = Employee")
    Page<Person> findAllEmployee(Pageable pageable);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Employee> findWithLockingById(long id);
}
