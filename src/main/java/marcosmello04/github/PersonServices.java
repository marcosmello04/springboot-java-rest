package marcosmello04.github;

import marcosmello04.github.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());


    // http://localhost:8080/person
    public ArrayList<Person> findAll() {
        logger.info("Listing People.");
        var list = new ArrayList<Person>();

        for (long i = 0; i < 10; i++) {
            list.add(newPerson(i));
        }

        return list;
    }

    // http://localhost:8080/person/id
    public Person findById(String id) {
        logger.info("Finding Person.");

        return newPerson(counter.incrementAndGet());
    }

    public Person newPerson(Long id) {
        logger.info("New Person.");
        Person person = new Person();
        person.setId(id);
        person.setFirstName("Test");
        person.setLastName("Test");
        person.setAddress("Test");
        person.setSex("Test");

        return person;
    }

    //Post
    public Person createPerson(Person person) {
        logger.info("Creating Person.");
        return person;
    }

    //Put
    public Person updatePerson(Person person) {
        logger.info("Updating Person.");
        return person;
    }

    //Delete
    public void deletePerson(String id) {
        logger.info("Deleting Person.");
    }
}
