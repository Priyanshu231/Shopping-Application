package com.bej.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND,reason = "Product is not found")
// Use the@ResponseStatus annotation to set the exception message and status
public class ProductNotFoundException extends  Exception{
}
