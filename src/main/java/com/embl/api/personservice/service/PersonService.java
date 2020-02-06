/**
 * 
 */
package com.embl.api.personservice.service;

import java.util.List;

import com.embl.api.personservice.domain.Person;
import com.embl.api.personservice.dto.PersonDTO;
import com.embl.api.personservice.exception.NoEntityFoundException;

/**
 * @author sajjohn
 *
 */
public interface PersonService {
	
	/**
	 * Method: getPersons FIlters the records based on the parameters passed in case
	 * no parameters are passed then enables pagination support & also provides
	 * sorting support based on a set of parameters
	 * 
	 * @param firstname
	 * @param lastname
	 * @param zipcode
	 * @param pageno
	 * @param sortby
	 * @return
	 * @throws Exception
	 */
	List<PersonDTO> getPersons(String firstname, String lastname, String zipcode, Integer pageno,String sortby) throws Exception;
	
	/**
	 * @param personId
	 * @return
	 * @throws NoEntityFoundException
	 * @throws Exception
	 */
	PersonDTO getPerson(Long personId) throws NoEntityFoundException, Exception;
	
	/**
	 * @param personDTO
	 * @return
	 * @throws Exception
	 */
	PersonDTO savePerson(PersonDTO personDTO) throws Exception;
	
	/**
	 * @param personId
	 * @param personDTO
	 * @return
	 * @throws NoEntityFoundException
	 * @throws Exception
	 */
	PersonDTO updatePerson(Long personId, PersonDTO personDTO) throws NoEntityFoundException, Exception;
	
	/**
	 * @param personId
	 * @throws NoEntityFoundException
	 * @throws Exception
	 */
	void deletePerson(Long personId) throws NoEntityFoundException, Exception;

}
