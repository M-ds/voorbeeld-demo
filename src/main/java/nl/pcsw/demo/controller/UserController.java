package nl.pcsw.demo.controller;

import nl.pcsw.demo.controller.response.UserResponse;
import nl.pcsw.demo.serivce.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public UserResponse getUsers() {
        var persons = userService.getUsers();
        UserResponse userResponse = new UserResponse();

        if (persons.isEmpty()) {
            return userResponse;
        }

        userResponse.setPersons(persons);
        return userResponse;
    }

}
