package ua.unif.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import ua.unif.dao.entity.Good;
import ua.unif.dao.entity.OrderedGoood;

/*
dao for og table
*/
public class OrderedGoodDAO {
    private final static Logger log = Logger.getLogger(OrderedGoodDAO.class);
    private final Connection connection;
    
    public OrderedGoodDAO (Connection c){
        connection = c;
    }
   
    //adds new orderes good
    public void addOrderedGood (int orderId, Good good, int amount) throws SQLException{
        PreparedStatement ps = connection.prepareStatement("INSERT INTO "
                + "og (idgoods, idorder, amount) "
                + "VALUES (?,?,?)");
        
        ps.setInt(1, good.getId());
        ps.setInt(2, orderId);
        ps.setInt(3, amount);     
        ps.execute();
        log.info(ps.toString());  
    }
    
    //gets all ordered good in order
    public List<OrderedGoood> getAll (int orderId, String locale) throws SQLException{
        List<OrderedGoood> list = new ArrayList<OrderedGoood>();
        
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM og "
                + "WHERE idorder = ?");
        ps.setInt(1, orderId);
        
        GoodDAO gd = DAOFactory.getGoodDAO();
        
        ResultSet rs = ps.executeQuery();
        log.info(ps.toString());
        if (rs!=null){
            while (rs.next()){
                OrderedGoood og = new OrderedGoood();
                og.setIdog(rs.getInt(1));
                Good g = gd.getGood(rs.getInt(2), locale);
                og.setGood(g);
                og.setAmount(rs.getInt(4));
                list.add(og);
            }
        }  
        return list;
    }    
}
