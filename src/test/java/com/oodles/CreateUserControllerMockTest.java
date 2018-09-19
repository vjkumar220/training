package com.oodles;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.oodles.controllers.RegistrationController;
import com.oodles.models.User;
import com.oodles.services.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(RegistrationController.class)
public class CreateUserControllerMockTest {
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    @MockBean
    private User user;
    @Test
	public void getUserById() throws Exception {
		when(userService.createUser(user)).thenReturn(null);
		this.mockMvc.perform(get("/v1/users/1")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void getAllUsers() throws Exception {
		when(userService.createUser(user)).thenReturn(null);
		this.mockMvc.perform(get("/users")).andDo(print()).andExpect(status().isOk());																
	}
	
	@Test
	public void deleteUserById() throws Exception {
		when(userService.createUser(user)).thenReturn(null);
		this.mockMvc.perform(delete("v1/users/7")).andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	public void createUser() throws Exception {
		when(userService.createUser(user)).thenReturn(null);
		this.mockMvc.perform(post("/v1/signup")).andDo(print()).andExpect(status().isOk());
	}

}
