package com.embl.api.personservice.service.impl;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.embl.api.personservice.domain.Person;
import com.embl.api.personservice.dto.PersonDTO;
import com.embl.api.personservice.exception.NoEntityFoundException;
import com.embl.api.personservice.mapper.PersonMapper;
import com.embl.api.personservice.repository.PersonRepository;
import com.embl.api.personservice.service.PersonService;



@Service
public class PersonServiceImpl implements PersonService{
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private PersonMapper personMapper;
	
	/**
	 *
	 */
	@Override
	public List<PersonDTO> getPersons(String firstname, String lastname, String zipcode, Integer pageno, String sortby) throws Exception {
		// TODO Auto-generated method stub
		List<Person> persons = null;
		
		if(pageno == null) {
			pageno = 0;
		}
		
		if(!StringUtils.isEmpty(firstname) && !StringUtils.isEmpty(lastname) 
			&& !StringUtils.isEmpty(zipcode))
		{
			persons = personRepository.findByFirstnameAndLastnameAndZipcode(firstname, lastname, zipcode);
		}
		else if (!StringUtils.isEmpty(firstname) && !StringUtils.isEmpty(lastname)) 
		{
			persons = personRepository.findByFirstnameAndLastname(firstname, lastname);
		}
		else if (!StringUtils.isEmpty(firstname) && !StringUtils.isEmpty(zipcode)) 
		{
			persons = personRepository.findByFirstnameAndZipcode(firstname, zipcode);
		}
		else if (!StringUtils.isEmpty(firstname))
		{
			persons = personRepository.findByFirstname(firstname);
		}
		else 
		{	
			if(!StringUtils.isEmpty(sortby)) {
			persons = personRepository.findAll(PageRequest.
									   of(pageno, 5, Sort.by(sortby))).toList();
			}
			else {
				
			persons = personRepository.findAll(PageRequest.
						   of(pageno, 5 )).toList();
			}
			
		}
		

		return personMapper.toDTOs(persons);

	}
	
	/**
	 *
	 */
	@Override
	public PersonDTO getPerson(Long personId) throws NoEntityFoundException, Exception {
		// TODO Auto-generated method stub
		
		if(!personRepository.existsById(personId)) {
			throw new NoEntityFoundException("No Person exists by the given Id");
		}
		
		Person person = personRepository.findById(personId).get();
		
		return personMapper.toDTO(person);
	}
	
	/**
	 *
	 */
	@Transactional(rollbackOn = Exception.class)
	public PersonDTO savePerson(PersonDTO personDTO) throws Exception {
		
		Person person = personMapper.fromDTO(personDTO);
		return personMapper.toDTO(personRepository.save(person));
	}
	
	/**
	 *
	 */
	@Override
	@Transactional(rollbackOn = Exception.class)
	public PersonDTO updatePerson(Long personId, PersonDTO personDTO) throws NoEntityFoundException, Exception {
		// TODO Auto-generated method stub
		
		if(!personRepository.existsById(personId)) {
			throw new NoEntityFoundException("No Person exists by the given Id");
		}
		
		
		Person person = personMapper.fromDTO(personDTO);
		person.setId(personId);
		return personMapper.toDTO(personRepository.save(person));
	}
	
	@Transactional(rollbackOn = Exception.class)
	public void deletePerson(Long personId) throws NoEntityFoundException, Exception {
		
		if(!personRepository.existsById(personId)) {
			throw new NoEntityFoundException("No Person exists by the given Id");
		}
		
		personRepository.deleteById(personId);
		
	}

}
