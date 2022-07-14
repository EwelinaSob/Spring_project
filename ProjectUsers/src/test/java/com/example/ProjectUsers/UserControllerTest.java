package com.example.ProjectUsers;

import com.example.ProjectUsers.model.User;
import com.example.ProjectUsers.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.*;
import java.util.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    @MockBean
    UserRepository repository;

    User RECORD_1 = new User(1, "Anna", "Kot", "President", null);
    User RECORD_2 = new User(2, "Ola", "Krzak", "Manager", 1);
    User RECORD_3 = new User(3, "Piotr", "Joda", "Programist", 2);


  //  @Test
//    public void getAllUsers_success() throws Exception {
//        List<User> records = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));
//        Mockito.when(repository.findAll()).thenReturn(records);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/users")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[2].name", Matchers.is("Piotr")));
//    }

    @Test
    public void getUserByID_UserDTO_creation_success() throws Exception {

        User found =  User.builder()
                .id(1)
                .name("Anna")
                .surname("Kot")
                .job("President")
                .manager(null)
                .build();

        Mockito.when(repository.findById(RECORD_1.getId())).thenReturn(java.util.Optional.of(found));

                mockMvc.perform(MockMvcRequestBuilders.get("/users/1")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.nameUser", is("Anna")));
    }

    @Test
    public void getUserByID_UserDTO_creation_failure() throws Exception {
        Mockito.when(repository.findById(RECORD_1.getId())).thenThrow(new UserNotFoundException(RECORD_1.getId()));
        mockMvc.perform(MockMvcRequestBuilders.get("/users/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void addUser_success() throws Exception {

        User user = User.builder()
                .name("Waldek")
                .surname("Kiepski")
                .job("HR")
                .manager(2)
                .build();
        Mockito.when(repository.save(user)).thenReturn(user);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(user));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Waldek")));
    }
   @Test
    public void addUser_failure_null() throws Exception {
        User user =  User.builder()
                .id(1)
                .name("Anna")
                .surname(null)
                .job("President")
                .manager(null)
                .build();
       Mockito.when(repository.save(user)).thenThrow(new FieldNotEmptyException());
        mockMvc.perform(MockMvcRequestBuilders.post("/users"))
                .andExpect(status().isBadRequest());

    }
    @Test
    public void addUser_failure_doubledUser() throws Exception {
        User user = User.builder()
                .name("Waldek")
                .surname("Kiepski")
                .job("HR")
                .manager(2)
                .build();

        Mockito.when(repository.save(user)).thenThrow(new ConflictException());

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(user));
        mockMvc.perform(mockRequest)
                .andExpect(status().isConflict())
                ;


    }
}
