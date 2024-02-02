package com.bej.product.commander.controller;

import com.bej.product.controller.UserProductController;
import com.bej.product.domain.Product;
import com.bej.product.domain.User;
import com.bej.product.exception.ProductNotFoundException;
import com.bej.product.exception.UserAlreadyExistsException;
import com.bej.product.exception.UserNotFoundException;
import com.bej.product.service.UserProductServiceImpl;
import com.bej.product.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@AutoConfigureMockMvc(addFilters = false)
public class UserProductControllerTest {

 @Autowired
     private MockMvc mockMvc;
@Autowired
    private JwtUtil jwtUtil;
 @Mock
     private UserProductServiceImpl userProductService;
     private Product product1,product2;
     private User user;
     List<Product> productList;

 @InjectMocks
     private UserProductController userProductController;



     @BeforeEach
     public void setUp(){

         mockMvc = MockMvcBuilders.standaloneSetup(userProductController).build();
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

     }

     @AfterEach
     public void tearDown()
     {
         product1 = null;
         product2 = null;
         productList = null;
         user=null;
     }
     @Test
     public void registerUserSuccess() throws Exception {
         when(userProductService.registerUser(any())).thenReturn(user);
         mockMvc.perform(post("/api/v1/register")
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(asJSONString(user)))
                 .andExpect(status().isCreated())
                 .andDo(MockMvcResultHandlers.print());
     }

     @Test
     public void registerUserFailure() throws Exception {
       when(userProductService.registerUser(any())).thenThrow(UserAlreadyExistsException.class);
         mockMvc.perform(post("/api/v1/register")
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(asJSONString(user)))
                 .andExpect(status().isConflict())
                 .andDo(MockMvcResultHandlers.print());
     }


     private static String asJSONString(Object user) {
         try {
             return new ObjectMapper().writeValueAsString(user);
         } catch (Exception e) {
             throw new RuntimeException(e);
         }
     }
 }

