package marcosmello04.github.services;

import marcosmello04.github.data.dto.v1.PersonDTO;
import marcosmello04.github.exception.RequiredObjectIsNullException;
import marcosmello04.github.model.Person;
import marcosmello04.github.repository.PersonRepository;
import marcosmello04.github.unitetests.mapper.mocks.MockPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServicesTest {

    MockPerson input;

    @InjectMocks
    private PersonServices service;

    @Mock
    PersonRepository repository;

    @BeforeEach
    void setUp() {
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() {
        Person person = input.mockEntity(1);
        person.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(person));
        var result = service.findById(1L);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self")
                        && link.getHref().endsWith("/api/person/v1/1")
                        && link.getType().equals("GET")
                )
        );

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("GET")
                )
        );

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("createPerson")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("POST")
                )
        );

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("updatePerson")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("PUT")
                )
        );

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("deletePerson")
                        && link.getHref().endsWith("/api/person/v1/1")
                        && link.getType().equals("DELETE")
                )
        );

        assertEquals("Address Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("female", result.getSex());
    }

    @Test
    void createPerson() {
        Person person = input.mockEntity(1);
        Person persisted = person;
        persisted.setId(1L);

        PersonDTO dto = input.mockDTO(1);

        when(repository.save(person)).thenReturn(persisted);
        var result = service.createPerson(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self")
                        && link.getHref().endsWith("/api/person/v1/1")
                        && link.getType().equals("GET")
                )
        );

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("GET")
                )
        );

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("createPerson")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("POST")
                )
        );

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("updatePerson")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("PUT")
                )
        );

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("deletePerson")
                        && link.getHref().endsWith("/api/person/v1/1")
                        && link.getType().equals("DELETE")
                )
        );

        assertEquals("Address Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("female", result.getSex());
    }

    @Test
    void createPersonV2() {
    }

    @Test
    void createNullObjectPerson() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.createPerson(null);
        });

        String expectedMessage = "Null object cannot be persisted.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void updatePerson() {
        Person person = input.mockEntity(1);
        Person persisted = person;
        persisted.setId(1L);

        PersonDTO dto = input.mockDTO(1);

        when(repository.findById(1L)).thenReturn(Optional.of(person));
        when(repository.save(person)).thenReturn(persisted);
        var result = service.updatePerson(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self")
                        && link.getHref().endsWith("/api/person/v1/1")
                        && link.getType().equals("GET")
                )
        );

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("GET")
                )
        );

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("createPerson")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("POST")
                )
        );

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("updatePerson")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("PUT")
                )
        );

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("deletePerson")
                        && link.getHref().endsWith("/api/person/v1/1")
                        && link.getType().equals("DELETE")
                )
        );

        assertEquals("Address Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("female", result.getSex());
    }

    @Test
    void updateNullObjectPerson() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.updatePerson(null);
        });

        String expectedMessage = "Null object cannot be persisted.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void deletePerson() {
        Person person = input.mockEntity(1);
        person.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(person));
        service.deletePerson(1L);
        verify(repository, times(1)).findById(anyLong());
        verify(repository, times(1)).delete(any(Person.class));
        verifyNoMoreInteractions(repository);
    }

    @Test
    void findAll() {
        List<Person> list = input.mockEntityList();
        when(repository.findAll()).thenReturn(list);
        List<PersonDTO> ppl = service.findAll();

        assertNotNull(ppl);
        assertEquals(10, ppl.size());

        var firstPerson = ppl.get(1);

        assertNotNull(firstPerson);
        assertNotNull(firstPerson.getId());
        assertNotNull(firstPerson.getLinks());

        assertNotNull(firstPerson.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self")
                        && link.getHref().endsWith("/api/person/v1/1")
                        && link.getType().equals("GET")
                )
        );

        assertNotNull(firstPerson.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("GET")
                )
        );

        assertNotNull(firstPerson.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("createPerson")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("POST")
                )
        );

        assertNotNull(firstPerson.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("updatePerson")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("PUT")
                )
        );

        assertNotNull(firstPerson.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("deletePerson")
                        && link.getHref().endsWith("/api/person/v1/1")
                        && link.getType().equals("DELETE")
                )
        );

        assertEquals("Address Test1", firstPerson.getAddress());
        assertEquals("First Name Test1", firstPerson.getFirstName());
        assertEquals("Last Name Test1", firstPerson.getLastName());
        assertEquals("female", firstPerson.getSex());

        var thirdPerson = ppl.get(3);

        assertNotNull(thirdPerson);
        assertNotNull(thirdPerson.getId());
        assertNotNull(thirdPerson.getLinks());

        assertNotNull(thirdPerson.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self")
                        && link.getHref().endsWith("/api/person/v1/3")
                        && link.getType().equals("GET")
                )
        );

        assertNotNull(thirdPerson.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("GET")
                )
        );

        assertNotNull(thirdPerson.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("createPerson")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("POST")
                )
        );

        assertNotNull(thirdPerson.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("updatePerson")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("PUT")
                )
        );

        assertNotNull(thirdPerson.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("deletePerson")
                        && link.getHref().endsWith("/api/person/v1/3")
                        && link.getType().equals("DELETE")
                )
        );

        assertEquals("Address Test3", thirdPerson.getAddress());
        assertEquals("First Name Test3", thirdPerson.getFirstName());
        assertEquals("Last Name Test3", thirdPerson.getLastName());
        assertEquals("female", thirdPerson.getSex());

        var sixthPerson = ppl.get(6);

        assertNotNull(sixthPerson);
        assertNotNull(sixthPerson.getId());
        assertNotNull(sixthPerson.getLinks());

        assertNotNull(sixthPerson.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self")
                        && link.getHref().endsWith("/api/person/v1/6")
                        && link.getType().equals("GET")
                )
        );

        assertNotNull(sixthPerson.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("GET")
                )
        );

        assertNotNull(sixthPerson.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("createPerson")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("POST")
                )
        );

        assertNotNull(sixthPerson.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("updatePerson")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("PUT")
                )
        );

        assertNotNull(sixthPerson.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("deletePerson")
                        && link.getHref().endsWith("/api/person/v1/6")
                        && link.getType().equals("DELETE")
                )
        );

        assertEquals("Address Test6", sixthPerson.getAddress());
        assertEquals("First Name Test6", sixthPerson.getFirstName());
        assertEquals("Last Name Test6", sixthPerson.getLastName());
        assertEquals("male", sixthPerson.getSex());
    }
}