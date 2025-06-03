package marcosmello04.github.mapper.custom;

import marcosmello04.github.data.dto.v2.PersonDTOV2;
import marcosmello04.github.model.Person;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper {

    public PersonDTOV2 convertEntityToDTO(Person person) {
        PersonDTOV2 dto = new PersonDTOV2();
        dto.setId(person.getId());
        dto.setFirstName(person.getFirstName());
        dto.setLastName(person.getLastName());
        dto.setBirthday(new Date());
        dto.setAddress(person.getAddress());
        dto.setSex(person.getSex());

        return dto;
    }

    public Person convertDTOtoEntity(PersonDTOV2 person) {
        Person entity = new Person();
        entity.setId(person.getId());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        //entity.setBirthday(new Date());
        entity.setAddress(person.getAddress());
        entity.setSex(person.getSex());

        return entity;
    }
}
