package nl.pcsw.demo.config;

import javax.sql.DataSource;
import nl.pcsw.demo.repo.UserRepository;
import nl.pcsw.demo.repo.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class RepoConfig {

    private final String url;
    private final String username;
    private final String password;
    private final String driverClassName;
    private final DataSource dataSource;

    public RepoConfig(@Value("${spring.datasource.url}") String url,
            @Value("${spring.datasource.username}") String username,
            @Value("${spring.datasource.password}") String password,
            @Value("${spring.datasource.driver-class-name}") String driverClassName) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.driverClassName = driverClassName;
        this.dataSource = createDataSource();
    }

    @Bean
    public UserRepository userRepository() {
        return new UserRepositoryImpl(new JdbcTemplate(dataSource));
    }

    private DataSource createDataSource() {
        return DataSourceBuilder.create().url(url).username(username).password(password)
                .driverClassName(driverClassName).build();
    }

}
