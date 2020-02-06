package com.embl.api.personservice.domain;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data 
@NoArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class Person {
	
	@GeneratedValue
	@Id
	private Long id;
	private String firstname;
	private String lastname;
	private String middlename;
	private String gender;
	private Date dateofbirth;
	private String address;
	private String zipcode;
	private String email;
	
	
	
	

}
