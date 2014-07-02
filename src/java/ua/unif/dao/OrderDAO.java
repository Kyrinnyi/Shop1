package ua.unif.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import ua.unif.dao.entity.Client;
import ua.unif.dao.entity.Order;

/*
dao for orders table
*/
public class OrderDAO {
    private final static Logger log = Logger.getLogger(OrderDAO.class);
    private final Connection connection;
    
    public OrderDAO (Connection c){
        connection = c;
    }
    
    //gets all orders on shop
    public List<Order> getAll(String locale) throws SQLException{
        List<Order> orders = new ArrayList<Order>();
        
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM orders "
                + "WHERE closed = ?");
        ps.setInt(1, 0);        
        ResultSet rs = ps.executeQuery();
        log.info(ps.toString());
            
        if (rs!=null){
            while (rs.next()){
                Order order = new Order();
                order.setId(rs.getInt(1));
                ClientDAO cd = DAOFactory.getClientDAO();
                order.setClient(cd.getClient(rs.getInt(2)));
                OrderedGoodDAO ogd = DAOFactory.getOrderedGoodDAO();
                order.setOrderedGooods(ogd.getAll(order.getId(), locale));
                
                order.setPaid(rs.getInt(3)!=0);
                order.setConfirmed(rs.getInt(4)!=0);
                order.setClosed(rs.getInt(5)!=0);
                
                orders.add(order);
            }
        }
        return  orders;      
    } 
  
    //gets order by id
    public Order getOrder(int id, String locale) throws SQLException{
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM orders "
                + "WHERE idorders = ?");
        ps.setInt(1, id);        
        ResultSet rs = ps.executeQuery();
        log.info(ps.toString());
        
        Order order = null;
        if (rs!=null){
            if (rs.next()){
                order = new Order();
                order.setId(rs.getInt(1));
                ClientDAO cd = DAOFactory.getClientDAO();
                order.setClient(cd.getClient(rs.getInt(2)));
                OrderedGoodDAO ogd = DAOFactory.getOrderedGoodDAO();
                order.setOrderedGooods(ogd.getAll(order.getId(), locale));
                
                order.setPaid(rs.getInt(3)!=0);
                order.setConfirmed(rs.getInt(4)!=0);
                order.setClosed(rs.getInt(5)!=0);
            }
        }
        return  order;   
    }
    
    public void setPaid(int id) throws SQLException{
        PreparedStatement ps = connection.prepareStatement("UPDATE orders "
                + "SET paid = ? WHERE idorders = ?");
        ps.setInt(1, 1);   
        ps.setInt(2, id);   
        ps.execute(); 
        log.info(ps.toString());
    }
    
    public void setConfirmed(int id) throws SQLException{
        PreparedStatement ps = connection.prepareStatement("UPDATE orders "
                + "SET confirmed = ? WHERE idorders = ?");
        ps.setInt(1, 1);   
        ps.setInt(2, id);   
        ps.execute(); 
        log.info(ps.toString());
    }
    
    public void setClosed(int id) throws SQLException{
        PreparedStatement ps = connection.prepareStatement("UPDATE orders "
                + "SET closed = ? WHERE idorders = ?");
        ps.setInt(1, 1);   
        ps.setInt(2, id);   
        ps.execute();  
        log.info(ps.toString());
    }
    
    public Order newOrder(Client client) throws SQLException{
        PreparedStatement ps = connection.prepareStatement("INSERT INTO "
                + "orders (idclient, paid, confirmed, closed) "
                + "VALUES (?,?,?,?)");
        ps.setInt(1, client.getId());
        ps.setInt(2, 0);
        ps.setInt(3, 0);
        ps.setInt(4, 0);    
        ps.execute();
        log.info(ps.toString());        
        
        //gets order id
        ps = connection.prepareStatement("SELECT * FROM orders"
                + " WHERE idclient = ?");
        ps.setInt(1, client.getId()); 
        log.info(ps.toString());

        ResultSet rs = ps.executeQuery();
        Order order = new Order();

        if (rs!=null){
            if (rs.last()){
                order.setId(rs.getInt(1));                
            }
        }
        return order;   
    }
}
