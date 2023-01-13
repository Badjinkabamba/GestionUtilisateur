package com.sid.gestionUtilisateurs.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sid.gestionUtilisateurs.dtos.UserDto;
import com.sid.gestionUtilisateurs.exceptions.UserNotFoundException;
import com.sid.gestionUtilisateurs.services.UserServiceI;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author A734647
 *
 */

@RestController
@RequestMapping(path = "/api")
public class UserRestController {
	@Autowired
    private UserServiceI userService;
   

	@GetMapping("/users")
    public ResponseEntity<?> users(){
		try {
			List<UserDto> users = userService.listUsers();
			 return new ResponseEntity<>(users, HttpStatus.OK) ;
		} catch (Exception e) {
			return new ResponseEntity<String>("Unable to load list.", HttpStatus.NOT_FOUND);
		}
       
    }
	
    @GetMapping("/Users/search")
    public ResponseEntity <?> searchUser(@RequestParam(name = "keyword",defaultValue = "") String keyword){
    	try {
			List<UserDto> users = userService.searchUser("%"+keyword+"%");
			 return new ResponseEntity<>(users, HttpStatus.OK) ;
		} catch (Exception e) {
			return new ResponseEntity<String>("Unable to load list.", HttpStatus.NOT_FOUND);
		}
    }
    
    @GetMapping("/Users/{id}")
    public ResponseEntity<?> getUser(@PathVariable(name = "id") Long UserId) throws UserNotFoundException {
    	try {
    		UserDto  userData = userService.getUserById(UserId);
    		return new ResponseEntity<UserDto>(userData, HttpStatus.OK);
    		} 
    		catch (UserNotFoundException e) {
    			return new ResponseEntity<String>("User not found.", HttpStatus.NOT_FOUND);
    		}
        
    }
    
    @PostMapping("/Users")
    public ResponseEntity<?>  createUser(@RequestBody UserDto UserDto) throws UserNotFoundException {
    	try {
    		UserDto saveduser = userService.saveUser(UserDto);
    		return new ResponseEntity<UserDto>(saveduser, HttpStatus.CREATED);
    	} 	catch (UserNotFoundException e) {
    		return new ResponseEntity<String>("Error, the data entered is not consisten", HttpStatus.NOT_ACCEPTABLE);
    	}
    	catch (ParseException  e) {
    		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
    
    @PutMapping("/Users/{UserId}")
    public ResponseEntity<?>  updateUser(@PathVariable Long UserId, @RequestBody UserDto userDto) throws UserNotFoundException{
        
        try {
        	userDto.setId(UserId);
        	UserDto updateUser = userService.updateUser(userDto);
    		return new ResponseEntity<UserDto>(updateUser, HttpStatus.OK);
    	} 	catch (UserNotFoundException e) {
    		return new ResponseEntity<String>("Error, the data entered is not consisten", HttpStatus.NOT_ACCEPTABLE);
    	}
    	catch (ParseException  e) {
    		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
    
    @DeleteMapping("/Users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) throws UserNotFoundException{
    	  
        try {
        	userService.deleteUser(id);
    		return new ResponseEntity<>("", HttpStatus.OK);
    	} 	catch (UserNotFoundException e) {
    		return new ResponseEntity<String>("Error, the data entered is not consisten", HttpStatus.NOT_ACCEPTABLE);
    	}
    	catch (ParseException  e) {
    		return new  ResponseEntity<String>("User not found.", HttpStatus.NOT_FOUND);
    	}
    }
}
