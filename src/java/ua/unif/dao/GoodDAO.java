
package ua.unif.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import ua.unif.dao.entity.Good;

/*
dao for goods and goods_ru table
*/
public class GoodDAO {
    private final static Logger log = Logger.getLogger(GoodDAO.class);
    private final Connection connection;
    
    public GoodDAO (Connection c){
        connection = c;
    }
    
    //gets all goods in group from database
    public List<Good> getAll(String group, String locale) throws SQLException{
        //choose what table to use
        String add = getLocalePref(locale);           
        
        List<Good> goods = new ArrayList<Good>();
        PreparedStatement ps;
        //if group = g choose everything
        //else only from same groop 
        if (group.equals("g")){
            ps = connection.prepareStatement("SELECT * FROM goods" + add);
        } else {
            ps = connection.prepareStatement("SELECT * FROM goods" + add + " WHERE gr = ?");
            ps.setString(1, group);
        }
        ResultSet rs = ps.executeQuery();
        log.info(ps.toString());

        if (rs!=null){
            while (rs.next()){
                Good good = new Good();
                good.setId(rs.getInt(1));
                good.setName(rs.getString(2));
                good.setDescription(rs.getString(3));
                good.setPrice(rs.getFloat(4));
                good.setPicture(rs.getString(5));
                good.setAvailable(rs.getInt(6));
                good.setGroup(rs.getString(7));
                goods.add(good);
            }
        }
        return  goods;      
    } 
    
    //gets good by id
    public Good getGood (int id, String locale) throws SQLException{
        String add = getLocalePref(locale);
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM "
                + "goods" + add + " WHERE id = ?");
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        log.info(ps.toString());
        Good good = null;
        if (rs != null){
            if(rs.next()){
                good= new Good();
                good.setId(rs.getInt(1));
                good.setName(rs.getString(2));
                good.setDescription(rs.getString(3));
                good.setPrice(rs.getFloat(4));
                good.setPicture(rs.getString(5));
                good.setAvailable(rs.getInt(6));
                good.setGroup(rs.getString(7));
            }          
        }
        return good;      
    }
    
    //gets list of groups
    public List<String> getGroups (String locale) throws SQLException{
        String add = getLocalePref(locale);
        PreparedStatement ps = connection.prepareStatement("SELECT DISTINCT gr"
                + " FROM goods" + add);
        List<String> groups = new ArrayList<String>();
        ResultSet rs = ps.executeQuery();
        log.info(ps.toString());
        if (rs != null){
            while(rs.next()){
                groups.add(rs.getString(1));
            }
            
        }
        return groups;      
    }
    
    //adds good to both databases
    public void addGood (Good g, Good gRu) throws SQLException{
        try {
            //transaction mode
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement("INSERT INTO "
                    + "goods (name, description, price, picture, available, gr) "
                    + "VALUES (?,?,?,?,?,?)");
            ps.setString(1, g.getName());
            ps.setString(2, g.getDescription());
            ps.setFloat(3, g.getPrice());
            ps.setString(4, g.getPicture());
            ps.setInt(5, g.getAvailable());
            ps.setString(6, g.getGroup());
            ps.execute();
            log.info(ps.toString());
            ps = connection.prepareStatement("INSERT INTO "
                    + "goods_ru (name, description, price, picture, available, gr) "
                    + "VALUES (?,?,?,?,?,?)");
            ps.setString(1, gRu.getName());
            ps.setString(2, gRu.getDescription());
            ps.setFloat(3, gRu.getPrice());
            ps.setString(4, gRu.getPicture());
            ps.setInt(5, gRu.getAvailable());
            ps.setString(6, gRu.getGroup());
            ps.execute();
            log.info(ps.toString());
            //transaction mode off
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            log.error("adding good err", ex);
            connection.rollback();
        }
    }

    //sets amount of available goods by id
    public void setAvailable (int id, int amount) throws SQLException{
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement("UPDATE goods "
                    + "SET available = ? WHERE id = ?");
            ps.setInt(1, amount);
            ps.setInt(2, id);
            ps.execute();
            log.info(ps.toString());
            ps = connection.prepareStatement("UPDATE goods_ru "
                    + "SET available = ? WHERE id = ?");
            ps.setInt(1, amount);
            ps.setInt(2, id);
            ps.execute();
            log.info(ps.toString());
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            log.error("set available good err", ex);
            connection.rollback();
        }
    }
    
    //sets amount of available goods by good
    public void setAvailable (Good g, int amount) throws SQLException{
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement("UPDATE goods "
                    + "SET available = ? WHERE id = ?");
            ps.setInt(1, amount);
            ps.setInt(2, g.getId());
            ps.execute();
            log.info(ps.toString());
            ps = connection.prepareStatement("UPDATE goods_ru "
                    + "SET available = ? WHERE id = ?");
            ps.setInt(1, amount);
            ps.setInt(2, g.getId());
            ps.execute();
            log.info(ps.toString());
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            connection.rollback();
        }
    }
    
    public void deleteGood (int id) throws SQLException{
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement("DELETE FROM "
                    + "goods WHERE id = ?");
            ps.setInt(1, id);
            ps.execute();
            ps = connection.prepareStatement("DELETE FROM "
                    + "goods_ru WHERE id = ?");
            ps.setInt(1, id);
            ps.execute();
            log.info(ps.toString());
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            log.error("delete good err", ex);
            connection.rollback();
        } 
    }
    
    //deletes good from both tables
    public void deleteGood (Good g) throws SQLException {
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement("DELETE FROM "
                    + "goods WHERE id = ?");
            ps.setInt(1, g.getId());
            ps.execute();
            log.info(ps.toString());
            ps = connection.prepareStatement("DELETE FROM "
                    + "goods_ru WHERE id = ?");
            ps.setInt(1, g.getId());
            ps.execute();
            log.info(ps.toString());
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            log.error("delete good err", ex);
            connection.rollback();
        }
    }
    
    private String getLocalePref (String locale){
        if (locale.equals("UA")){
            return "_ru";
        } else {
            return  "";
        }
    }
}
