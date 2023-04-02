package nl.pcsw.demo.serivce;

import java.util.List;
import nl.pcsw.demo.controller.UserDto;

public interface UserService {

    List<UserDto> getUsers();

}
