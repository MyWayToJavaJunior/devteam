package com.epam.devteam.dao;

import com.epam.devteam.entity.response.Feedback;

public interface FeedbackDao extends Dao<Feedback> {

    abstract Feedback findByOrderId(int id) throws DaoException;

}
