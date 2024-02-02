package com.bej.authentication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND,reason = "No user found")
// Use the@ResponseStatus annotation to set the exception message and status
public class InvalidCredentialsException extends Exception{
}
