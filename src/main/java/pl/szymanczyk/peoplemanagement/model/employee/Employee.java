package pl.szymanczyk.peoplemanagement.model.employee;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.szymanczyk.peoplemanagement.model.Person;
import pl.szymanczyk.peoplemanagement.model.Role;
import pl.szymanczyk.peoplemanagement.model.employeeposition.EmployeePosition;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
public class Employee extends Person  {

    private LocalDate startDateOfEmployment;
    private String position;
    private BigDecimal salary;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private Set<EmployeePosition> employeePositions = new HashSet<>();
}

