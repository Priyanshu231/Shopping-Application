package com.bej.product.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Document
public class User {
    @Id
    private String userId;
    private String userPassword;
    private String userName;
    private String userEmail;
    private List<Product> productList;

    public User() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userPassword=" + userPassword +
                ", userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", productList=" + productList +
                '}';
    }
}
