package com.bej.product.commander.service;

import com.bej.product.domain.Product;
import com.bej.product.domain.User;
import com.bej.product.exception.UserAlreadyExistsException;
import com.bej.product.exception.UserNotFoundException;
import com.bej.product.repository.UserProductRepository;
import com.bej.product.service.UserProductServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class UserProductServiceTest {
    @Mock
    private UserProductRepository userProductRepository;
    @InjectMocks
    private UserProductServiceImpl userProductService;
    private Product product1,product2;
    private User user;
    List<Product> productList;
    @BeforeEach
    public void setUp(){

        product1 = new Product();
        product1.setProductCode("P001");
        product1.setProductName("Furniture");
        product1.setNoOfProducts(3);
        product1.setSpecifications("Garden Chair 31 X 21 X 14");
        product1.setStock(true);
        product1.setPrice(4000);
        product1.setDiscountedPrice(3500);

        product2 = new Product();
        product2.setProductCode("P002");
        product2.setProductName("Tea Table");
        product2.setNoOfProducts(3);
        product2.setSpecifications("Table 31 X 21 X 14");
        product2.setStock(true);
        product2.setPrice(4000);
        product2.setDiscountedPrice(3500);

        productList = Arrays.asList(product1,product2);

        user = new User();
        user.setUserId("S123");
        user.setUserEmail("sam@gmail.com");
        user.setUserName("Sammy");
        user.setUserPassword("1234");
        //user.setProductList(productList);
    }

    @AfterEach
    public void tearDown()
    {
     user = null;
     product1 = null;
     product2 = null;
     productList = null;
    }

    @Test
    public void givenUserToSaveReturnSavedUserSuccess() throws UserAlreadyExistsException {
        when(userProductRepository.findById(any())).thenReturn(Optional.ofNullable(null));
        when(userProductRepository.save(any())).thenReturn(user);
        assertEquals(user,userProductService.registerUser(user));
        verify(userProductRepository,times(1)).findById(any());
        verify(userProductRepository,times(1)).save(any());
    }
    @Test
    public void givenUserToSaveReturnSavedUserFailure() throws UserAlreadyExistsException {
        when(userProductRepository.findById(any())).thenReturn(Optional.ofNullable(user));
        assertThrows(UserAlreadyExistsException.class,()->userProductService.registerUser(user));
        verify(userProductRepository,times(1)).findById(any());
        verify(userProductRepository,times(0)).save(any());

   }
   @Test
    public void givenProductSaveUserProductToList() throws UserNotFoundException {
       User u  = new User();
       u.setUserId("S123");
       u.setUserEmail("sam@gmail.com");
       u.setUserName("Sammy");
       u.setUserPassword("1234");
       u.setProductList(List.of(product1));
       when(userProductRepository.findById(user.getUserId())).thenReturn(Optional.ofNullable(user));
       when(userProductRepository.save(any())).thenReturn(user);
       System.out.println(user);
       User us = userProductService.saveUserProductToList(product1, user.getUserId());
       System.out.println(us);
       assertEquals(u.getProductList().size(),us.getProductList().size());
       verify(userProductRepository,times(2)).findById(any());
       verify(userProductRepository,times(1)).save(any());
   }
}
