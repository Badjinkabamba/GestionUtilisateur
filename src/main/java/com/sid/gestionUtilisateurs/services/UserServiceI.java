package com.sid.gestionUtilisateurs.services;

import java.util.List;


import com.sid.gestionUtilisateurs.dtos.UserDto;
import com.sid.gestionUtilisateurs.exceptions.UserNotFoundException;

/**
 * 
 * @author A734647
 *
 */

public interface UserServiceI {

	/**
	 * Methode To create a new user
	 */
	UserDto saveUser(UserDto userDto) throws UserNotFoundException;

	/**
	 * Methode To find all user
	 */
	List<UserDto> listUsers();

	/**
	 * Methode To find user by id
	 */
	UserDto getUserById(Long id) throws UserNotFoundException;

	/**
	 * Methode To update a new user
	 */
	UserDto updateUser(UserDto userDto) throws UserNotFoundException;

	/**
	 * Methode To find user by id
	 */
	String deleteUser(Long id) throws UserNotFoundException;

	/**
	 * Methode To find user by Nam
	 */
	List<UserDto> searchUser(String keyword);

	   
}
