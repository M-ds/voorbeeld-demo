package nl.pcsw.demo.serivce;

import java.util.List;
import nl.pcsw.demo.controller.UserDto;
import nl.pcsw.demo.repo.UserRepository;
import nl.pcsw.demo.repo.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepositoryImpl userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> getUsers() {
        var users = userRepository.getUsers();
        return users.stream().map(user -> new UserDto(user.getUsername(), user.getRole())).toList();
    }

}
