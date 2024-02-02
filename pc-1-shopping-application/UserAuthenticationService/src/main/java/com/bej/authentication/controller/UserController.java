package com.bej.authentication.controller;

import com.bej.authentication.exception.UserAlreadyExistsException;
import com.bej.authentication.exception.InvalidCredentialsException;
import com.bej.authentication.security.SecurityTokenGenerator;
import com.bej.authentication.service.IUserService;
import com.bej.authentication.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    // Autowire the IUserService, SecurityTokenGenerator using constructor autowiring
    private IUserService iuserService;
    private ResponseEntity responseEntity;
    private SecurityTokenGenerator securityTokenGenerator;
    @Autowired
    public UserController(IUserService iuserService,SecurityTokenGenerator securityTokenGenerator)
    {
        this.iuserService=iuserService;
        this.securityTokenGenerator=securityTokenGenerator;
    }

    @PostMapping("/saveCustomer")
    public ResponseEntity<?> saveCustomer(@RequestBody User user) throws UserAlreadyExistsException {
        // Write the logic to save a user,
        // return 201 status if user is saved else 500 status
        try
        {
            iuserService.saveUser(user);
            responseEntity=new ResponseEntity(user,HttpStatus.CREATED);
        }
        catch(UserAlreadyExistsException exception)
        {
            throw new UserAlreadyExistsException();
        }
        catch(Exception exception)
        {
            responseEntity=new ResponseEntity(iuserService.saveUser(user),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user)throws InvalidCredentialsException
    {
        // Generate the token on login,
        // return 200 status if user is saved else 500 status
        String str = null;
        try {
            User user1 = iuserService.getUserByUserIdAndPassword(user.getUserId(),user.getPassword());
            if (user1.getUserId().equals(user.getUserId())) {
                str = securityTokenGenerator.createToken(user);
            }
            responseEntity = new ResponseEntity(str, HttpStatus.OK);
        }
        catch(InvalidCredentialsException e){
            throw new InvalidCredentialsException();
        }
        catch (Exception e){
            responseEntity = new ResponseEntity("Try after sometime!!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
