package com.sid.gestionUtilisateurs;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sid.gestionUtilisateurs.dtos.UserDto;
import com.sid.gestionUtilisateurs.enums.Gender;
import com.sid.gestionUtilisateurs.exceptions.UserNotFoundException;
import com.sid.gestionUtilisateurs.mappers.UserMapperImpl;
import com.sid.gestionUtilisateurs.repositories.UserRepository;
import com.sid.gestionUtilisateurs.services.UserServiceI;
import com.sid.gestionUtilisateurs.web.UserRestController;

//@SpringBootTest
@WebMvcTest(controllers = UserRestController.class)
class GestionUtilisateursApplicationTests {

	@Autowired
   private MockMvc mockMVC;

    @MockBean
    private UserServiceI userService;
    
    @MockBean
    private UserRepository userRepositorie;
    
	private UserMapperImpl userMapper = new UserMapperImpl() ;
    
    LocalDate localDate = LocalDate.of(1995, 1, 12);
	

    private  ObjectMapper mapper = new ObjectMapper();
    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
   
    @BeforeEach
    public  void beforeAll(){
    	try {
			mapper.findAndRegisterModules();
			mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
    
	/**
	 *  Start a POST-type Rest request for the url '/ data'
	 * @throws ResourceException
	 */
	@Test
	public void createUserMockMVCTest() throws UserNotFoundException {

    	UserDto expectedUser = UserDto.builder()
			 	.id(1)
				.nom("Bamba")
				.paysResidence("France")
				.telephone("+33 1 23 45 67 90")
				.genre(Gender.F.toString())
				.dateNaissance(localDate)
				.build();
    	
		try {
			
			
			String json= ow.writeValueAsString(expectedUser);
			
			MvcResult mvcResult =  mockMVC.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON)
					.characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON))
			        .andReturn();
			int status = mvcResult.getResponse().getStatus();
			 assertEquals(201, status);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Start a GET-type Rest request for the url '/ data'
	 * @throws ResourceException
	 */
@Test
public void getUserByIdkMVCTest() throws UserNotFoundException {
	try {
		 UserDto expectedUser = UserDto.builder()
				 	.id(1)
					.nom("Bamba")
					.paysResidence("France")
					.telephone("+33 1 23 45 67 90")
					.genre(Gender.F.toString())
					.dateNaissance(localDate)
					.build();
		 String json= ow.writeValueAsString(expectedUser);
		 Mockito.when(userService.getUserById(1L)).thenReturn(expectedUser);
		 
		 MockHttpServletResponse response =mockMVC.perform(get("/api/users/1").contentType(MediaType.APPLICATION_JSON)
				      .content(json).accept(MediaType.APPLICATION_JSON))
		              .andReturn().getResponse();
		 
		 assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		 assertThat(response.getContentAsString()).isEqualTo(json);
	        	
	} catch (Exception e) {
		e.printStackTrace();
	}

}
	
	 @Test
	    public void testGetAllUsers() throws Exception {
		 UserDto expectedUser = UserDto.builder()
				 	.id(1)
					.nom("Bamba")
					.paysResidence("France")
					.telephone("+33 1 23 45 67 90")
					.genre(Gender.F.toString())
					.dateNaissance(localDate)
					.build();

		    List<UserDto> allUsers = Arrays.asList(expectedUser);

		     Mockito.when(userService.listUsers()).thenReturn(allUsers);
		    
		 mockMVC.perform(get("/api/users"))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$[0].nom", is("Bamba")));
	    }
	 
	 @Test
		public void testDeleteUserById() throws Exception {
		 UserDto expectedUser = UserDto.builder()
				 	.id(1)
					.nom("Bamba")
					.paysResidence("France")
					.telephone("+33 1 23 45 67 90")
					.genre(Gender.F.toString())
					.dateNaissance(localDate)
					.build();
		// userService.saveUser(expectedUser);
		 Mockito.when(userRepositorie.getById(1L)).thenReturn(userMapper.toEntity(expectedUser));
		 Mockito.when(userService.deleteUser(1L)).thenReturn("SUCCESS");
			
		 mockMVC.perform(delete("/api/users/3") .contentType(MediaType.APPLICATION_JSON)
			      .content("SUCCESS"))
				 .andExpect(status().isOk());

		}

}
