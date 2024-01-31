package pl.szymanczyk.peoplemanagement.repository;

import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import pl.szymanczyk.peoplemanagement.model.Person;
import pl.szymanczyk.peoplemanagement.model.student.Student;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT DISTINCT p FROM Person p WHERE TYPE(p) = Student")
    Page<Person> findAllStudent(Pageable pageable);

    @Query("SELECT s FROM Student s WHERE"
            + " CONCAT(s.firstName, s.lastName, s.personalId, s.mail, s.academyName,s.studyYear,s.studyField,s.scholarship)"
            + "LIKE %?1%")
    Page<Person> findAllStudentsWithParam(String value, Pageable pageable);


    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Student> findWithLockingById(Long id);
}









//    @Query("SELECT s FROM Student s " +
//            "WHERE (LOWER(s.academyName) LIKE LOWER(CONCAT('%', COALESCE(:academyName, ''),'%'))) " +
//            "AND (:studyYear IS NULL OR s.studyYear = :studyYear) " +
//            "AND (LOWER(s.studyField) LIKE LOWER(CONCAT('%', COALESCE(:studyField, ''),'%'))) " +
//            "AND (:scholarship IS NULL OR s.scholarship <= :scholarship)")
//    Page<Person> findAllStudentsWithFilters(@Param("academyName") String academyName,
//                                            @Param("studyYear") Integer studyYear,
//                                            @Param("studyField") String studyField,
//                                            @Param("scholarship") BigDecimal scholarship,
//                                            Pageable pageable);


//    default Page<Person> findAllBy(String param, Pageable pageable) {
//        return findAllStudentsWithFilters(
//                "academyName",
//                "studyYear",
//                "studyField",
//                "scholarship",
//                pageable
//        );
//    }
