/**
 * 
 */
package com.embl.api.personservice.validator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author sajjohn
 * 
 *         Validation constraint logic to implement parameter validation based
 *         on Enum contents
 */
public class SortParamValidator implements ConstraintValidator<ValidSortParam, CharSequence> {
	
	 private List<String> acceptedValues;
	
	/**
	 *
	 */
	@Override
    public void initialize(ValidSortParam annotation) {
        acceptedValues = Stream.of(annotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
 
    /**
     *
     */
    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
 
        return acceptedValues.contains(value.toString());
    }
    
}
