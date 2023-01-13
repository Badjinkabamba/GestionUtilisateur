package com.sid.gestionUtilisateurs.dtos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Builder
@Data
@JsonInclude
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)

/**
 * 
 * @author A734647
 *
 */
public class UserDto {

	private long id;
	
	private String nom;
	
	@JsonProperty(access = Access.AUTO)
	@JsonFormat(pattern = "dd/M/yyyy")
	private LocalDate dateNaissance;
	
	private String paysResidence;
	
	private String telephone;
	
	private String genre;
	
}
