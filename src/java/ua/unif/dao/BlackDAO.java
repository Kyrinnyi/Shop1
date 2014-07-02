package ua.unif.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import ua.unif.dao.entity.Client;

/*
dao for black table
*/
public class BlackDAO {
    private final static Logger log = Logger.getLogger(BlackDAO.class);
    private final Connection connection;
    
    public BlackDAO (Connection c){
        connection = c;
    }
    
    //cheacks if client is in black list by client id
    public boolean isBlack(int id) throws SQLException{
        PreparedStatement ps = connection.prepareStatement("SELECT id FROM "
                + "black WHERE client_id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        log.info(ps.toString());
        if (rs != null){
            if(rs.next()){
                return true;
            }           
        }
        return false;
    }
    
    //cheacks if client is in black list by client object
    public boolean isBlack(Client client) throws SQLException{
        PreparedStatement ps = connection.prepareStatement("SELECT id FROM "
                + "black WHERE client_id = ?");
        ps.setInt(1, client.getId());
        ResultSet rs = ps.executeQuery();
        log.info(ps.toString());
        if (rs != null){
            if(rs.next()){
                return true;
            }           
        }
        return false;
    } 
    
    //put client to black list by id
    public void setBlack(int id) throws SQLException{ 
        PreparedStatement ps = connection.prepareStatement("INSERT INTO "
                + "black (client_id) VALUES (?)");
        ps.setInt(1, id);
        ps.execute();
        log.info(ps.toString());
    }
    
    //put client to black list by client object
    public void setBlack(Client client) throws SQLException{
        PreparedStatement ps = connection.prepareStatement("INSERT INTO "
                + "black (client_id) VALUES (?)");
        ps.setInt(1, client.getId());
        ps.execute();
        log.info(ps.toString());
    }   
}
