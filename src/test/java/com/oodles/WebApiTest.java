/*package com.oodles;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oodles.controllers.RegistrationController;
import com.oodles.dto.UserDto;
import com.oodles.models.User;
import com.oodles.services.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(RegistrationController.class)
public class WebApiTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testCreateUserSuccess() throws Exception {
    	UserDto user = new UserDto("Shubham","Shubhamsingh@gmail.com","9876543567","India","ASdfw@123");

        given(userService.createUser(any(UserDto.class))).willReturn(user);

        String res = mockMvc.perform(post("/v1/user/signup")
            .content(mapper.writeValueAsString(user))
            .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

        UserDto responseUser = mapper.readValue(res, UserDto.class);
        assertEquals(user, responseUser);
    }
}*/