package ua.unif.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import ua.unif.dao.entity.Client;

/*
dao for clients table
*/
public class ClientDAO {
    private final static Logger log = Logger.getLogger(ClientDAO.class);
    private final Connection connection;
    
    public ClientDAO (Connection c){
        connection = c;
    }
    
    //gets client from clients table
    //distinguish it by mail
    //or if it not exist creates new client in database
    public Client getClient(Client c) throws SQLException{
        //tries to get client by mail
        PreparedStatement ps = connection.prepareStatement("SELECT id FROM "
                + "clients WHERE mail = ?");
        ps.setString(1, c.getMail());
        ResultSet rs = ps.executeQuery();
        log.info(ps.toString());
        if (rs != null){
            if(rs.next()){
                //if client exist cheaks if it is on black list 
                BlackDAO bd = DAOFactory.getBlackDAO();
                c.setId(rs.getInt(1));
                c.setBlack(bd.isBlack(c.getId()));
                //Update bd with last info
                ps = connection.prepareStatement("UPDATE clients "
                + "SET name = ?, surename = ?, phone = ?, addres = ? WHERE id = ?");
                ps.setString(1, c.getName());
                ps.setString(2, c.getSurename());
                ps.setString(3, c.getPhone());
                ps.setString(4, c.getAddres());
                ps.setInt(5, c.getId());
                ps.execute();
                log.info(ps.toString());
            }           
        } 
        //if there is no client in db
        //creats new client 
        if (c.getId() == 0){
            ps = connection.prepareStatement("INSERT INTO "
                + "clients (name, surename, phone, mail, addres) "
                + "VALUES (?,?,?,?,?)");
            ps.setString(1, c.getName());
            ps.setString(2, c.getSurename());
            ps.setString(3, c.getPhone());
            ps.setString(4, c.getMail());
            ps.setString(5, c.getAddres());
            ps.execute();  
            log.info(ps.toString());
            c.setBlack(false);       
            
            //get client id
            ps = connection.prepareStatement("SELECT id FROM "
                + "clients WHERE mail = ?");
            ps.setString(1, c.getMail());
            rs = ps.executeQuery();
            log.info(ps.toString());
            if (rs != null){
                if(rs.next()){
                    c.setId(rs.getInt(1));               
                }           
            } 
        }    
        return c;
    }
    
    //gets client by id
    public Client getClient(int id) throws SQLException{
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM "
                + "clients WHERE id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        log.info(ps.toString());
        Client client = null;
        if (rs != null){
            if(rs.next()){
                client = new Client();
                client.setId(id);
                BlackDAO bd = DAOFactory.getBlackDAO();
                client.setBlack(bd.isBlack(id));
                client.setName(rs.getString(2));
                client.setSurename(rs.getString(3));
                client.setPhone(rs.getString(4));
                client.setMail(rs.getString(5));
                client.setAddres(rs.getString(6));
            }           
        } 
        return client;
    }
}
