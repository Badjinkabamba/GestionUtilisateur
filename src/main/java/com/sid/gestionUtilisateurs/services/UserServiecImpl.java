package com.sid.gestionUtilisateurs.services;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sid.gestionUtilisateurs.aspect.UserLogEntryExit;
import com.sid.gestionUtilisateurs.dtos.UserDto;
import com.sid.gestionUtilisateurs.entities.User;
import com.sid.gestionUtilisateurs.exceptions.UserNotFoundException;
import com.sid.gestionUtilisateurs.mappers.UserMapperImpl;
import com.sid.gestionUtilisateurs.repositories.UserRepository;

/**
 * 
 * @author A734647
 *
 */

@Service
@Transactional(rollbackFor = {Throwable.class})
public class UserServiecImpl implements UserServiceI {
	
	private UserRepository userRepository;
	
	private UserMapperImpl userMapper = new UserMapperImpl() ;
	
	

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}



	/**
	 * Methode To create a new user
	 */
	@UserLogEntryExit(showArgs = true, showResult = true, unit = ChronoUnit.MILLIS)
	@Override
	public UserDto saveUser(UserDto userDto) throws UserNotFoundException {

		   Period diff = Period.between(LocalDate.parse(userDto.getDateNaissance().toString()), LocalDate.now());
		   if (userDto.getPaysResidence().equalsIgnoreCase("France") && diff.getYears() >= 18) {
			   User user= userMapper.toEntity(userDto);
		        User savedUser = userRepository.save(user);
		        return userMapper.toDto(savedUser);
		   }
			else {
					throw new UserNotFoundException("Inconsistency! Please check the data entered.");
					
			}
	       
	}

	/**
	 * Methode To find all user
	 */
	@UserLogEntryExit(showArgs = true, showResult = true, unit = ChronoUnit.MILLIS)
	@Override
	public List<UserDto> listUsers() {
	
	  List<User> users= userRepository.findAll();
	  List<UserDto> userDtos = userMapper.toDtos(users);
	  return userDtos;
	}

	/**
	 * Methode To find user by id
	 */
	@UserLogEntryExit(showArgs = true, showResult = true, unit = ChronoUnit.MILLIS)
	@Override
	public UserDto getUserById(Long id) throws UserNotFoundException {
		  User user = userRepository.findById(id)
	                .orElseThrow(() -> new UserNotFoundException("User Not found"));
		return userMapper.toDto(user);
	}

	/**
	 * Methode To update a new user
	 */
	@UserLogEntryExit(showArgs = true, showResult = true, unit = ChronoUnit.MILLIS)
	@Override
	public UserDto updateUser(UserDto userDto) throws UserNotFoundException {

		  userRepository.findById(userDto.getId())
	                .orElseThrow(() -> new UserNotFoundException("User Not found"));
		  return saveUser(userDto);
	}

	/**
	 * Methode To find user by id
	 */
	@UserLogEntryExit(showArgs = true, showResult = true, unit = ChronoUnit.MILLIS)
	@Override
	public void deleteUser(Long id) throws UserNotFoundException {
		 User user = userRepository.findById(id)
	                .orElseThrow(() -> new UserNotFoundException("User Not found"));
		
		 userRepository.delete(user);
	}

	/**
	 * Methode To find user by Nam
	 */
	@UserLogEntryExit(showArgs = true, showResult = true, unit = ChronoUnit.MILLIS)
	@Override
	public List<UserDto> searchUser(String keyword) {
		
		List<User> users = userRepository.searchUser(keyword);
		
		return userMapper.toDtos(users);
	}

	
}
