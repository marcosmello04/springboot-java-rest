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
        Person person = new Person();
        person.setId(id);
        person.setFirstName("");
        person.setLastName("");
        person.setAddress("");
        person.setSex("");

        return person;
    }
}
