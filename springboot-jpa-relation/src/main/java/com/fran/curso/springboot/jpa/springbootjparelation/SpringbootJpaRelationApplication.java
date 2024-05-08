package com.fran.curso.springboot.jpa.springbootjparelation;

import com.fran.curso.springboot.jpa.springbootjparelation.entities.Address;
import com.fran.curso.springboot.jpa.springbootjparelation.entities.Client;
import com.fran.curso.springboot.jpa.springbootjparelation.entities.ClientDetails;
import com.fran.curso.springboot.jpa.springbootjparelation.entities.Course;
import com.fran.curso.springboot.jpa.springbootjparelation.entities.Invoice;
import com.fran.curso.springboot.jpa.springbootjparelation.entities.Student;
import com.fran.curso.springboot.jpa.springbootjparelation.repository.ClientDetailsRepository;
import com.fran.curso.springboot.jpa.springbootjparelation.repository.ClientRepository;
import com.fran.curso.springboot.jpa.springbootjparelation.repository.CourseRepository;
import com.fran.curso.springboot.jpa.springbootjparelation.repository.InvoiceRepository;
import com.fran.curso.springboot.jpa.springbootjparelation.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication
public class SpringbootJpaRelationApplication implements CommandLineRunner {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ClientDetailsRepository clientDetailsRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJpaRelationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        manyToManyBiDirectionalRemove();
    }

    @Transactional
    public void manyToManyBiDirectionalRemove() {

        Student student1 = new Student("Jano", "Pura");
        Student student2 = new Student("Erba", "Doe");

        Course course1 = new Course("Curso de Java", "Andres");
        Course course2 = new Course("Curso de Angular", "Andres");

        student1.addCourse(course1);
        student1.addCourse(course2);
        student2.addCourse(course2);

        studentRepository.saveAll(Set.of(student1, student2));

        System.out.println(student1);
        System.out.println(student2);

        Optional<Student> optionalStudent = studentRepository.findById(1L);

        optionalStudent.ifPresent(student -> {
            Optional<Course> optionalCourse = courseRepository.findById(2L);

            optionalCourse.ifPresent(course -> {

                student.removeCourse(course);

                studentRepository.save(student);

                System.out.println(student);
            });
        });

    }

    @Transactional
    public void manyToManyBiDirectional() {

        Student student1 = new Student("Jano", "Pura");
        Student student2 = new Student("Erba", "Doe");

        Course course1 = new Course("Curso de Java", "Andres");
        Course course2 = new Course("Curso de Angular", "Andres");

        student1.addCourse(course1);
        student1.addCourse(course2);
        student2.addCourse(course2);

        studentRepository.saveAll(Set.of(student1, student2));

        System.out.println(student1);
        System.out.println(student2);



    }

    @Transactional
    public void manyToManyFindRemove() {

        Optional<Student> student1OptionalStudent = studentRepository.findById(1L);
        Optional<Student> student2OptionalStudent = studentRepository.findById(2L);

        Student student1 = student1OptionalStudent.get();
        Student student2 = student2OptionalStudent.get();

        Course course1 = courseRepository.findById(1L).get();
        Course course2 = courseRepository.findById(2L).get();

        student1.setCourses(Set.of(course1, course2));
        student2.setCourses(Set.of(course2));

        studentRepository.saveAll(Set.of(student1, student2));

        System.out.println(student1);
        System.out.println(student2);

        Optional<Student> optionalStudent = studentRepository.findById(1L);

        optionalStudent.ifPresent(student -> {
            Optional<Course> optionalCourse = courseRepository.findById(2L);

            optionalCourse.ifPresent(course -> {

                student.getCourses().remove(course);

                studentRepository.save(student);

                System.out.println(student);
            });
        });

    }

    @Transactional
    public void manyToManyFind() {

        Optional<Student> student1OptionalStudent = studentRepository.findById(1L);
        Optional<Student> student2OptionalStudent = studentRepository.findById(2L);

        Student student1 = student1OptionalStudent.get();
        Student student2 = student2OptionalStudent.get();

        Course course1 = courseRepository.findById(1L).get();
        Course course2 = courseRepository.findById(2L).get();

        student1.setCourses(Set.of(course1, course2));
        student2.setCourses(Set.of(course2));

        studentRepository.saveAll(Set.of(student1, student2));

        System.out.println(student1);
        System.out.println(student2);

    }

    @Transactional
    public void manyToMany() {

        Student student1 = new Student("Jano", "Pura");
        Student student2 = new Student("Erba", "Doe");

        Course course1 = new Course("Curso de Java", "Andres");
        Course course2 = new Course("Curso de Angular", "Andres");

        student1.setCourses(Set.of(course1, course2));
        student2.setCourses(Set.of(course2));

        studentRepository.saveAll(Set.of(student1, student2));

        System.out.println(student1);
        System.out.println(student2);

    }

    @Transactional
    public void oneToOneBiDireccionalFindById() {

        Optional<Client> clientOptional = clientRepository.findById(1L);

        clientOptional.ifPresent(client -> {

            ClientDetails clientDetails = new ClientDetails(true, 50000);

            client.setClientDetails(clientDetails);

            clientRepository.save(client);

            System.out.println(client);
        });

    }


    @Transactional
    public void oneToOneBiDireccional() {

        Client client = new Client("Fran", "Moras");

        ClientDetails clientDetails = new ClientDetails(true, 50000);

        client.setClientDetails(clientDetails);

        clientRepository.save(client);

        System.out.println(client);

    }

    @Transactional
    public void oneToOneFindById() {

        ClientDetails clientDetails = new ClientDetails(true, 50000);

        clientDetailsRepository.save(clientDetails);

        Optional<Client> clientOptional = clientRepository.findById(2L);

        clientOptional.ifPresent(client -> {
            client.setClientDetails(clientDetails);

            clientRepository.save(client);

            System.out.println(client);
        });


    }

    @Transactional
    public void oneToOne() {

        ClientDetails clientDetails = new ClientDetails(true, 50000);

        clientDetailsRepository.save(clientDetails);

        Client client = new Client("Fran", "Moras");
        client.setClientDetails(clientDetails);

        clientRepository.save(client);

    }

    @Transactional
    public void removeInvoiceBidereccional() {

        Client client = new Client("Fran", "Moras");

        Invoice invoiceHouse = new Invoice("Compras de la casa", 5000L);
        Invoice invoiceOffice = new Invoice("Compras de la oficina", 5000L);

        client.addInvoice(invoiceOffice).addInvoice(invoiceHouse);

        clientRepository.save(client);

        System.out.println(client);


        Optional<Client> optionalClientBd = clientRepository.findById(3L);

        optionalClientBd.ifPresent(clientDB -> {

//            Invoice invoiceDelete = new Invoice("Compras de la casa", 5000L);
//            invoiceDelete.setId(1L);
//            Optional<Invoice> optionalInvoice = Optional.of(invoiceDelete);
            //        Ejemplo si quieres buscarlo en el reepository
            Optional<Invoice> optionalInvoice = invoiceRepository.findById(2L);

            optionalInvoice.ifPresent(invoice -> {

                clientDB.removeInvoice(invoice);

                clientRepository.save(clientDB);
                System.out.println(clientDB);
            });

        });

    }

    @Transactional
    public void removeInvoiceBidereccionalFindById() {


        Optional<Client> optionalClient = clientRepository.findById(1L);

        optionalClient.ifPresent(client -> {

            Invoice invoiceHouse = new Invoice("Compras de la casa", 5000L);
            Invoice invoiceOffice = new Invoice("Compras de la oficina", 5000L);

            client.addInvoice(invoiceOffice).addInvoice(invoiceHouse);

            clientRepository.save(client);

            System.out.println(client);

        });

        Optional<Client> optionalClientBd = clientRepository.findById(1L);

        optionalClientBd.ifPresent(client -> {

            Invoice invoiceDelete = new Invoice("Compras de la casa", 5000L);
            invoiceDelete.setId(1L);
            //        Ejemplo si quieres buscarlo en el reepository
//            Optional<Invoice> optionalInvoice = invoiceRepository.findById(2L);
            Optional<Invoice> optionalInvoice = Optional.of(invoiceDelete);

            optionalInvoice.ifPresent(invoice -> {

                client.removeInvoice(invoice);

                clientRepository.save(client);
                System.out.println(client);
            });

        });

    }

    @Transactional
    public void oneToManyInvoiceBidereccionalFindById() {
        Optional<Client> optionalClient = clientRepository.findById(1L);

        optionalClient.ifPresent(client -> {

            Invoice invoiceHouse = new Invoice("Compras de la casa", 5000L);
            Invoice invoiceOffice = new Invoice("Compras de la oficina", 5000L);

            client.addInvoice(invoiceOffice).addInvoice(invoiceHouse);

            clientRepository.save(client);

            System.out.println(client);

        });

    }

    @Transactional
    public void oneToManyInvoiceBidereccional() {
        Client client = new Client("Fran", "Moras");

        Invoice invoiceHouse = new Invoice("Compras de la casa", 5000L);
        Invoice invoiceOffice = new Invoice("Compras de la oficina", 5000L);

        client.addInvoice(invoiceOffice).addInvoice(invoiceHouse);

        clientRepository.save(client);

        System.out.println(client);
    }

    @Transactional
    public void removeAddressById() {
        Optional<Client> optionalClient = clientRepository.findById(2L);

        optionalClient.ifPresent(client -> {
            Address address = new Address(189, "El verjel");
            Address address2 = new Address(158, "Fraternidad");

            client.setAddresses(List.of(address, address2));

            clientRepository.save(client);

            System.out.println(client);

            Optional<Client> optionalClient2 = clientRepository.findById(2L);

            optionalClient2.ifPresent(c -> {
                c.getAddresses().remove(address);
                clientRepository.save(c);
                System.out.println(c);
            });
        });


    }

    @Transactional
    public void removeAddress() {
        Client client = new Client("Fran", "Moras");

        Address address = new Address(189, "El verjel");
        Address address2 = new Address(158, "Fraternidad");

        client.getAddresses().add(address);
        client.getAddresses().add(address2);

        clientRepository.save(client);
        System.out.println(client);

        Optional<Client> optionalClient = clientRepository.findById(3L);

        optionalClient.ifPresent(c -> {
            c.getAddresses().remove(address);
            clientRepository.save(c);
            System.out.println(c);
        });

    }

    @Transactional
    public void oneToManyFindById() {
        Client client = clientRepository.findById(2L).orElseThrow();

        Address address = new Address(189, "El verjel");
        Address address2 = new Address(158, "Fraternidad");

        client.setAddresses(List.of(address2, address));

        clientRepository.save(client);

        System.out.println(client);
    }

    @Transactional
    public void oneToMany() {
        Client client = new Client("Fran", "Moras");

        Address address = new Address(189, "El verjel");
        Address address2 = new Address(158, "Fraternidad");

        client.getAddresses().add(address);
        client.getAddresses().add(address2);

        clientRepository.save(client);

        System.out.println(client);
    }

    @Transactional
    public void manyToOne() {

        Client client = new Client("Jhon", "Doe");

        clientRepository.save(client);

        Invoice invoice = new Invoice("Compras de oficina", 2000L);

        invoice.setClient(client);

        Invoice invoiceDB = invoiceRepository.save(invoice);

        System.out.println(invoiceDB);
    }

    @Transactional
    public void manyToOneFindById() {

        Optional<Client> clientRepositoryById = clientRepository.findById(2L);

        if (clientRepositoryById.isPresent()) {
            Client client = clientRepositoryById.orElseThrow();

            Invoice invoice = new Invoice("Compras de oficina", 2000L);

            invoice.setClient(client);

            Invoice invoiceDB = invoiceRepository.save(invoice);

            System.out.println(invoiceDB);
        }

    }
}
