package pl.szymanczyk.peoplemanagement.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class PersonRequestDto {

    @NotNull(message = "Type is required")
    private String type;

    @NotNull(message = "Map is required")
    private Map<String, String> args;

}