package com.bej.product.commander.repository;

import com.bej.product.domain.Product;
import com.bej.product.domain.User;
import com.bej.product.repository.UserProductRepository;
import com.bej.product.service.UserProductServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class UserProductRepositoryTest {
     @Autowired
     private UserProductRepository userProductRepository;
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
         user.setProductList(productList);
     }

     @AfterEach
     public void tearDown()
     {
         userProductRepository.deleteAll();
     }

     @Test
     public void registerUserSuccess()
     {
         userProductRepository.insert(user);
         User u = userProductRepository.findById(user.getUserId()).get();
         assertNotNull(u);
         assertEquals(user.getUserId(),u.getUserId());
     }

     @Test
     public void getUserByEmailSuccess()
     {
         userProductRepository.insert(user);
         User u = userProductRepository.findById(user.getUserId()).get();
         assertNotNull(u);
         assertEquals(user.getUserId(),u.getUserId());
     }
     @Test
     public void getProductsByUserIDSuccess()
     {
         userProductRepository.insert(user);
         User u = userProductRepository.findById(user.getUserId()).get();
         List<Product> list = u.getProductList();
         assertEquals(2,list.size());
     }
     @Test
     public void deleteProductByUserIDSuccess()
     {
         userProductRepository.insert(user);
         User u = userProductRepository.findById(user.getUserId()).get();
         List<Product> list = u.getProductList();
         list.removeIf(r->r.getProductCode().equals("P001"));
         assertEquals(1,list.size());
     }

}
