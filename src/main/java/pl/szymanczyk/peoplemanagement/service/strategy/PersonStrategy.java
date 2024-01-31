package pl.szymanczyk.peoplemanagement.service.strategy;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.szymanczyk.peoplemanagement.model.Person;
import pl.szymanczyk.peoplemanagement.model.PersonRequestDto;

import java.util.Map;


public interface PersonStrategy {

    Page<Person> findAllByValue (String param, Pageable pageable);

    Person save(PersonRequestDto personRequestDto);

    Person update(Long personId, PersonRequestDto updatedPersonData);
}