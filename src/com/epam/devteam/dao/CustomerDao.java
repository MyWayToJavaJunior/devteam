package com.epam.devteam.dao;

import com.epam.devteam.entity.Customer;

public abstract class CustomerDao extends AbstractDao<Customer> {

    public abstract Customer find(String email, String password) throws DaoException;
    
}
