package pl.szymanczyk.peoplemanagement.mapper;

import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.szymanczyk.peoplemanagement.model.student.Student;
import pl.szymanczyk.peoplemanagement.model.student.StudentDto;

import java.util.Map;

@Mapper
public interface StudentMapper {

    Student mapToEntity(Map<String, String> args);

    StudentDto mapToDto(Map<String, String> args);

    Student fromDto(StudentDto studentDto);
}