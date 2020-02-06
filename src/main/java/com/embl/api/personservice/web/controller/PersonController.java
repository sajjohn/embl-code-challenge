package com.embl.api.personservice.web.controller;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.embl.api.personservice.domain.SortParam;
import com.embl.api.personservice.dto.PersonDTO;
import com.embl.api.personservice.exception.NoEntityFoundException;
import com.embl.api.personservice.service.PersonService;
import com.embl.api.personservice.validator.ValidSortParam;

/**
 * @author sajjohn
 *
 */
@RestController
@Validated
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	
	/**
	 * Method: getPersons Gets list of persons from in Memory H2 DB , has support
	 * for pagination & sorting & allows filtering of results based on certain
	 * parameters
	 * 
	 * @param firstname
	 * @param lastname
	 * @param zipcode
	 * @param pageno
	 * @param sortby
	 * @return List of Persons
	 * @throws Exception
	 */
	@GetMapping("/persons")
	public ResponseEntity<List<PersonDTO>> getPersons(@RequestParam (required = false) 
													String firstname,
													@RequestParam (required = false)
													String lastname,
													@RequestParam (required = false)
													String zipcode,
													@RequestParam (required = false)
													Integer pageno ,
													@ValidSortParam(enumClass = SortParam.class)
													@RequestParam (required = false)
													String sortby
													) throws Exception{
		
		return ResponseEntity.ok(personService.getPersons(firstname, lastname, zipcode, pageno, sortby  ));
	}
	
	
	/**
	 * Method: getPerson(personId) Gets specific person details based on the Id
	 * passed in path variable
	 * @param personId
	 * @return Person
	 * @throws NoEntityFoundException
	 * @throws Exception in case Id passed doesn't exist
	 */
	@GetMapping("/persons/{personId}")
	public ResponseEntity<PersonDTO> getPerson(@PathVariable Long personId) throws NoEntityFoundException, Exception {
		
		return ResponseEntity.ok(personService.getPerson(personId));
	}
	
	
	/**
	 * Method: savePerson Inserts a person in to In memory DB H2.
	 * @param personDTO
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/persons")
	public ResponseEntity<PersonDTO> savePerson(@Valid @RequestBody PersonDTO personDTO) throws Exception{
		
		PersonDTO newPersonDTO = personService.savePerson(personDTO);
		return new ResponseEntity<>(newPersonDTO, HttpStatus.CREATED);
		
	}
	
	/**
	 * Method: updatePerson Updates the person details for the provided Id
	 * @param personId
	 * @param personDTO
	 * @return Person
	 * @throws NoEntityFoundException
	 * @throws Exception in case the ID doesn't exist
	 */
	@PutMapping("/persons/{personId}")
	public ResponseEntity<PersonDTO> updatePerson(@PathVariable Long personId ,@Valid @RequestBody PersonDTO personDTO)
			throws NoEntityFoundException, Exception {
		
		PersonDTO updatedPersonDTO = personService.updatePerson(personId, personDTO);
		
		return ResponseEntity.ok(updatedPersonDTO);
		
	}
	
	/**
	 * Method: deletePerson Deletes a person from the in Memory DB
	 * @param personId
	 * @return
	 * @throws NoEntityFoundException
	 * @throws Exception
	 */
	@DeleteMapping("/persons/{personId}")
	public ResponseEntity deletePerson(@PathVariable Long personId) throws NoEntityFoundException, Exception {
		
		personService.deletePerson(personId);
		
		return new ResponseEntity(HttpStatus.OK);
		
	}
	

}
