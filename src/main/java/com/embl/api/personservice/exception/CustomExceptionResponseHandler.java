package com.embl.api.personservice.exception;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author sajjohn 
 * This class serves as Exception handler advise for the
 *         COntroller
 *
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class CustomExceptionResponseHandler extends ResponseEntityExceptionHandler{

	private static final Logger logger = LoggerFactory.getLogger(CustomExceptionResponseHandler.class);
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler#handleNoHandlerFoundException(org.springframework.web.servlet.NoHandlerFoundException, org.springframework.http.HttpHeaders, org.springframework.http.HttpStatus, org.springframework.web.context.request.WebRequest)
	 */
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		String error ="No handler found for "+ ex.getHttpMethod()+" "+ ex.getRequestURL();
		ApiError apiError= new ApiError(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), error, null);
		logger.error("Error occured"+ex.getMessage(),ex);
		return buildResponseEntity(apiError);
	}
	
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		String error = "Incorrect input format";
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error, null);
		logger.error("Error occured"+ex.getMessage(),ex);
		return buildResponseEntity(apiError);
		
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler#handleTypeMismatch(org.springframework.beans.TypeMismatchException, org.springframework.http.HttpHeaders, org.springframework.http.HttpStatus, org.springframework.web.context.request.WebRequest)
	 */
	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		String error = ex.getPropertyName()+ "should be of type "+ ex.getRequiredType();
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error, null);
		logger.error("Error occured"+ex.getMessage(),ex);
		return buildResponseEntity(apiError);
	}
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		 List<String> errors = new ArrayList<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage= error.getDefaultMessage();
			String message = fieldName + "->" + errorMessage + "\n";
			errors.add(message);
		});
		
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "", "", errors);
		logger.error("Error occured"+ex.getMessage(),ex);
		return buildResponseEntity(apiError);
	}
	/**
	 * @param ex NoTickFoundException
	 * @return ResponseEntity of ErrorHandler object
	 */
	@ExceptionHandler(NoEntityFoundException.class)
	protected ResponseEntity<Object> handleNoEntityFound(NoEntityFoundException ex){
		String error=" No Entity fouund with the given Id";
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(), error, null);
		logger.error("Error occured"+ex.getMessage(),ex);
		return buildResponseEntity(apiError);
		
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	protected ResponseEntity<Object> handleUniqueEntitytError(DataIntegrityViolationException ex){
		String error="Entity with the same details already exists";
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), error, null);
		logger.error("Error occured"+ex.getMessage(),ex);
		return buildResponseEntity(apiError);
		
	}
	
	/**
	 * @param ex Exception for Generic Exception
	 * @return Response Entity
	 */
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object>  handleInternalError(Exception ex){
		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), "Internal Error", null);
		logger.error("Error occured"+ex.getMessage(),ex);
		return buildResponseEntity(apiError);
		
	}
	
	
	
	/**
	 * @param apiError
	 * @return Response Entity Error Handler
	 */
	private ResponseEntity<Object> buildResponseEntity(ApiError apiError){
		return new ResponseEntity<>(apiError, apiError.getHttpStatus());
	}
}
