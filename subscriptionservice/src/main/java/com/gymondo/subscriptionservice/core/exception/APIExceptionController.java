package com.gymondo.subscriptionservice.core.exception;

import java.nio.file.AccessDeniedException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.gymondo.subscriptionservice.core.utils.ValidationUtil;


/**
 * Class meant to handle API exceptions
 */
@ControllerAdvice
public class APIExceptionController{

	/**
	 * Method to handle 404 not found exception
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	protected ResponseEntity<Object> noHandlerFoundException(Exception ex) {
		return new ResponseEntity<> (new RestErrorInfo(HttpStatus.NOT_FOUND, ex.getMessage()), HttpStatus.NOT_FOUND);
	}

	/**
	 * Method to handle json fields validation and bad request exception
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected ResponseEntity<Object> invalidInput(MethodArgumentNotValidException ex) {
		return new ResponseEntity<> (new RestErrorInfo(HttpStatus.BAD_REQUEST, ValidationUtil.getErrorMessages(ex.getBindingResult())), HttpStatus.BAD_REQUEST);
	} 

	/**
	 * Method to handle no content exception
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	protected ResponseEntity<Object> noDataFound(ResourceNotFoundException ex) {
		return new ResponseEntity<> (new RestErrorInfo(HttpStatus.NOT_FOUND, ex.getMessage()), HttpStatus.NOT_FOUND);
	} 

	/**
	 * Method to handle internal server exceptions
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	protected ResponseEntity<Object> internalServerError(Exception ex) {
		return new ResponseEntity<> (new RestErrorInfo(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	} 

	/**
	 * Method to handle incorrect parameter in rest request
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected ResponseEntity<Object> invalidInput(MissingServletRequestParameterException ex) {
		return new ResponseEntity<> (new RestErrorInfo(HttpStatus.BAD_REQUEST, ex.getMessage()), HttpStatus.BAD_REQUEST);
	} 

	/**
	 * Method to handle unsupported media type exception
	 * @param ex
	 * @return
	 */
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	protected ResponseEntity<Object> handleUnsupportedMediaType(HttpMediaTypeNotSupportedException ex) {
		return new ResponseEntity<> (new RestErrorInfo(HttpStatus.NOT_ACCEPTABLE, ex.getMessage()), HttpStatus.NOT_ACCEPTABLE);
	}

	/**
	 * Method to handle invalid method type for rest request
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	protected ResponseEntity<Object> methodNotSupported(HttpRequestMethodNotSupportedException ex) {
		return new ResponseEntity<> (new RestErrorInfo(HttpStatus.METHOD_NOT_ALLOWED, ex.getMessage()), HttpStatus.METHOD_NOT_ALLOWED);
	} 

	/**
	 * Method to handle message not readable exception
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected ResponseEntity<Object> messageNotReadableException(HttpMessageNotReadableException ex) {
		return new ResponseEntity<> (new RestErrorInfo(HttpStatus.BAD_REQUEST, ex.getMessage()), HttpStatus.BAD_REQUEST);
	} 

	/**
	 * Method to handle access denied exception
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	protected ResponseEntity<Object> accessDeniedException(AccessDeniedException ex) {
		return new ResponseEntity<> (new RestErrorInfo(HttpStatus.FORBIDDEN, ex.getMessage()), HttpStatus.FORBIDDEN);
	} 

	/**
	 * Method to handle application layer exceptions
	 * @param ex
	 * @return
	 */
	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(value = {ServiceException.class})
	protected ResponseEntity<Object> serviceException(Exception ex) {
		return new ResponseEntity<> (new RestErrorInfo(HttpStatus.CONFLICT, ex.getMessage()), HttpStatus.CONFLICT);
	}

}
