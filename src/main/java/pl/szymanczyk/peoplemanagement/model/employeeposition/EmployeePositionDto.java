package pl.szymanczyk.peoplemanagement.model.employeeposition;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EmployeePositionDto {

    private int id;

    @NotNull
    @Past(message = "Start date cannot be in the future")
    private LocalDate startDate;

    @NotNull
    @PastOrPresent(message = "End date cannot be in the future")
    private LocalDate endDate;

    @NotNull
    @Pattern(regexp = "^[A-ZŁŻ][a-zA-Ząćęłńóśźż\\s]+", message = "Invalid position format. The name should start with an uppercase letter followed by letters and spaces.")
    private String position;

    @NotNull
    @Min(value = 1, message = "Value cannot be less than 1!")
    private BigDecimal salary;

}
