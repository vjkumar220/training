/*package com.oodles;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.oodles.controllers.RegistrationController;
import com.oodles.services.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(RegistrationController.class)
public class CreateUserControllerMockTest {
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    
	@Test
	public void greetingShouldReturnMessageFromService() throws Exception {
        when(userService.createUser()).thenReturn("success");
        this.mockMvc.perform(get("/v1/signup")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("success")));
    }

}
*/