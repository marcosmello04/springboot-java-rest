package marcosmello04.github.data.dto.v1;

/*import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;*/

/*import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import marcosmello04.github.serializer.SexSerializer;*/

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

//@JsonPropertyOrder({"id", "first_name", "last_name", "address", "sex"})
//@JsonFilter("PersonFilter")
public class PersonDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    //@JsonProperty("first_name")
    private String firstName;
    //@JsonProperty("last_name")
    private String lastName;
    //@JsonInclude(JsonInclude.Include.NON_NULL)
    //@JsonFormat(pattern = "dd/MM/yyyy")
    private Date birthday;
    private String address;
    //@JsonInclude(JsonInclude.Include.NON_EMPTY)
    //@JsonInclude(JsonInclude.Include.NON_NULL)
    private String phoneNumber;
    //@JsonIgnore
    //@JsonSerialize(using = SexSerializer.class)
    private String sex;
    //@JsonInclude(JsonInclude.Include.NON_NULL)
    private String sensitiveData;

    public PersonDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSensitiveData() {
        return sensitiveData;
    }

    public void setSensitiveData(String sensitiveData) {
        this.sensitiveData = sensitiveData;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PersonDTO personDTO = (PersonDTO) o;
        return Objects.equals(getId(), personDTO.getId()) && Objects.equals(getFirstName(), personDTO.getFirstName()) && Objects.equals(getLastName(), personDTO.getLastName()) && Objects.equals(getBirthday(), personDTO.getBirthday()) && Objects.equals(getAddress(), personDTO.getAddress()) && Objects.equals(getPhoneNumber(), personDTO.getPhoneNumber()) && Objects.equals(getSex(), personDTO.getSex()) && Objects.equals(getSensitiveData(), personDTO.getSensitiveData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getBirthday(), getAddress(), getPhoneNumber(), getSex(), getSensitiveData());
    }
}
