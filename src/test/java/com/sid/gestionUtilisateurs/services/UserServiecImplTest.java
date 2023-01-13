package com.sid.gestionUtilisateurs.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
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
	
}
