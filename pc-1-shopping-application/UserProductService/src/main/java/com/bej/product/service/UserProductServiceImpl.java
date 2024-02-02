package com.bej.product.service;

import com.bej.product.domain.User;
import com.bej.product.domain.Product;
import com.bej.product.exception.UserAlreadyExistsException;
import com.bej.product.exception.UserNotFoundException;
import com.bej.product.exception.ProductNotFoundException;
import com.bej.product.repository.UserProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserProductServiceImpl implements IUserProductService {

    // Autowire the UserProductRepository using constructor autowiring
    private UserProductRepository userProductRepository;
    @Autowired
    public UserProductServiceImpl(UserProductRepository userProductRepository)
    {
        this.userProductRepository=userProductRepository;
    }
    @Override
    public User registerUser(User user)throws UserAlreadyExistsException  {
        // Register a new user

        if(userProductRepository.findById(user.getUserId()).isPresent())
        {
            throw new UserAlreadyExistsException();
        }
        return userProductRepository.save(user);
    }

    @Override
    public User saveUserProductToList(Product product, String userId)throws UserNotFoundException {
        // Save the product to the User
        if(userProductRepository.findById(userId).isEmpty())
        {
            throw new UserNotFoundException();
        }
        User user1=userProductRepository.findById(userId).get();
        if(user1.getProductList()==null)
        {
            user1.setProductList(Arrays.asList(product));
        }
        else {
            List<Product> list=user1.getProductList();
            list.add(product);
            user1.setProductList(list);
        }
        return userProductRepository.save(user1);
    }

    @Override
    public User deleteUserProductFromList(String userId, String productCode)throws UserNotFoundException  {
        // Delete a product from the user list
        boolean userIdIsPresent = false;
        if(userProductRepository.findById(userId).isEmpty())
        {
            throw new UserNotFoundException();
        }
        User user = userProductRepository.findById(userId).get();
        List<Product> list=user.getProductList();
        userIdIsPresent =list.removeIf(s->s.getProductCode().equals(productCode));
        if(!userIdIsPresent)
        {
            throw new UserNotFoundException();
        }
        user.setProductList(list);
        return userProductRepository.save(user);
    }

    @Override
    public List<Product> getAllUserProductsFromList(String userId)throws UserNotFoundException {
        // Get all products from the User list
        if(userProductRepository.findById(userId).isEmpty())
        {
            throw new UserNotFoundException();
        }
        return userProductRepository.findById(userId).get().getProductList();
    }
}
