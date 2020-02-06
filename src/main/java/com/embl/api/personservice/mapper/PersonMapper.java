/**
 * 
 */
package com.embl.api.personservice.mapper;
import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.springframework.util.StringUtils;

import com.embl.api.personservice.domain.Person;
import com.embl.api.personservice.dto.PersonDTO;


/**
 * @author sajjohn
 * 
 * Mapper class responsible for entity to DTO conversion & vice versa,
 * 
 *
 */
@Mapper(componentModel = "spring")
public interface PersonMapper {
	
	/**
	 * @param personDTO
	 */
	@AfterMapping
	public static void populateFullNames( @MappingTarget PersonDTO personDTO ){
		
		String middlename= personDTO.getMiddlename() != null && !StringUtils.isEmpty(personDTO.getMiddlename())?" " + personDTO.getMiddlename():"";
		String fullname = personDTO.getFirstname() + middlename +" " + personDTO.getLastname();
		personDTO.setFullname(fullname);
		
	}
	/**
	 * @param person
	 * @return
	 */
	@Mappings({
	@Mapping(source = "id", target= "personId" )
	})
	PersonDTO toDTO(Person person);
	
	
	/**
	 * @param personDTO
	 * @return
	 */
	Person fromDTO(PersonDTO personDTO);
	
	
	List<PersonDTO> toDTOs(List<Person> persons);
	
	
	/**
	 * @param personDTOs
	 * @return
	 */
	List<Person> fromDTOs(List<PersonDTO> personDTOs);

}
