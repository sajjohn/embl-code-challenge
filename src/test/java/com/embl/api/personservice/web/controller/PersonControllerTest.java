package com.embl.api.personservice.web.controller;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.embl.api.personservice.EmblPersonServiceApplication;
import com.embl.api.personservice.domain.Gender;
import com.embl.api.personservice.dto.PersonDTO;

import junit.framework.Assert;



@SpringBootTest(classes = EmblPersonServiceApplication.class,
webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonControllerTest {
	
	@LocalServerPort
	private int port;
	
	
	private TestRestTemplate testRestTemplate = new TestRestTemplate();
	
	
	private PersonDTO mockPersonDTO() {
		
		PersonDTO personDTO = new PersonDTO();
		personDTO.setFirstname("John");
		personDTO.setLastname("Doe");
		personDTO.setDateofbirth(new Date("01/01/2000"));
		personDTO.setAddress("ShakeSphere Square, Oxford circle London");
		personDTO.setZipcode("BAX87Y");
		personDTO.setGender(Gender.MALE);
		personDTO.setMiddlename("");
		personDTO.setEmail("test123@testmail.com");
		
		return personDTO;
		
	}
	
	
	private PersonDTO mockUpdatePersonDTO() {
		
		PersonDTO personDTO = new PersonDTO();
		personDTO.setFirstname("John");
		personDTO.setLastname("Doe");
		personDTO.setDateofbirth(new Date("01/01/2000"));
		personDTO.setAddress("Sherlock Holmes Square, Oxford circle London");
		personDTO.setZipcode("BAX87Y");
		personDTO.setGender(Gender.MALE);
		personDTO.setMiddlename("");
		personDTO.setEmail("test123@testmail.com");
		
		return personDTO;
		
	}
	
	@Test
	public void testsavePerson() {
		
		String url = "http://localhost:" + port + "/emblservice/persons";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		PersonDTO personDTO = mockPersonDTO();
		HttpEntity<PersonDTO> entity = new HttpEntity<>(personDTO, headers);
		
		ResponseEntity<PersonDTO> responseEntity = testRestTemplate.exchange(url, HttpMethod.POST, entity, PersonDTO.class);
		Assert.assertEquals(201, responseEntity.getStatusCodeValue());
		Assert.assertEquals(Long.valueOf(1), responseEntity.getBody().getPersonId());
		Assert.assertEquals("John Doe", responseEntity.getBody().getFullname());
	}
	
	@Test
	public void testgetPersons() {
		
		String url = "http://localhost:" + port + "/emblservice/persons";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity entity = new HttpEntity<>(null, headers);
		ResponseEntity<PersonDTO[]> responseEntity = testRestTemplate.exchange(url, HttpMethod.GET, entity, PersonDTO[].class);
		Assert.assertEquals(200, responseEntity.getStatusCodeValue());
		Assert.assertEquals("John", responseEntity.getBody()[0].getFirstname());
		Assert.assertEquals("Doe", responseEntity.getBody()[0].getLastname());
	}
	
	@Test
	public void testgetPersonbyId() {
		
		String url = "http://localhost:" + port + "/emblservice/persons/1";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		
		
		HttpEntity entity = new HttpEntity<>(null, headers);
		ResponseEntity<PersonDTO> responseEntity = testRestTemplate.exchange(url, HttpMethod.GET, entity, PersonDTO.class);
		Assert.assertEquals(200, responseEntity.getStatusCodeValue());
		Assert.assertEquals("John", responseEntity.getBody().getFirstname());
		Assert.assertEquals("Doe", responseEntity.getBody().getLastname());
	}
	
	
	@Test
	public void testUpdatePersonbyId() {
		
		String url = "http://localhost:" + port + "/emblservice/persons/1";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		PersonDTO personDTO = mockUpdatePersonDTO();
		HttpEntity<PersonDTO> entity = new HttpEntity<>(personDTO, headers);
		ResponseEntity<PersonDTO> responseEntity = testRestTemplate.exchange(url, HttpMethod.PUT, entity, PersonDTO.class);
		System.out.println("Updated Id"+responseEntity.getBody().getPersonId());
		Assert.assertEquals(200, responseEntity.getStatusCodeValue());
		Assert.assertEquals("Sherlock Holmes Square, Oxford circle London", responseEntity.getBody().getAddress());
		
	}
	
	
	//@Test
	public void testDeletePersonbyId() {
		
		String url = "http://localhost:" + port + "/emblservice/persons/1";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity entity = new HttpEntity<>(null, headers);
		ResponseEntity<PersonDTO> responseEntity = testRestTemplate.exchange(url, HttpMethod.GET, entity, PersonDTO.class);
		Assert.assertEquals(200, responseEntity.getStatusCodeValue());
		Assert.assertEquals("John", responseEntity.getBody().getFirstname());
		
		
	}
	
	
	

}
