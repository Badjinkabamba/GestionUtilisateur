package com.sid.gestionUtilisateurs.mappers;

import java.util.ArrayList;
import java.util.List;

import com.sid.gestionUtilisateurs.dtos.UserDto;
import com.sid.gestionUtilisateurs.entities.User;
import com.sid.gestionUtilisateurs.enums.Gender;

import lombok.NoArgsConstructor;

/**
 * 
 * @author A734647
 *
 */

@NoArgsConstructor
public class UserMapperImpl {

	public User toEntity(UserDto userDto) {
		
		User entity = new User();
		
		entity.setId(userDto.getId());
		
		entity.setNom(userDto.getNom());
		
		entity.setDateNaissance(userDto.getDateNaissance());;
		
		entity.setPaysResidence(userDto.getPaysResidence());
		
		if (userDto.getGenre()!=null && userDto.getGenre().length()> 0) {
			
			entity.setGenre(Gender.valueOf(userDto.getGenre().toUpperCase()));
		}
		else {
			
			entity.setGenre(null);
		}

		entity.setTelephone(userDto.getTelephone());
		
		return entity;

	}

	public UserDto toDto(User entity) {
		
		UserDto userDto = new UserDto();
		
		userDto.setId(entity.getId());
		
		userDto.setNom(entity.getNom());
		
		userDto.setDateNaissance(entity.getDateNaissance());;
		
		userDto.setPaysResidence(entity.getPaysResidence());
		
		if ( entity.getGenre()!=null  && entity.getGenre().toString().length()>0) {
			
			userDto.setGenre(entity.getGenre().toString());
		}
		else {
			
			userDto.setGenre(null);
		}

		userDto.setTelephone(entity.getTelephone());
		
		return userDto;

	}

	public List<UserDto> toDtos(List<User> entities) {
		
		List<UserDto> models = new ArrayList<>();
		
		if (!entities.isEmpty()) {
			
			entities.forEach(e -> {
				
				models.add(toDto(e));
			});
		}
		
		return models;

	}

	public List<User> toEntities(List<UserDto> models) {
		
		List<User> entities = new ArrayList<>();
		
		if (!models.isEmpty()) {
			
			models.forEach(e -> {
				
				entities.add(toEntity(e));
			});
		}
		
		return entities;

	}

}
