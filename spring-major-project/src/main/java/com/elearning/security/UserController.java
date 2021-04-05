package com.elearning.security;
//package com.example.demo.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.annotation.Secured;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//
//import com.example.demo.model.User;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@CrossOrigin(origins = "*", maxAge = 3600)
//@RestController
//public class UserController {
//
//    @Autowired
//    private UserRepository ur;
//
//    @PreAuthorize("hasRole('ADMIN','USER')")
//    @RequestMapping(value="/users", method = RequestMethod.GET)
//    public List<User> listUser(){
//        return ur.findAll();
//    }
//
//    @PreAuthorize("hasRole('USER')")
//    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
//    public Optional<User> getOne(@PathVariable(value = "id") int id){
//        return ur.findById(id);
//    }
//
//    @RequestMapping(value="/signup", method = RequestMethod.POST)
//    public User saveUser(@RequestBody User user){
//    	List<String> roles = new ArrayList<String>();
//    	roles.add("USER");		//change this depending on roles
//    	user.setRoles(roles);
//        return ur.save(user);
//    }
//}