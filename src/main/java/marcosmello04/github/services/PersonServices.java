package marcosmello04.github.services;

import marcosmello04.github.data.dto.v1.PersonDTO;
import marcosmello04.github.data.dto.v2.PersonDTOV2;
import marcosmello04.github.exception.ResourceNotFoundException;
import static marcosmello04.github.mapper.ObjectMapper.parseObject;
import static marcosmello04.github.mapper.ObjectMapper.parseListObject;

import marcosmello04.github.mapper.custom.PersonMapper;
import marcosmello04.github.model.Person;
import marcosmello04.github.repository.PersonRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private org.slf4j.Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());

    //Inject
    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper converter;

    // http://localhost:8080/person
    public List<PersonDTO> findAll() {
        logger.info("Listing People.");

        return parseListObject(repository.findAll(), PersonDTO.class);

    }

    // http://localhost:8080/person/id
    public PersonDTO findById(Long id) {
        logger.info("Finding Person.");
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person not found."));
        return parseObject(entity, PersonDTO.class);
        //return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person not found."));
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

    //Post V1
    public PersonDTO createPerson(PersonDTO person) {
        logger.info("Creating Person. V1");
        var entity = parseObject(person, Person.class);
        return parseObject(repository.save(entity), PersonDTO.class);
    }

    //Post V2
    public PersonDTOV2 createPersonV2(PersonDTOV2 person) {
        logger.info("Creating Person. V2");
        var entity = converter.convertDTOtoEntity(person);
        return converter.convertEntityToDTO(repository.save(entity));
    }

    //Put
    public PersonDTO updatePerson(PersonDTO person) {
        logger.info("Updating Person.");
        Person entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("Person not found."));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setSex(person.getSex());
        entity.setAddress(person.getAddress());

        return parseObject(repository.save(entity), PersonDTO.class);
    }

    //Delete
    public void deletePerson(Long id) {
        logger.info("Deleting Person.");
        Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person not found."));

        repository.delete(entity);
    }
}
