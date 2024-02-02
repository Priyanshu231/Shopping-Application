package com.bej.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.CONFLICT,reason = "User Already exists")
// Use the@ResponseStatus annotation to set the exception message and status
public class UserAlreadyExistsException extends  Exception{
}
