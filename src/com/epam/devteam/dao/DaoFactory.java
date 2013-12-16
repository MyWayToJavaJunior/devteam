/**
 * 
 */
package com.epam.devteam.dao;

import com.epam.devteam.dao.impl.PostgresqlDaoFactory;
import com.epam.devteam.db.ConnectionPool;

/**
 * @date Dec 15, 2013 	
 * @author Andrey Kovalskiy
 *
 */
public abstract class DaoFactory {
	
	public abstract void setConnectionPool(ConnectionPool connectionPool);
	
	public abstract UserDao takeUserDao() throws DaoException;
	
	public static DaoFactory takeDaoFactory(DaoFactoryTypes daoFactoryTypes) throws DaoException{
		DaoFactory daoFactory = null;
		switch(daoFactoryTypes){
		case POSTGRESQL:
			daoFactory = new PostgresqlDaoFactory();
			break;
		default:
			System.out.println("Not available dao factory type!");
			throw new DaoException();
		}
		return daoFactory;
	}
		
}
