package nl.pcsw.demo.serivce;

import java.util.List;
import nl.pcsw.demo.controller.UserDto;
import nl.pcsw.demo.repo.UserRepository;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> getUsers() {
        var users = userRepository.getUsers();
        return users.stream().map(user -> new UserDto(user.getUsername(), user.getRole())).toList();
    }

}
