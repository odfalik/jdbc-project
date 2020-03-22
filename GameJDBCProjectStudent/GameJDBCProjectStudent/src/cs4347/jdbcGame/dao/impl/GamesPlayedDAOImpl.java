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
import java.util.ArrayList;
import java.util.List;

import cs4347.jdbcGame.dao.GamesPlayedDAO;
import cs4347.jdbcGame.entity.GamesOwned;
import cs4347.jdbcGame.entity.GamesPlayed;
import cs4347.jdbcGame.util.DAOException;

public class GamesPlayedDAOImpl implements GamesPlayedDAO
{

	
	private static final String insertSQL = "INSERT INTO gamesPlayed (player_id_gp, game_id_gp, time_finished, score) VALUES (?, ?, ?, ?);";
			
    @Override
    public GamesPlayed create(Connection connection, GamesPlayed gamesPlayed) throws SQLException, DAOException
    {
    	if (gamesPlayed.getId() != null) {
        	throw new DAOException("Trying to insert GamesPlayed with NON-NULL ID");
        }
    	
    	 PreparedStatement ps = null;
    	 try {
         	ps = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
         	ps.setLong(1, gamesPlayed.getPlayerID());
         	ps.setLong(2, gamesPlayed.getGameID());
         	ps.setDate(3, new java.sql.Date(gamesPlayed.getTimeFinished().getTime()));
         	ps.setInt(4, gamesPlayed.getScore());
         	ps.executeUpdate();
         	
         	ResultSet keyRS = ps.getGeneratedKeys();
            keyRS.next();
            int lastKey = keyRS.getInt(1);
            gamesPlayed.setId((long) lastKey);
            return gamesPlayed;
    	 }
    	 finally
    	 {
    		 if (ps != null && !ps.isClosed()) {
                 ps.close();
             }
    	 }
    }
   
    final static String selectSQL = "SELECT id, player_id_gp, game_id_gp, time_finished, score FROM gamesPlayed where id = ?";
    
    @Override
    public GamesPlayed retrieveID(Connection connection, Long gamePlayedID) throws SQLException, DAOException
    {
    	if (gamePlayedID == null) {
            throw new DAOException("Trying to retrieve GamesPlayed with NULL ID");
        }
    	else
    	{
            PreparedStatement ps = null;
    		try
    		{
    			ps = connection.prepareStatement(selectSQL);
    			ps.setLong(1, gamePlayedID);
    	        ResultSet rs = ps.executeQuery();
    	        if (!rs.next()) {
    	           return null;
    	        }
    	        GamesPlayed gp = extractFromRS(rs);
    	        return gp;
    		}
    		finally {
                if (ps != null && !ps.isClosed()) {
                    ps.close();
                }
            }
    	}
        //return null;
    }

    final static String retrievePGIDSQL = "SELECT * FROM gamesPlayed where player_id_gp = ? AND game_id_gp = ?;";
	
    @Override
    public List<GamesPlayed> retrieveByPlayerGameID(Connection connection, Long playerID, Long gameID)
            throws SQLException, DAOException
    {
    	List<GamesPlayed> result = new ArrayList<GamesPlayed>();
        PreparedStatement ps = null;
        try
        {
        	 ps = connection.prepareStatement(retrievePGIDSQL);
        	 ps.setLong(1, playerID);
             ps.setLong(2, gameID);
             ResultSet rs = ps.executeQuery();
             while (rs.next()) {
            	 GamesPlayed gp = extractFromRS(rs);
                 result.add(gp);
             }
             return result;
        }
        finally {
            if (ps != null && !ps.isClosed()) {
                ps.close();
            }
        }
        //return null;
    }

    final static String retrieveByPlayerIDSQL = "SELECT * FROM gamesPlayed where player_id_gp = ?;";
    @Override
    public List<GamesPlayed> retrieveByPlayer(Connection connection, Long playerID) throws SQLException, DAOException
    {
    	List<GamesPlayed> result = new ArrayList<GamesPlayed>();
        PreparedStatement ps = null;
        try {
        	 ps = connection.prepareStatement(retrieveByPlayerIDSQL);
        	 ps.setLong(1, playerID);
             ResultSet rs = ps.executeQuery();
             
             while (rs.next()) {
            	 GamesPlayed gp = extractFromRS(rs);
                 result.add(gp);
             }
             return result;
        }
        finally {
            if (ps != null && !ps.isClosed()) {
                ps.close();
            }
        }
    }

    final static String retrieveGIDSQL = "SELECT * FROM gamesPlayed where game_id_gp = ?;";
    @Override
    public List<GamesPlayed> retrieveByGame(Connection connection, Long gameID) throws SQLException, DAOException
    {
    	List<GamesPlayed> result = new ArrayList<GamesPlayed>();
        PreparedStatement ps = null;
        try
        {
        	 ps = connection.prepareStatement(retrieveGIDSQL);
             ps.setLong(1, gameID);
             ResultSet rs = ps.executeQuery();
             while (rs.next()) {
            	 GamesPlayed gp = extractFromRS(rs);
                 result.add(gp);
             }
             return result;
        }
        finally {
            if (ps != null && !ps.isClosed()) {
                ps.close();
            }
        }
        
        //return null;
    }
   
    
    final static String updateSQL = "UPDATE gamesPlayed SET player_id_gp = ?, game_id_gp = ?, time_finished = ?, score = ? WHERE id = ?;";
    @Override
    public int update(Connection connection, GamesPlayed gamesPlayed) throws SQLException, DAOException
    {
    	Long id = gamesPlayed.getId();
        if (id == null) {
            throw new DAOException("Trying to update GamesPlayed with NULL ID");
        }

        PreparedStatement ps = null;
        try {
        	ps = connection.prepareStatement(updateSQL);
        	ps.setLong(1, gamesPlayed.getPlayerID());
         	ps.setLong(2, gamesPlayed.getGameID());
         	ps.setDate(3, new java.sql.Date(gamesPlayed.getTimeFinished().getTime()));
         	ps.setInt(4, gamesPlayed.getScore());
        	ps.setLong(5, id);
        	
        	int rows = ps.executeUpdate();
        	return rows;
        
        }
        finally {
            if (ps != null && !ps.isClosed()) {
                ps.close();
            }
        }
        //return 0;
    }

    final static String deleteSQL = "delete from gamesPlayed where id = ?;";
    @Override
    public int delete(Connection connection, Long gamePlayedID) throws SQLException, DAOException
    {
    	if (gamePlayedID == null) {
            throw new DAOException("Trying to delete Game Played with NULL ID");
        }
    	
    	PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(deleteSQL);
            ps.setLong(1, gamePlayedID);
            int rows = ps.executeUpdate();
            return rows;
        }
        finally {
            if (ps != null && !ps.isClosed()) {
                ps.close();
            }
        }
    	//return 0;
    }

    final static String countSQL = "select count(*) from gamesPlayed";
    @Override
    public int count(Connection connection) throws SQLException, DAOException
    {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(countSQL);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new DAOException("No Count Returned");
            }
            int count = rs.getInt(1);
            return count;
        }
        finally {
            if (ps != null && !ps.isClosed()) {
                ps.close();
            }
        }
        //return 0;
    }

    
    private GamesPlayed extractFromRS(ResultSet rs) throws SQLException
    {
        GamesPlayed gamePlayed = new GamesPlayed();
        gamePlayed.setId(rs.getLong("id"));
        gamePlayed.setPlayerID(rs.getLong("player_id_gp"));
        gamePlayed.setGameID(rs.getLong("game_id_gp"));
        gamePlayed.setTimeFinished(rs.getDate("time_finished"));
        gamePlayed.setScore(rs.getInt("score"));
        return gamePlayed;
    }
    
}
    
    

