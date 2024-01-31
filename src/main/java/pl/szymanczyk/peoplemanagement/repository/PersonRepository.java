package pl.szymanczyk.peoplemanagement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.szymanczyk.peoplemanagement.model.Person;

import java.util.Map;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("SELECT p FROM Person p WHERE"
    + " CONCAT(p.firstName, p.lastName,p.weight,p.height,p.personalId,p.mail)"
    + "LIKE %?1%")
    Page<Person> findAllByValue(String value, Pageable pageable);


    Optional<Person> findByBlockedAndMail(Boolean blocked, String mail);
}








//@Query("SELECT p FROM Person p " +
//            "WHERE (:minWeight IS NULL OR p.weight >= :minWeight) " +
//            "AND (:maxWeight IS NULL OR p.weight <= :maxWeight) " +
//            "AND (:minHeight IS NULL OR p.height >= :minHeight) " +
//            "AND (:maxHeight IS NULL OR p.height <= :maxHeight) " +
//            "AND (LOWER(p.firstName) LIKE LOWER(CONCAT('%', COALESCE(:firstName, ''),'%'))) " +
//            "AND (LOWER(p.lastName) LIKE LOWER(CONCAT('%', COALESCE(:lastName, ''),'%'))) " +
//            "AND (LOWER(p.personalId) LIKE LOWER(CONCAT('%', COALESCE(:personalId, ''),'%'))) " +
//            "AND (LOWER(p.mail) LIKE LOWER(CONCAT('%', COALESCE(:mail, ''),'%'))) ")
//    Page<Person> findAllWithFilters(@Param("minWeight") Integer minWeight,
//                                    @Param("maxWeight") Integer maxWeight,
//                                    @Param("minHeight") Integer minHeight,
//                                    @Param("maxHeight") Integer maxHeight,
//                                    @Param("firstName") String firstName,
//                                    @Param("lastName") String lastName,
//                                    @Param("personalId") String personalId,
//                                    @Param("mail") String mail,
//                                    Pageable pageable);