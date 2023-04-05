package nl.pcsw.demo;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.pcsw.demo.controller.UserController;
import nl.pcsw.demo.controller.UserDto;
import nl.pcsw.demo.controller.response.UserResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("classpath:/application.properties")
@Sql({ "/users.sql" })
class DemoApplicationTests {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCanCreatePersonAndRetrieve() throws Exception {
        //Given
        MockHttpServletRequestBuilder builder = get("/users").contentType(MediaType.APPLICATION_JSON_VALUE);

        //When
        MvcResult response = mockMvc.perform(builder).andExpect(status().isOk())
                .andExpect(handler().methodCall(MvcUriComponentsBuilder.on(UserController.class).getUsers()))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.persons.*", hasSize(3))).andReturn();

        //Then
        assertNotNull(response);
        String content = response.getResponse().getContentAsString();
        UserResponse userResponse = objectMapper.readValue(content, UserResponse.class);

        assertNotNull(userResponse);
        UserDto personUser = userResponse.getPersons().get(0);
        assertEquals("person", personUser.username());
        assertEquals("user", personUser.role());

        UserDto memberUser = userResponse.getPersons().get(1);
        assertEquals("member", memberUser.username());
        assertEquals("member", memberUser.role());

        UserDto adminUser = userResponse.getPersons().get(2);
        assertEquals("admin", adminUser.username());
        assertEquals("admin", adminUser.role());
    }

}
