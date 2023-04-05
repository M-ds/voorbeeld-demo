package nl.pcsw.demo.repo;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> getUsers() {
        var getUsers = """
                select *
                from person
                """.trim();

        return jdbcTemplate.query(getUsers, userRowMapper());
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> {
            User user = new User();
            user.setRole(rs.getString("role"));
            user.setUsername(rs.getString("username"));
            return user;
        };
    }

}
