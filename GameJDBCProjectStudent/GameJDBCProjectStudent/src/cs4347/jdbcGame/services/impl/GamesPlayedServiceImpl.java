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
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import cs4347.jdbcGame.dao.GamesOwnedDAO;
import cs4347.jdbcGame.dao.GamesPlayedDAO;
import cs4347.jdbcGame.dao.impl.GamesOwnedDAOImpl;
import cs4347.jdbcGame.dao.impl.GamesPlayedDAOImpl;
import cs4347.jdbcGame.entity.GamesPlayed;
import cs4347.jdbcGame.services.GamesPlayedService;
import cs4347.jdbcGame.util.DAOException;

public class GamesPlayedServiceImpl implements GamesPlayedService
{
    private DataSource dataSource;
    private GamesPlayedDAO gamesPlayedDAO;
    
    public GamesPlayedServiceImpl(DataSource dataSource)
    {
        this.dataSource = dataSource;
        this.gamesPlayedDAO = new GamesPlayedDAOImpl();
    }

    @Override
    public GamesPlayed create(GamesPlayed gamesPlayed) throws DAOException, SQLException
    {
    	Connection connection = dataSource.getConnection();
        try {
            return gamesPlayedDAO.create(connection, gamesPlayed);
        }
        finally {
            connection.close();
        }
        //return null;
    }

    @Override
    public GamesPlayed retrieveByID(long gamePlayedID) throws DAOException, SQLException
    {
    	Connection connection = dataSource.getConnection();
        try {
            return gamesPlayedDAO.retrieveID(connection, gamePlayedID);
        }
        finally {
            connection.close();
        }
        //return null;
    }

    @Override
    public List<GamesPlayed> retrieveByPlayerGameID(long playerID, long gameID) throws DAOException, SQLException
    {
    	Connection connection = dataSource.getConnection();
        try {
            return gamesPlayedDAO.retrieveByPlayerGameID(connection, playerID, gameID);
        }
        finally {
            connection.close();
        }
        //return null;
    }

    @Override
    public List<GamesPlayed> retrieveByGame(long gameID) throws DAOException, SQLException
    {
    	Connection connection = dataSource.getConnection();
        try {
            return gamesPlayedDAO.retrieveByGame(connection, gameID);
        }
        finally {
            connection.close();
        }
        //return null;
    }

    @Override
    public List<GamesPlayed> retrieveByPlayer(long playerID) throws DAOException, SQLException
    {
    	Connection connection = dataSource.getConnection();
        try {
            return gamesPlayedDAO.retrieveByPlayer(connection, playerID);
        }
        finally {
            connection.close();
        }
        //return null;
    }

    @Override
    public int update(GamesPlayed gamesPlayed) throws DAOException, SQLException
    {
    	Connection connection = dataSource.getConnection();
        try {
            return gamesPlayedDAO.update(connection, gamesPlayed);
        }
        finally {
            connection.close();
        }
    }

    @Override
    public int delete(long gamePlayedID) throws DAOException, SQLException
    {
    	Connection connection = dataSource.getConnection();
        try {
            return gamesPlayedDAO.delete(connection, gamePlayedID);
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
            return gamesPlayedDAO.count(connection);
        }
        finally {
            connection.close();
        }
    }

}
