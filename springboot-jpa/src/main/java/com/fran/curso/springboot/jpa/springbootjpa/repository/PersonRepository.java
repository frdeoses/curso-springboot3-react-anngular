package com.fran.curso.springboot.jpa.springbootjpa.repository;

import com.fran.curso.springboot.jpa.springbootjpa.dto.PersonDto;
import com.fran.curso.springboot.jpa.springbootjpa.entities.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Long> {
    @Query("select p from Person  p where p.id in ?1")
    List<Person> getPersonByIds(List<Long> ids);

    @Query("select p.name, length(p.name) from Person  p where length(p.name) = (select min(length(p.name)) from Person p)")
    List<Object[]> getShorterName();

    @Query("select p from Person  p where p.id = (select max (p.id) from Person p)")
    Optional<Person> getLastRegistration();

    @Query("select  min (p.id), max (p.id), sum(p.id), avg (length(p.name) ), count(p.id) from Person  p")
    Object getResumeAggregationFunction();

    @Query("select  max (length(p.name)) from Person  p")
    Integer getMaxLengthName();

    @Query("select  min (length(p.name)) from Person  p")
    Integer getMinLengthName();

    @Query("select p.name, length(p.name) from Person  p")
    List<Object[]> getPersonNameLength();

    @Query("select min (p.id) from Person  p   ")
    Long getMinId();

    @Query("select max (p.id) from Person  p   ")
    Long getMaxId();

    @Query("select count (p) from Person  p   ")
    Long getTotalPerson();

    List<Person> findByOrderByNameDesc();

    @Query("select p from Person  p  order by p.name desc , p.lastname asc ")
    List<Person> getAllOrdered();

    List<Person> findByIdBetweenOrderByIdDesc(Long id1, Long id2);

    List<Person> findByIdBetween(Long id1, Long id2);

    List<Person> findByNameBetween(String name1, String name2);

    @Query("select p from Person  p where p.name between ?1 and ?2 order by p.name")
    List<Person> findAllBetweenName(String name1, String name2);

    @Query("select p from Person  p where p.id between ?1 and ?2 order by p.name desc, p.lastname asc ")
    List<Person> findAllBetweenId(Long id1, Long id2);

    @Query("select p.id, upper(p.name) , lower(p.lastname) , upper(p.programmingLanguage)  from Person p ")
    List<Object[]> findAllPersonDataListCase();

    @Query("select upper(p.name || ' ' || p.lastname)  from Person  p ")
    List<String> findAllFullNameConcatUpper();

    @Query("select lower(p.name || ' ' || p.lastname) from Person  p ")
    List<String> findAllFullNameConcatLower();

    //    @Query("select concat(p.name,' ',p.lastname) as fullname from Person  p ")
    @Query("select p.name || ' ' || p.lastname as fullname from Person  p ")
    List<String> findAllFullNameConcat();

    @Query("select p.name from Person p")
    List<String> findAllNames();

    @Query("select distinct (p.name) from Person p")
    List<String> findAllNamesDistinct();

    @Query("select count (distinct (p.name)) from Person p")
    Long findAllNamesDistinctCount();

    @Query("select new com.fran.curso.springboot.jpa.springbootjpa.dto.PersonDto(p.name,p.lastname) from Person p")
    List<PersonDto> findAllPersonDto();

    @Query("select new Person(p.name,p.lastname) from Person p")
    List<Person> findAllObjectPersonPersonalizer();

    @Query("select p.name from Person  p where p.id = ?1")
    String getNameById(Long id);

    @Query("select p.id from Person  p where p.id = ?1")
    Long getIdById(Long id);

    @Query("select concat(p.name,' ',p.lastname) as fullname from Person  p where p.id = ?1")
    String getFullNameById(Long id);

    @Query("select p from Person p where p.id = ?1")
    Optional<Person> findOne(Long id);

    @Query("select p from Person p where p.name = ?1")
    Optional<Person> findOneName(String name);

    @Query("select p from Person p where p.name like %?1%")
    Optional<Person> findLikeName(String name);

    Optional<Person> findByNameContaining(String name);

    List<Person> findByProgrammingLanguage(String programmingLanguage);

    @Query("select p from Person p where p.programmingLanguage=?1 and p.name=?2")
    List<Person> buscarByProgrammingLanguage(String programmingLanguage, String name);

    List<Person> findByProgrammingLanguageAndName(String programmingLanguage, String name);

    @Query("select p.id, p.name, p.lastname, p.programmingLanguage from Person p where p.id=?1")
    Object obtenerPersonDataFullById(Long id);

    @Query("select p,  p.programmingLanguage from Person p ")
    List<Object[]> findAllMixPerson();

    @Query("select p.id, p.name, p.lastname, p.programmingLanguage from Person p ")
    List<Object[]> obtenerPersonDataFullList();

    @Query("select p.name, p.programmingLanguage from Person p ")
    List<Object[]> obtenerPersonData();

    @Query("select p.name, p.programmingLanguage from Person p where  p.name=?1")
    List<Object[]> obtenerPersonDataByName(String name);

    @Query("select p.name, p.programmingLanguage from Person p where  p.programmingLanguage=?1")
    List<Object[]> obtenerPersonDataByProgrammingLanguage(String programmingLanguage);

    @Query("select p.name, p.programmingLanguage from Person p where p.programmingLanguage=?1 and p.name=?2")
    List<Object[]> obtenerPersonData(String programmingLanguage, String name);

}
