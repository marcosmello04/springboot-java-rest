package marcosmello04.github.services;

import marcosmello04.github.controllers.PersonController;
import marcosmello04.github.data.dto.v1.PersonDTO;
import marcosmello04.github.data.dto.v2.PersonDTOV2;
import marcosmello04.github.exception.RequiredObjectIsNullException;
import marcosmello04.github.exception.ResourceNotFoundException;
import static marcosmello04.github.mapper.ObjectMapper.parseObject;
import static marcosmello04.github.mapper.ObjectMapper.parseListObject;

import marcosmello04.github.mapper.custom.PersonMapper;
import marcosmello04.github.model.Person;
import marcosmello04.github.repository.PersonRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServices {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());

    //Inject
    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper converter;

    // http://localhost:8080/person
    public List<PersonDTO> findAll() {
        logger.info("Listing People.");

        var ppl = parseListObject(repository.findAll(), PersonDTO.class);
        //ppl.forEach(p -> addHateoasLinks(p));
        ppl.forEach(this::addHateoasLinks);

        return ppl;

    }

    // http://localhost:8080/person/id
    public PersonDTO findById(Long id) {
        logger.info("Finding Person.");
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person not found."));
        var dto = parseObject(entity, PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    private void addHateoasLinks(PersonDTO dto) {
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).createPerson(dto)).withRel("createPerson").withType("POST"));
        dto.add(linkTo(methodOn(PersonController.class).updatePerson(dto)).withRel("updatePerson").withType("PUT"));
        dto.add(linkTo(methodOn(PersonController.class).deletePerson(dto.getId())).withRel("deletePerson").withType("DELETE"));
    }

    //Post V1
    public PersonDTO createPerson(PersonDTO person) {
        if (person == null) throw new RequiredObjectIsNullException();
        logger.info("Creating Person. V1");
        var entity = parseObject(person, Person.class);
        var dto =  parseObject(repository.save(entity), PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    //Post V2
    public PersonDTOV2 createPersonV2(PersonDTOV2 person) {
        if (person == null) throw new RequiredObjectIsNullException();
        logger.info("Creating Person. V2");
        var entity = converter.convertDTOtoEntity(person);
        return converter.convertEntityToDTO(repository.save(entity));
    }

    //Put
    public PersonDTO updatePerson(PersonDTO person) {
        if (person == null) throw new RequiredObjectIsNullException();
        logger.info("Updating Person.");
        Person entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("Person not found."));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setSex(person.getSex());
        entity.setAddress(person.getAddress());

        var dto = parseObject(repository.save(entity), PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    //Delete
    public void deletePerson(Long id) {
        logger.info("Deleting Person.");
        Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person not found."));

        repository.delete(entity);
    }
}
