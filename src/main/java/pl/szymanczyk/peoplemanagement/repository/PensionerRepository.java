package pl.szymanczyk.peoplemanagement.repository;

import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.szymanczyk.peoplemanagement.model.Person;
import pl.szymanczyk.peoplemanagement.model.pensioner.Pensioner;

import java.util.Optional;

    public interface PensionerRepository extends JpaRepository<Pensioner, Long> {

        @Query("SELECT DISTINCT p FROM Person p WHERE TYPE(p) = Pensioner")
        Page<Person> findAllPensioner(Pageable pageable);


        @Query("SELECT p FROM Pensioner p WHERE"
                + " CONCAT(p.firstName, p.lastName,p.weight,p.height,p.personalId,p.mail, p.employmentYears,p.pension)"
                + "LIKE %?1%")
        Page<Person> findAllByValue(String param, Pageable pageable);


        @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Pensioner> findWithLockingById(Long id);
}