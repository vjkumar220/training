/*package com.oodles.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oodles.services.UserService;

@Controller
public class RegistrationController {
	private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);
 
    public RegistrationController() {
        System.out.println("RegistrationController()");
      @Autowired
      private UserService userService;
      
      @RequestMapping(value = "/")
      public ModelAndView listUser(ModelAndView model) throws IOException {
          List<User> listUser = userService.getAllUser();
          model.addObject("listUser", listUser);
          model.setViewName("home");
          return model;
      }
   
      @RequestMapping(value = "/newUser", method = RequestMethod.GET)
      public ModelAndView newContact(ModelAndView model) {
    	  User user = new User();
          model.addObject("user", user);
          model.setViewName("UserForm");
          return model;
      }
   
      @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
      public ModelAndView saveUser(@ModelAttribute User user) {
          if (user.getId() == 0) { 
              userService.addUser(user);
          } else {
        	  userService.updateUser(user);
          }
          return new ModelAndView("redirect:/");
      }
   
      @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
      public ModelAndView deleteUser(HttpServletRequest request) {
          int userId = Integer.parseInt(request.getParameter("id"));
          userService.deleteUser(userId);
          return new ModelAndView("redirect:/");
      }
   
      @RequestMapping(value = "/editUser", method = RequestMethod.GET)
      public ModelAndView editContact(HttpServletRequest request) {
          int userId = Integer.parseInt(request.getParameter("id"));
          User user = userService.getUser(userId);
          ModelAndView model = new ModelAndView("UserForm");
          model.addObject("user",user);
   
          return model;
      }
   
        
        
        
        
    }
}*/
