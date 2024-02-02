package com.bej.product.service;

import com.bej.product.domain.User;
import com.bej.product.domain.Product;
import com.bej.product.exception.UserAlreadyExistsException;
import com.bej.product.exception.UserNotFoundException;
import com.bej.product.exception.ProductNotFoundException;

import java.util.List;

public interface IUserProductService {
    User registerUser(User user) throws UserAlreadyExistsException;
    User saveUserProductToList(Product product, String userId) throws UserNotFoundException;
    User deleteUserProductFromList(String userId, String productCode) throws UserNotFoundException, ProductNotFoundException;
    List<Product> getAllUserProductsFromList(String userId) throws UserNotFoundException;

}
