package com.sid.gestionUtilisateurs.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sid.gestionUtilisateurs.entities.User;

/**
 * 
 * @author A734647
 *
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	/**
	 * Methode To find user by Nam
	 */
	
	 @Query("select c from User c where c.nom like :kw")
	    List<User> searchUser(@Param("kw") String keyword);
}
