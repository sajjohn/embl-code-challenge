package com.embl.api.personservice.repository;


import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.embl.api.personservice.domain.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

	/**
	 * Method: findByFirstnameAndLastnameAndZipcode Filter based on first name ,
	 * last name & zip code
	 * 
	 * @param firstname
	 * @param lastname
	 * @param zipcode
	 * @return
	 */
	List<Person> findByFirstnameAndLastnameAndZipcode(String firstname, String lastname , String zipcode);

	/**
	 * Method: findByFirstnameAndLastnameAndZipcode filters based on first & last
	 * name
	 * @param firstname
	 * @param lastname
	 * @return
	 */
	List<Person> findByFirstnameAndLastname(String firstname, String lastname);
	
	/**
	 * Method: findByFirstnameAndZipcode filters based on first name & Zip code
	 * @param firstname
	 * @param zipcode
	 * @return
	 */
	List<Person> findByFirstnameAndZipcode(String firstname, String zipcode);
	
	/**
	 * Method: findByFirstname Filters based on first name
	 * @param firstname
	 * @return
	 */
	List<Person> findByFirstname(String firstname);
	
	
	
	
	
}
