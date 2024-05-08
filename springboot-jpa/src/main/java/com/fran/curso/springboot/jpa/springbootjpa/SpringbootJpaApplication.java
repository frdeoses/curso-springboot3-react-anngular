package com.fran.curso.springboot.jpa.springbootjpa;

import com.fran.curso.springboot.jpa.springbootjpa.dto.PersonDto;
import com.fran.curso.springboot.jpa.springbootjpa.entities.Person;
import com.fran.curso.springboot.jpa.springbootjpa.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner {

    @Autowired
    private PersonRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        update();
    }

    @Transactional(readOnly = true)
    public void whereIn() {
        System.out.println("================== Consulta where in ==================");
        List<Person> people = repository.getPersonByIds(List.of(1L,2L,4L));
        people.forEach(System.out::println);
    }
    @Transactional(readOnly = true)
    public void subQueries() {
        System.out.println("================== Consulta por el nombre mas corto y su largo ==================");
        List<Object[]> objects = repository.getShorterName();
        objects.forEach(objects1 -> {
            String name = (String) objects1[0];
            Integer length = (Integer) objects1[1];

            System.out.println("name: " + name + ", length: " + length);
        });

        System.out.println("================== Consulta para obtener el ultimo registro de persona ==================");
        Optional<Person> optionalPerson = repository.getLastRegistration();

        optionalPerson.ifPresent(System.out::println);
    }

    @Transactional(readOnly = true)
    public void queriesFunctionAggregation() {


        System.out.println("================== Consulta con eel total de registros de la tabla ==================");
        Long count = repository.getTotalPerson();
        System.out.println(count);

        System.out.println("================== Consulta con eel min de id ==================");
        Long max = repository.getMaxId();
        System.out.println(max);

        System.out.println("================== Consulta con eel max dee id ==================");
        Long min = repository.getMinId();
        System.out.println(min);


        System.out.println("================== Consulta nombre y su longitud ==================");
        List<Object[]> objects = repository.getPersonNameLength();
        objects.forEach(objects1 -> {
            String name = (String) objects1[0];
            Integer length = (Integer) objects1[1];

            System.out.println("name: " + name + ", length: " + length);
        });

        System.out.println("================== Consulta con eel nnombre mas corto ==================");
        Integer minLengthName = repository.getMinLengthName();
        System.out.println(minLengthName);

        System.out.println("================== Consulta con eel nombre mas largo ==================");
        Integer maxLengthName = repository.getMaxLengthName();
        System.out.println(maxLengthName);

        System.out.println("================== Consulta resumen de funcionese de agragacion min, max, sum, avg, count ==================");
        Object[] resumen = (Object[]) repository.getResumeAggregationFunction();
        System.out.println("min: " + resumen[0] +
                ", max: " + resumen[1] +
                " sum: " + resumen[2] +
                " avg: " + resumen[3] +
                " count: " + resumen[4]);
    }

    @Transactional(readOnly = true)
    public void personalizeQueryBetween() {
        System.out.println("================== Consulta por rangos ==================");

//        List<Person> persons = repository.findAllBetweenId(2L, 5L);
//        List<Person> persons = repository.findByIdBetween(2L,5L);
        List<Person> persons = repository.findByIdBetweenOrderByIdDesc(2L, 5L);

        persons.forEach(System.out::println);

        System.out.println("================== Consulta por rangos (name) ==================");

        List<Person> personsNames = repository.findAllBetweenName("J", "Q");
//        List<Person> personsNames = repository.findByNameBetween("J","Q");

        personsNames.forEach(System.out::println);

        System.out.println("================== Consulta que muestra todo ==================");

//        List<Person> people = repository.getAllOrdered();
        List<Person> people = repository.findByOrderByNameDesc();

        people.forEach(System.out::println);
    }

    @Transactional(readOnly = true)
    public void personalizeQueryConcatUpperAndLowerCase() {

        System.out.println("================== Consulta con nombres y apellidos de personas ==================");

        List<String> names = repository.findAllFullNameConcat();

        names.forEach(System.out::println);
        System.out.println("================== Consulta con nombres y apellidos (Mayusculas) ==================");

        List<String> namesUpper = repository.findAllFullNameConcatUpper();

        namesUpper.forEach(System.out::println);

        System.out.println("================== Consulta con nombres y apellidos de personas (Minusculas) ==================");

        List<String> namesLower = repository.findAllFullNameConcatLower();

        namesLower.forEach(System.out::println);

        System.out.println("================== Consulta con nombres y apellidos de personas (Mix) ==================");

        List<Object[]> namesLowerUpper = repository.findAllPersonDataListCase();
        namesLowerUpper.forEach(reg -> System.out.println("id : " + reg[0] +
                ", nombre: " + reg[1] +
                ", apellidos: " + reg[2] +
                ", leenguaje: " + reg[3]));

    }

    @Transactional(readOnly = true)
    public void personalizeQueryDistinct() {
        System.out.println("================== Consulta con nombres de personas ==================");

        List<String> names = repository.findAllNames();

        names.forEach(System.out::println);

        System.out.println("================== Consulta con nombres de personas con nombres no repeetidos  ==================");

        List<String> namesDistinct = repository.findAllNamesDistinct();

        namesDistinct.forEach(System.out::println);

        System.out.println("================== Consulta con nombres de personas con nombres no repeetidos y los cuenta ==================");

        Long namesDistinctCount = repository.findAllNamesDistinctCount();

        System.out.println(namesDistinctCount);

    }

    @Transactional(readOnly = true)
    public void personalizeQuery2() {
        System.out.println("================== Consulta por objeto persona y lenguaje dee programacion ==================");

        List<Object[]> personReg = repository.findAllMixPerson();

        personReg.forEach(person ->
                System.out.println("programmingLanguage = " + person[1] + ", person: " + person[0])
        );

        System.out.println("================== Consulta por objeto persona y deevuelve un entity de instancia ==================");

        List<Person> personList = repository.findAllObjectPersonPersonalizer();

        personList.forEach(System.out::println);

        System.out.println("================== Consulta por objeto persona y deevuelve un dto  ==================");

        List<PersonDto> personDtoList = repository.findAllPersonDto();

        personDtoList.forEach(System.out::println);
    }

    @Transactional(readOnly = true)
    public void personalizeQuery() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("================== Consulta solo el nombre por el id ==================");
        System.out.println("Ingrese el id de la persona para el nombre: ");
        Long id = scanner.nextLong();

        scanner.close();

        System.out.println("==================== Mostrando nombre por el id ====================");
        String name = repository.getNameById(id);
        System.out.println(name);

        System.out.println("==================== Mostrando solo el id ====================");
        Long idPer = repository.getIdById(id);
        System.out.println(idPer);


        System.out.println("==================== Mostrando nombre completo con concat ====================");
        String fullName = repository.getFullNameById(id);
        System.out.println(fullName);

        System.out.println("Consulta por campos personalizados por el id");
        Object[] perObjects = (Object[]) repository.obtenerPersonDataFullById(id);
        System.out.println("id : " + perObjects[0] +
                ", nombre: " + perObjects[1] +
                ", apellidos: " + perObjects[2] +
                ", leenguaje: " + perObjects[3]);

        System.out.println("Consulta por campos personalizados lista");
        List<Object[]> regs = repository.obtenerPersonDataFullList();
        regs.forEach(reg -> System.out.println("id : " + reg[0] +
                ", nombre: " + reg[1] +
                ", apellidos: " + reg[2] +
                ", leenguaje: " + reg[3]));
    }

    @Transactional
    public void delete() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el id de la persona a eliminar: ");
        Long id = scanner.nextLong();
        repository.findAll().forEach(System.out::println);
        repository.deleteById(id);
        repository.findAll().forEach(System.out::println);
        scanner.close();

    }

    @Transactional
    public void delete2() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el id de la persona a eliminar: ");
        Long id = scanner.nextLong();
        Optional<Person> personOptional = repository.findById(id);
        personOptional.ifPresentOrElse(
                repository::delete,
                () -> System.out.println("Lo sentimos no exist la persona con es id.."));
        scanner.close();

    }

    @Transactional
    public void update() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el id de la persona a editar: ");
        Long id = scanner.nextLong();

        Optional<Person> personOptional = repository.findById(id);

        personOptional.ifPresent(person -> {
                    System.out.println(person);
                    System.out.println("Ingrese el lenguage de programacion: ");
                    String programmingLanguage = scanner.next();
                    person.setProgrammingLanguage(programmingLanguage);

                    Person personDB = repository.save(person);
                    System.out.println(personDB);
                }

        );

        scanner.close();

    }

    @Transactional
    public void create() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el nombre: ");
        String name = scanner.next();

        System.out.println("Ingrese el apellido: ");
        String lastname = scanner.next();

        System.out.println("Ingrese el lenguaje de programacion: ");
        String programmingLanguage = scanner.next();
        scanner.close();

        Person person = new Person(null, name, lastname, programmingLanguage);

        Person newPerson = repository.save(person);

        System.out.println(newPerson);

        repository.findById(newPerson.getId()).ifPresent(System.out::println);


    }

    @Transactional(readOnly = true)
    public void findOne() {
//        Person person = null;
//
//        Optional<Person> optionalPerson = repository.findById(8L);
//
//        if(optionalPerson.isPresent())
//        if(!optionalPerson.isEmpty())
//            person = optionalPerson.get();
//
//        System.out.println(person);

//        repository.findById(9L).ifPresent(System.out::println);
//        repository.findOne(9L).ifPresent(System.out::println);
//        repository.findOneName("Andres").ifPresent(System.out::println);
//        repository.findLikeName("Fr").ifPresent(System.out::println);
        repository.findByNameContaining("Fr").ifPresent(System.out::println); // Igual que el like %

    }

    @Transactional(readOnly = true)
    public void list() {
        //        List<Person> persons = (List<Person>) repository.findAll();
//        List<Person> persons = repository.buscarByProgrammingLanguage("Java","Andres");
        List<Person> persons = repository.findByProgrammingLanguageAndName("Java",
                "Andres");

        persons.forEach(System.out::println);

        List<Object[]> personsData = repository.obtenerPersonDataByProgrammingLanguage("Java");

        personsData.forEach(personData -> System.out.println(personData[0] + " es experto en " + personData[1]));
    }
}
