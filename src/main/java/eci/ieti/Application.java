package eci.ieti;

import eci.ieti.data.CustomerRepository;
import eci.ieti.data.ProductRepository;
import eci.ieti.data.TodoRepository;
import eci.ieti.data.model.Customer;
import eci.ieti.data.model.Product;

import java.util.Date;

import eci.ieti.data.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@SpringBootApplication
public class Application implements CommandLineRunner {


    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TodoRepository todoRepository;


    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfiguration.class);
        MongoOperations mongoOperation = (MongoOperations) applicationContext.getBean("mongoTemplate");


        customerRepository.deleteAll();

        customerRepository.save(new Customer("Alice", "Smith"));
        customerRepository.save(new Customer("Bob", "Marley"));
        customerRepository.save(new Customer("Jimmy", "Page"));
        customerRepository.save(new Customer("Freddy", "Mercury"));
        customerRepository.save(new Customer("Michael", "Jackson"));

        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");

        customerRepository.findAll().stream().forEach(System.out::println);
        System.out.println();
        customerRepository.findAll();
        System.out.println("-------------------------------");
        customerRepository.findAll().stream().forEach(System.out::println);
        System.out.println();


        productRepository.deleteAll();

        productRepository.save(new Product(1L, "Samsung S8", "All new mobile phone Samsung S8"));
        productRepository.save(new Product(2L, "Samsung S8 plus", "All new mobile phone Samsung S8 plus"));
        productRepository.save(new Product(3L, "Samsung S9", "All new mobile phone Samsung S9"));
        productRepository.save(new Product(4L, "Samsung S9 plus", "All new mobile phone Samsung S9 plus"));
        productRepository.save(new Product(5L, "Samsung S10", "All new mobile phone Samsung S10"));
        productRepository.save(new Product(6L, "Samsung S10 plus", "All new mobile phone Samsung S10 plus"));
        productRepository.save(new Product(7L, "Samsung S20", "All new mobile phone Samsung S20"));
        productRepository.save(new Product(8L, "Samsung S20 plus", "All new mobile phone Samsung S20 plus"));
        productRepository.save(new Product(9L, "Samsung S20 ultra", "All new mobile phone Samsung S20 ultra"));

        System.out.println("Paginated search of products by criteria:");
        System.out.println("-------------------------------");

        productRepository.findByDescriptionContaining("plus", PageRequest.of(0, 2)).stream()
                .forEach(System.out::println);

        System.out.println();

        todoRepository.deleteAll();

        todoRepository.save(new Todo(1L,"Google Integration y arreglo de Base de datos",2,new Date(2020,10,9),"Sergio Nuñez","Pending"));
        todoRepository.save(new Todo(2L,"AWS Integration",2,new Date(2020,10,8),"Duvan Zapata","Pending"));
        todoRepository.save(new Todo(3L,"Amazon Integration",2,new Date(2020,10,7),"Pedro Coral","Pending"));
        todoRepository.save(new Todo(4L,"YouTuBe Integration",2,new Date(2020,10,6),"Camilo Forero","Pending"));
        todoRepository.save(new Todo(5L,"GitHub Integration",2,new Date(2020,10,5),"Gillermo Perez","Pending"));
        todoRepository.save(new Todo(6L,"Google Integration y arreglo de Base de datos",2,new Date(2020,10,4),"Sergio Nuñez","Pending"));
        todoRepository.save(new Todo(7L,"YouTuBe Integration",2,new Date(2020,10,3),"Julian Rodriguez","Pending"));
        todoRepository.save(new Todo(8L,"GitHub Integration",2,new Date(2020,10,2),"Lenny Ramirez","Pending"));
        todoRepository.save(new Todo(9L,"Amazon Integration",2,new Date(2020,10,1),"Ronaldo Nazario","Pending"));
        todoRepository.save(new Todo(10L,"AWS Integration",2,new Date(2020,10,11),"James Rodriguez","Pending"));
        todoRepository.save(new Todo(11L,"Amazon Integration",2,new Date(2020,10,12),"Juan Cuadrado","Pending"));
        todoRepository.save(new Todo(12L,"YouTuBe Integration",2,new Date(2020,10,13),"Davinson Sanchez","Pending"));
        todoRepository.save(new Todo(13L,"GitHub Integration",2,new Date(2020,10,15),"Harry Kain","Pending"));
        todoRepository.save(new Todo(14L,"Google Integration y arreglo de Base de datos",2,new Date(2020,10,16),"Cristiano Ronaldo","Pending"));
        todoRepository.save(new Todo(15L,"AWS Integration",2,new Date(2020,10,17),"Neymar junnior","Pending"));
        todoRepository.save(new Todo(16L,"Amazon Integration",2,new Date(2020,10,18),"Leonel andres Messi ","Pending"));
        todoRepository.save(new Todo(17L,"DB Integration",2,new Date(2020,10,19),"Javier Macherano","Pending"));
        todoRepository.save(new Todo(18L,"YouTuBe Integration",2,new Date(2020,10,20),"Yerry Mina","Pending"));
        todoRepository.save(new Todo(19L,"GitHub Integration",2,new Date(2020,10,21),"Santiago Arias","Pending"));
        todoRepository.save(new Todo(20L,"Google Integration y arreglo de Base de datos",2,new Date(2020,10,25),"Sergio Nuñez","Pending"));
        todoRepository.save(new Todo(21L,"AWS Integration",2,new Date(2020,10,26),"Radamel Falcao","Pending"));
        todoRepository.save(new Todo(22L,"AWS Integration",2,new Date(2020,10,27),"Duvan Zapata","Pending"));
        todoRepository.save(new Todo(23L,"AWS Integration",2,new Date(2020,10,28),"Duvan Zapata","Pending"));
        todoRepository.save(new Todo(24L,"Google Integration y arreglo de Base de datos",2,new Date(2020,10,29),"Sergio Nuñez","Pending"));
        todoRepository.save(new Todo(25L,"AWS Integration",2,new Date(2020,10,30),"Diomedes Diaz","Pending"));

        System.out.println("Paginated search of todo by responsible:");
        System.out.println("-------------------------------");

        todoRepository.findByResponsible("Sergio Nuñez", PageRequest.of(0, 2)).stream()
                .forEach(System.out::println);

        System.out.println("-------------------------------");

        System.out.println();

        Query query = new Query();
        query.addCriteria(Criteria.where("firstName").is("Alice"));

        Customer customer = mongoOperation.findOne(query, Customer.class);

        System.out.println(customer);

    }

}