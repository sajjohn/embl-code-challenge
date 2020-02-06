package com.embl.api.personservice.dto;
import java.util.Date;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import com.embl.api.personservice.domain.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sajjohn
 * 
 * This class uses the Bean validator framework to validate the incoming payload
 * & provide user friendly messages through overriding the default exception handling
 * behaviour of Spring
 */
@Data @NoArgsConstructor
public class PersonDTO {
	
	
	private Long personId;
	
	@NotNull(message = "firstname cannot be null")
	@NotBlank(message = "firstname is mandatory")
	@Size(min = 3, max = 20, message = "firstname should contain between 3-20 characters")
	@Pattern(regexp = "^[a-zA-Z]*$", message = "Invalid firstname")
	private String firstname;
	
	@NotNull(message = "lastname cannot be null")
	@NotBlank(message = "lastname is mandatory")
	@Size(min = 3, max = 20, message = "lastname should contain between 3-20 characters")
	@Pattern(regexp = "^[a-zA-Z]*$", message = "Invalid lastname")
	private String lastname;

	@Size(min = 0, max = 20, message =
	  "Middlename should contain between 0-20 characters")
	@Pattern(regexp = "^[a-zA-Z]*$", message = "Invalid middlename")
	private String middlename;
	
	private Gender gender;
		
	@NotNull(message = "dateofbirth cannot be null")
	@Past(message = "Date should be a past date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date dateofbirth;

	private String address;

	private String zipcode;
	
	@NotNull(message = "Email cannot be null")
	@NotBlank(message = "Email is mandatory")
	@Email(message = "Invalid Email")
	private String email;
	
	private String fullname;
}
