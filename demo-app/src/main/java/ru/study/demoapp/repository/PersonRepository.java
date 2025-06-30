package ru.study.demoapp.repository;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.study.demoapp.model.Person;

import java.util.List;
import java.util.Optional;

@Repository
public interface  PersonRepository extends JpaRepository<Person, Integer> {

    @Query("SELECT p FROM Person p WHERE p.age > 18 and p.city != 'Москва'")
    List<Person> findByAgeAndCity();

    @Query("SELECT p.city, COUNT(p) FROM Person p GROUP BY p.city")
    List<Object[]> countByCity();

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT p FROM Person p WHERE p.id = :id")
    Optional<Person> findByIdForUpdate(@Param("id") Integer id);
}
