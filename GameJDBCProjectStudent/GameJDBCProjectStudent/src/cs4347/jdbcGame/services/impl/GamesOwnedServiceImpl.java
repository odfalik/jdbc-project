/* NOTICE: All materials provided by this project, and materials derived 
 * from the project, are the property of the University of Texas. 
 * Project materials, or those derived from the materials, cannot be placed 
 * into publicly accessible locations on the web. Project materials cannot 
 * be shared with other project teams. Making project materials publicly 
 * accessible, or sharing with other project teams will result in the 
 * failure of the team responsible and any team that uses the shared materials. 
 * Sharing project materials or using shared materials will also result 
 * in the reporting of all team members for academic dishonesty. 
 */
package cs4347.jdbcGame.services.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import cs4347.jdbcGame.dao.GamesOwnedDAO;
import cs4347.jdbcGame.dao.impl.GamesOwnedDAOImpl;
import cs4347.jdbcGame.entity.GamesOwned;
import cs4347.jdbcGame.services.GamesOwnedService;
import cs4347.jdbcGame.util.DAOException;

public class GamesOwnedServiceImpl implements GamesOwnedService
{
    private DataSource dataSource;
    private GamesOwnedDAO gamesOwnedDAO;

    public GamesOwnedServiceImpl(DataSource dataSource)
    {
        this.dataSource = dataSource;
        this.gamesOwnedDAO = new GamesOwnedDAOImpl();
    }

    @Override
    public GamesOwned create(GamesOwned gamesOwned) throws DAOException, SQLException
    {
    	Connection connection = dataSource.getConnection();
        try {
            return gamesOwnedDAO.create(connection, gamesOwned);
        }
        finally {
            connection.close();
        }
    }

    @Override
    public GamesOwned retrieveByID(long gamesOwnedID) throws DAOException, SQLException
    {
    	Connection connection = dataSource.getConnection();
        try {
            return gamesOwnedDAO.retrieveID(connection, gamesOwnedID);
        }
        finally {
            connection.close();
        }
    }

    @Override
    public GamesOwned retrievePlayerGameID(long playerID, long gameID) throws DAOException, SQLException
    {
    	Connection connection = dataSource.getConnection();
        try {
            return gamesOwnedDAO.retrievePlayerGameID(connection, playerID, gameID);
        }
        finally {
            connection.close();
        }
    }

    @Override
    public List<GamesOwned> retrieveByGame(long gameID) throws DAOException, SQLException
    {
    	Connection connection = dataSource.getConnection();
        try {
            return gamesOwnedDAO.retrieveByGame(connection, gameID);
        }
        finally {
            connection.close();
        }
    }

    @Override
    public List<GamesOwned> retrieveByPlayer(long playerID) throws DAOException, SQLException
    {
    	Connection connection = dataSource.getConnection();
        try {
            return gamesOwnedDAO.retrieveByPlayer(connection, playerID);
        }
        finally {
            connection.close();
        }
    }

    @Override
    public int update(GamesOwned gamesOwned) throws DAOException, SQLException
    {
    	Connection connection = dataSource.getConnection();
        try {
            return gamesOwnedDAO.update(connection, gamesOwned);
        }
        finally {
            connection.close();
        }
    }

    @Override
    public int delete(long gameOwnedID) throws DAOException, SQLException
    {
    	Connection connection = dataSource.getConnection();
        try {
            return gamesOwnedDAO.delete(connection, gameOwnedID);
        }
        finally {
            connection.close();
        }
    }

    @Override
    public int count() throws DAOException, SQLException
    {
    	Connection connection = dataSource.getConnection();
        try {
            return gamesOwnedDAO.count(connection);
        }
        finally {
            connection.close();
        }
    }

}
