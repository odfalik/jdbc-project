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
package cs4347.jdbcGame.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import cs4347.jdbcGame.dao.GamesOwnedDAO;
import cs4347.jdbcGame.entity.GamesOwned;
import cs4347.jdbcGame.util.DAOException;

public class GamesOwnedDAOImpl implements GamesOwnedDAO
{
	private static final String insertSQL = "INSERT INTO gamesOwned (player_id_go, game_id_go, purchase_date, purchase_price) VALUES (?, ?, ?, ?);";

    @Override
    public GamesOwned create(Connection connection, GamesOwned gamesOwned) throws SQLException, DAOException
    {
        if (gamesOwned.getId() != null) {
        	throw new DAOException("Trying to insert GamesOwned with NON-NULL ID");
        }
        
        PreparedStatement ps = null;
        try {
        	ps = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
        	ps.setLong(1, gamesOwned.getPlayerID());
        	ps.setLong(2, gamesOwned.getGameID());
        	ps.setDate(3, new java.sql.Date(gamesOwned.getPurchaseDate().getTime()));
        	ps.setFloat(4, gamesOwned.getPurchasePrice());
        	ps.executeUpdate();
        	
        	//copy assigned ID
        	ResultSet keyRS = ps.getGeneratedKeys();
            keyRS.next();
            int lastKey = keyRS.getInt(1);
            gamesOwned.setId((long) lastKey);
            return gamesOwned;
        }
        finally {
            if (ps != null && !ps.isClosed()) {
                ps.close();
            }
        }
    }

    @Override
    public GamesOwned retrieveID(Connection connection, Long gamesOwnedID) throws SQLException, DAOException
    {
        return null;
    }

    @Override
    public GamesOwned retrievePlayerGameID(Connection connection, Long playerID, Long gameID)
            throws SQLException, DAOException
    {
        return null;
    }

    @Override
    public List<GamesOwned> retrieveByGame(Connection connection, Long gameID) throws SQLException, DAOException
    {
        return null;
    }

    @Override
    public List<GamesOwned> retrieveByPlayer(Connection connection, Long playerID) throws SQLException, DAOException
    {
        return null;
    }

    @Override
    public int update(Connection connection, GamesOwned gamesOwned) throws SQLException, DAOException
    {
        return 0;
    }

    @Override
    public int delete(Connection connection, Long gameOwnedID) throws SQLException, DAOException
    {
        return 0;
    }

    @Override
    public int count(Connection connection) throws SQLException, DAOException
    {
        return 0;
    }

}
