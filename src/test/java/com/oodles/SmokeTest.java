package com.oodles;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.oodles.controllers.OrderController;
import com.oodles.controllers.RegistrationController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmokeTest {
    
    @Autowired
    private RegistrationController userController;
    @Autowired
    private OrderController orderController;

    @Test
    public void userController() throws Exception {
        assertThat(userController).isNotNull();
    }
    
    @Test
    public void orderController() throws Exception {
        assertThat(orderController).isNotNull();
    }
}

