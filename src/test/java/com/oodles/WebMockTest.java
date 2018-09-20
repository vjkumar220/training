package com.oodles;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultHandler;

import com.oodles.controller.UserController;
import com.oodles.domain.User;
import com.oodles.dto.UserDto;
import com.oodles.service.UserService;
import com.oodles.util.ResponseHandler;
import static com.oodles.util.Constants.SUCCESS;;


@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class WebMockTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService service;

	@MockBean
	private UserDto user;

	@Test
	public void getUserById() throws Exception {
		when(service.createUser(user)).thenReturn(null);
		this.mockMvc.perform(get("/user/users/by/id/1")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void getAllUsers() throws Exception {
		when(service.createUser(user)).thenReturn(null);
		this.mockMvc.perform(get("/user/all/users")).andDo(print()).andExpect(status().isOk());																
	}
	
	@Test
	public void deleteUserById() throws Exception {
		when(service.createUser(user)).thenReturn(null);
		this.mockMvc.perform(delete("/user/delete/user/by/userId/1")).andDo(print()).andExpect(status().isOk());
	}
	
/*	@Test
	public void createUser() throws Exception {
		when(service.createUser(user)).thenReturn(null);
		this.mockMvc.perform(post("/user/signup")).andDo(print()).andExpect(status().isOk());
	}*/
}
