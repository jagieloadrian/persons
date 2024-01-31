package pl.szymanczyk.peoplemanagement.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import pl.szymanczyk.peoplemanagement.mapper.PersonMapper;
import pl.szymanczyk.peoplemanagement.model.Person;
import pl.szymanczyk.peoplemanagement.model.PersonDto;
import pl.szymanczyk.peoplemanagement.model.PersonRequestDto;
import pl.szymanczyk.peoplemanagement.repository.PersonRepository;
import pl.szymanczyk.peoplemanagement.service.strategy.PersonStrategy;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonService {

    private final PersonMapper personMapper;
    private final PersonRepository personRepository;
    private final Map<String, PersonStrategy> strategies;


    public Page<Person> findAllByValue(String type, Integer minWeight,
                                       Integer maxWeight,  Integer minHeight, Integer maxHeight,String value, Pageable pageable) {
        return Optional.ofNullable(type)
                .map(String::toUpperCase)
                .filter(strategies::containsKey)
                .map(strategies::get)
                .map(strategy -> strategy.findAllByValue(value, pageable))
                .orElseGet(() -> {
                    if (value != null) {
                        log.error("Strategy not found for type: {}", type);
                        return personRepository.findAllByValue(value, pageable);
                    }
                    return personRepository.findAll(pageable);
                });
    }

    public Page<Person> findAll(Pageable pageable) {
        return personRepository.findAll(pageable);
    }

    @Transactional
    public PersonDto save(PersonRequestDto personRequestDto) {
        String type = personRequestDto.getType().toUpperCase();
        if (!strategies.containsKey(type)) {
            throw new EntityNotFoundException("Invalid type. Allowed values are: " + String.join(", ", strategies.keySet()));
        }
        PersonStrategy strategy = strategies.get(type);
        Person savedPerson = strategy.save(personRequestDto);
        return personMapper.mapToPersonDto(savedPerson);
    }

    @Transactional
    @Modifying
    public PersonDto update(Long id, PersonRequestDto personRequestDto) {
        PersonStrategy strategy = strategies.get(personRequestDto.getType().toUpperCase());
        Person updatePerson = strategy.update(id, personRequestDto);
        return personMapper.mapToPersonDto(updatePerson);
    }
}


