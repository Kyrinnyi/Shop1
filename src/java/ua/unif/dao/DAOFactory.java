package ua.unif.dao;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

public class DAOFactory {
    private final static Logger log = Logger.getLogger(DAOFactory.class);
    private static DataSource ds;
    private static Connection con;
    
    //initialize pool of connections
    static {
        InitialContext initContext;
        try {
             initContext = new InitialContext();
             Context envContext  = (Context)initContext.lookup("java:/comp/env");
             ds = (DataSource)envContext.lookup("jdbc/appname");
             con = getC();
            // con = ds.getConnection();      
        } catch (NamingException ex) {       
            log.error("probleem with initializing db pool", ex);   
        }
    }
    
    public static AdminDAO getAdminDAO(){
        return new AdminDAO(con);
    }
       
    public static ClientDAO getClientDAO(){
        return new ClientDAO(con);
    }
    
    public static BlackDAO getBlackDAO(){
        return new BlackDAO(con);
    }
    
    public static GoodDAO getGoodDAO(){
        return new GoodDAO(con);
    }
    
    public static OrderedGoodDAO getOrderedGoodDAO(){
        return new OrderedGoodDAO(con);
    }
    
    public static OrderDAO getOrderDAO(){
        return new OrderDAO(con);
    }
    
    private static Connection getC(){
        Connection con = null; 
        try {
            con = ds.getConnection();
        } catch (SQLException ex) {
            log.error("probleem with initializing db pool", ex);
        }
        return con;
    }
}
