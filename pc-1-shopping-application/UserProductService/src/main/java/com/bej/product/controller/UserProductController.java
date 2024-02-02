package com.bej.product.controller;

import com.bej.product.domain.User;
import com.bej.product.domain.Product;
import com.bej.product.exception.UserAlreadyExistsException;
import com.bej.product.exception.UserNotFoundException;
import com.bej.product.exception.ProductNotFoundException;
import com.bej.product.service.IUserProductService;
import com.bej.product.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1")
public class UserProductController {
    // Autowire IUserProductService using constructor autowiring
    private IUserProductService iUserProductService;
    private ResponseEntity<?> responseEntity;
    @Autowired
    public UserProductController(IUserProductService iUserProductService)
    {
        this.iUserProductService=iUserProductService;
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerCustomer(@RequestBody User user)throws UserAlreadyExistsException {
        // Register a new user and save to db,
        // return 201 status if user is saved else 500 status
        try
        {
            iUserProductService.registerUser(user);
            responseEntity=new ResponseEntity(user,HttpStatus.CREATED);
        }
        catch(UserAlreadyExistsException exception)
        {
            throw new UserAlreadyExistsException();
        }
        catch(Exception exception)
        {
            responseEntity=new ResponseEntity(iUserProductService.registerUser(user),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PostMapping("/customer/saveProduct")
    public ResponseEntity<?> saveCustomerProductToList(@RequestBody Product product, HttpServletRequest request)throws UserNotFoundException {
        // add a product to a specific customer,
        // return 201 status if track is saved else 500 status
        try {
            System.out.println("header" +request.getHeader("Authorization"));
            Claims claims = (Claims) request.getAttribute("claims");
            System.out.println("userId from claims :: " + claims.getSubject());
            String userId = claims.getSubject();
            System.out.println("userId :: "+userId);
            responseEntity = new ResponseEntity<>(iUserProductService.saveUserProductToList(product, userId), HttpStatus.CREATED);
        }
        catch (UserNotFoundException e)
        {
            throw new UserNotFoundException();
        }
        catch (Exception exception)
        {
            responseEntity=new ResponseEntity<>(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;

    }

    @GetMapping("/customer/getAllProducts")
    public ResponseEntity<?> getAllCustomerProductsFromList(HttpServletRequest request)throws UserNotFoundException  {
        // list all products of a specific customer,
        // return 200 status if track is saved else 500 status
        try {
            System.out.println("header" +request.getHeader("Authorization"));
            Claims claims = (Claims) request.getAttribute("claims");
            System.out.println("productId from claims :: " + claims.getSubject());
            String productId = claims.getSubject();
            System.out.println("productId :: "+productId);
            responseEntity = new ResponseEntity<>(iUserProductService.getAllUserProductsFromList(productId), HttpStatus.OK);
        }
        catch (UserNotFoundException e)
        {
            throw new UserNotFoundException();
        }
        catch (Exception exception)
        {
            responseEntity=new ResponseEntity<>(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @DeleteMapping("/customer/{productCode}")
    public ResponseEntity<?> deleteCustomerProductFromList(@PathVariable String productCode,HttpServletRequest request)throws UserNotFoundException {
        // delete product of a specific customer,
        // return 200 status if track is saved else 500 status
        Claims claims = (Claims) request.getAttribute("claims");
        System.out.println("userId from claims :: " + claims.getSubject());
        String userId = claims.getSubject();
        System.out.println("userId :: "+userId);
        try {
            responseEntity = new ResponseEntity<>(iUserProductService.deleteUserProductFromList(productCode,userId), HttpStatus.OK);
        }
        catch (UserNotFoundException exception) {
            throw new UserNotFoundException();
        }
        catch (Exception exception)
        {
            responseEntity=new ResponseEntity<>(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    // Read the customer id present in the claims from the request
//    private String getCustomerIdFromClaims(HttpServletRequest request){
//       return null;
//    }
}
