package marcosmello04.github.services;

import marcosmello04.github.exception.ResourceNotFoundException;
import marcosmello04.github.model.Person;
import marcosmello04.github.repository.PersonRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private org.slf4j.Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());

    //Inject
    @Autowired
    PersonRepository repository;


    // http://localhost:8080/person
    public ArrayList<Person> findAll() {
        logger.info("Listing People.");

        return (ArrayList<Person>) repository.findAll();
        /*
        var list = new ArrayList<Person>();
        for (long i = 0; i < 10; i++) {
            list.add(newPerson(i));
        }
        return list;
        */
    }

    // http://localhost:8080/person/id
    public Person findById(Long id) {
        logger.info("Finding Person.");
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person not found."));
        //return newPerson(counter.incrementAndGet());
    }

    /*
        public Person newPerson(Long id) {
        logger.info("New Person.");
        Person person = new Person();
        person.setId(id);
        person.setFirstName("Test");
        person.setLastName("Test");
        person.setAddress("Test");
        person.setSex("Test");
        return person;
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person not found."));
        }
    */

    //Post
    public Person createPerson(Person person) {
        logger.info("Creating Person.");
        return repository.save(person);
    }

    //Put
    public Person updatePerson(Person person) {
        logger.info("Updating Person.");
        Person entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("Person not found."));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setSex(person.getSex());

        return repository.save(entity);
    }

    //Delete
    public void deletePerson(Long id) {
        logger.info("Deleting Person.");
        Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person not found."));

        repository.delete(entity);
    }
}
