package nl.pcsw.demo.config;

import nl.pcsw.demo.repo.UserRepository;
import nl.pcsw.demo.serivce.UserService;
import nl.pcsw.demo.serivce.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public UserService userService(UserRepository userRepository) {
        return new UserServiceImpl(userRepository);
    }

}
