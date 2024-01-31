package pl.szymanczyk.peoplemanagement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.szymanczyk.peoplemanagement.model.Person;
import pl.szymanczyk.peoplemanagement.model.PersonDto;
import pl.szymanczyk.peoplemanagement.model.PersonRequestDto;

@Mapper
public interface PersonMapper {

    @Mapping(source = "args.firstName", target = "firstName")
    @Mapping(source = "args.lastName", target = "lastName")
    @Mapping(source = "args.mail", target = "mail")
    @Mapping(source = "args.personalId", target = "personalId")
    @Mapping(source = "args.height", target = "height")
    @Mapping(source = "args.weight", target = "weight")
    @Mapping(source = "args.password", target = "password")
    @Mapping(target = "blocked", ignore = true)
    PersonDto mapToPersonDto(PersonRequestDto personRequestDto);


    PersonDto mapToPersonDto(Person person);
}

