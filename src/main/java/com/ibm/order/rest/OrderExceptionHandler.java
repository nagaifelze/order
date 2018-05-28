package com.ibm.order.rest;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ibm.order.model.ErrorInfo;
import com.ibm.order.model.OrderResponse;
import com.ibm.order.service.OrderValidationException;
@ControllerAdvice
public class OrderExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler({ OrderValidationException.class })
    public ResponseEntity<Object> handleOrderException(OrderValidationException ex, WebRequest request) {
		OrderResponse bodyOfResponse = new OrderResponse();
		bodyOfResponse.setResult("failure");
		bodyOfResponse.setError(ex.getErrorInfo().getName());
		bodyOfResponse.setErrorMessage(ex.getErrorInfo().getErrorMsg());
		
        return handleExceptionInternal(ex, bodyOfResponse, 
          new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
        }
	
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		OrderResponse bodyOfResponse = new OrderResponse();
		bodyOfResponse.setResult("failure");
		bodyOfResponse.setError(ErrorInfo.Invalid_Order.getName());
		StringBuilder errMsg = new StringBuilder();
		List<FieldError> fieldErrors  = ex.getBindingResult().getFieldErrors();
		fieldErrors.stream().forEach(
				error -> {
					if("orderitems".equalsIgnoreCase(error.getField())) {
						errMsg.append(ErrorInfo.Invalid_Order_Item_List.getErrorMsg());
					}else if("item".equalsIgnoreCase(error.getField())) {
						errMsg.append(ErrorInfo.Invalid_Order_Item_Entry.getErrorMsg());
					}else {
						errMsg.append(error.getField() + ":" + error.getDefaultMessage() + ";");
					}
				}
				);
		bodyOfResponse.setErrorMessage(errMsg.toString());
		
		return handleExceptionInternal(ex, bodyOfResponse, headers, status, request);
	}
}
