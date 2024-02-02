package com.bej.product.repository;
import com.bej.product.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserProductRepository extends MongoRepository<User, String> {

}
