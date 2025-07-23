package com.ecom.model;

public enum UserType {
    SELLER,
    BUYER
}
/*
To clearly distinguish between seller and buyer users. This helps your application:
Apply different rules or logic for each user type
Control access and UI
Store and retrieve this info safely from the database
 */