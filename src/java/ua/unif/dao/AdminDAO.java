package ua.unif.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import ua.unif.dao.entity.Admin;

/*
dao for admins table
*/
public class AdminDAO {
    private final static Logger log = Logger.getLogger(AdminDAO.class);
    private final Connection connection;
    
    public AdminDAO (Connection c){
        connection = c;
    }
    
    //cheaking if there is admin with such login and pass in admins table
    public Admin login (String login, String pass) throws SQLException{
        PreparedStatement ps = connection.prepareStatement("SELECT login FROM "
                + "admins WHERE login = ? AND pass = ?");
        ps.setString(1, login);
        ps.setString(2, pass);
        ResultSet rs = ps.executeQuery();
        log.info(ps.toString());
        
        Admin admin = null;
        if (rs != null){
            if(rs.next()){
                admin= new Admin();
                admin.setLogin( rs.getString(1));
                admin.setPass(pass);
            }        
        }
        return admin;      
    }
}
