package com.sid.gestionUtilisateurs.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sid.gestionUtilisateurs.dtos.UserDto;
import com.sid.gestionUtilisateurs.enums.Gender;
import com.sid.gestionUtilisateurs.exceptions.UserNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiecImplTest {

	
    private UserServiceI userService;
	
    @Autowired
	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}

	LocalDate localDate = LocalDate.of(1995, 1, 12);
	LocalDate localDate2 = LocalDate.of(2022, 1, 12);
	
	@Test
	public void shouldSaveUserWithSuccess() {
		
		try {
			UserDto expectedUser = UserDto.builder()
					.nom("Bamba")
					.paysResidence("France")
					.telephone("+33 1 23 45 67 90")
					.genre(Gender.F.toString())
					.dateNaissance(localDate)
					.build();
			
			UserDto savedUser = userService.saveUser(expectedUser);
			
			assertNotNull(savedUser);
			assertNotNull(savedUser.getId());
			assertEquals(expectedUser.getNom(), savedUser.getNom());
			assertEquals(expectedUser.getPaysResidence(), savedUser.getPaysResidence());
			assertEquals(expectedUser.getGenre(), savedUser.getGenre());
			assertEquals(expectedUser.getDateNaissance(), savedUser.getDateNaissance());
			assertEquals(expectedUser.getTelephone(), savedUser.getTelephone());
			
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	@Test
	public void shouldSaveUserWithNotUserNotFoundException() {
		
			UserDto expectedUser = UserDto.builder()
					.nom("Bamba")
					.paysResidence("France")
					.telephone("+33 1 23 45 67 90")
					.genre(Gender.F.toString())
					.dateNaissance(localDate2)
					.build();
			
			UserNotFoundException expectedException = assertThrows(UserNotFoundException.class, ()-> userService.saveUser(expectedUser));
			
			assertEquals("Inconsistency! Please check the data entered.",expectedException.getMessage());
			
	}
	
	@Test
	public void shouldUpdateUserWithSuccess() {
		
		try {
			UserDto expectedUser = UserDto.builder()
					.nom("Bamba")
					.paysResidence("France")
					.telephone("+33 1 23 45 67 90")
					.genre(Gender.F.toString())
					.dateNaissance(localDate)
					.build();
			
			UserDto savedUser = userService.saveUser(expectedUser);
			
			UserDto updateUser = savedUser;
			updateUser.setNom("Lamine");
			savedUser = userService.updateUser(updateUser);
			
			assertNotNull(updateUser);
			assertNotNull(updateUser.getId());
			assertEquals(updateUser.getId(), savedUser.getId());
			assertEquals(updateUser.getNom(), savedUser.getNom());
			assertEquals(updateUser.getPaysResidence(), savedUser.getPaysResidence());
			assertEquals(updateUser.getGenre(), savedUser.getGenre());
			assertEquals(updateUser.getDateNaissance(), savedUser.getDateNaissance());
			assertEquals(updateUser.getTelephone(), savedUser.getTelephone());
			
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void shouldfindUserByIdWithSuccess() {
		
		try {
			UserDto expectedUser = UserDto.builder()
					.nom("Bamba")
					.paysResidence("France")
					.telephone("+33 1 23 45 67 90")
					.genre(Gender.F.toString())
					.dateNaissance(localDate)
					.build();
			
			UserDto savedUser = userService.saveUser(expectedUser);
			
			UserDto findUser =  userService.getUserById(savedUser.getId());
			
			assertNotNull(findUser);
			assertNotNull(findUser.getId());
			assertEquals(findUser.getId(), savedUser.getId());
			assertEquals(findUser.getNom(), savedUser.getNom());
			assertEquals(findUser.getPaysResidence(), savedUser.getPaysResidence());
			assertEquals(findUser.getGenre(), savedUser.getGenre());
			assertEquals(findUser.getDateNaissance(), savedUser.getDateNaissance());
			assertEquals(findUser.getTelephone(), savedUser.getTelephone());
			
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void shouldfindUserByIdWithNotUserNotFoundException() {
		
		UserNotFoundException expectedException = assertThrows(UserNotFoundException.class, ()-> userService.getUserById(0l));
		assertEquals("User with id: 0 Not found",expectedException.getMessage());
	}
	
	@Test
	public void shouldfindUserByNameWithSuccess() {
		
		try {
			UserDto expectedUser = UserDto.builder()
					.nom("Bamba")
					.paysResidence("France")
					.telephone("+33 1 23 45 67 90")
					.genre(Gender.F.toString())
					.dateNaissance(localDate)
					.build();
			
			 userService.saveUser(expectedUser);
			
			List<UserDto> findUsers =  userService.searchUser("Bamba");
			assertNotNull(findUsers);
			assertThat(findUsers).size().isGreaterThan(0);
			
			
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void shouldfindUserByNameWhenDoesNotExist() {
		
		try {
			UserDto expectedUser = UserDto.builder()
					.nom("Bamba")
					.paysResidence("France")
					.telephone("+33 1 23 45 67 90")
					.genre(Gender.F.toString())
					.dateNaissance(localDate)
					.build();
			
			 userService.saveUser(expectedUser);
			
			List<UserDto> findUsers =  userService.searchUser("Lamine");
			assertNotNull(findUsers);
			assertThat(findUsers).size().isLessThanOrEqualTo(0);
			
			
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void shoulddeleteUserWithSuccess() {
		
		try {
			UserDto expectedUser = UserDto.builder()
					.nom("Bamba")
					.paysResidence("France")
					.telephone("+33 1 23 45 67 90")
					.genre(Gender.F.toString())
					.dateNaissance(localDate)
					.build();
			String succes="SUCCESS";
			 UserDto savedUser=userService.saveUser(expectedUser);
			 String reponse = userService.deleteUser(savedUser.getId());
			 assertThat(reponse).isEqualTo(succes);
			 
			 
			 
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
