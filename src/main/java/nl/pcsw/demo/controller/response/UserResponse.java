package nl.pcsw.demo.controller.response;

import java.util.ArrayList;
import java.util.List;
import nl.pcsw.demo.controller.UserDto;

public class UserResponse {

    private List<UserDto> persons = new ArrayList<>();

    public List<UserDto> getPersons() {
        return new ArrayList<>(persons);
    }

    public void setPersons(List<UserDto> persons) {
        this.persons = persons;
    }

}
