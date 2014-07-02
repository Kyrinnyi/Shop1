
package ua.unif.commands;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import ua.unif.dao.AdminDAO;
import ua.unif.dao.DAOFactory;
import ua.unif.dao.OrderDAO;
import ua.unif.dao.entity.Admin;
import ua.unif.dao.entity.Order;

/*
Gets login and password from request
Checks this parameters with database
If yes redirect to list of orders
else to bad login page
*/
public class AuthCommand1 implements Command{
    private final static Logger log = Logger.getLogger( AuthCommand1.class);
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        //geting parameters from request
        String login = request.getParameter("login");
        String password = request.getParameter("pass");
        //Cheacks if them right
        if (login == null || password == null){
            badFwd(request, response);
            return;
        }  
        if (login.isEmpty() || password.isEmpty()){
            badFwd(request, response);
            return;
        }
        //tries to get admin from dao 
        AdminDAO adao = DAOFactory.getAdminDAO();
        Admin admin = null;
        try {
            admin = adao.login(login, password);
        } catch (SQLException ex) {
            log.error("geting admin eror", ex);
        }
        //if there is no such admin redirect to bad login
        if (admin == null){
            badFwd(request, response);
            return;
        }
        //else gets list of orders 
        OrderDAO odao = DAOFactory.getOrderDAO();
        List<Order> l = null;
        //locale used to get right trnaslated goods from DAO
        Locale loc = request.getLocale();
        try {
            l = odao.getAll(loc.getCountry());
        } catch (SQLException ex) {
            log.error("geting orders eror", ex);
        }

        //Sets list of orders and admin to session
        HttpSession hs = request.getSession(true);
        hs.setAttribute("orderlist", l);
        hs.setAttribute("admin", true);
        hs.setAttribute("adm", admin);

        //redirect to orders
        RequestDispatcher rd = request.getRequestDispatcher("/orders.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException ex) {
            log.error("redirect to orders.jsp err", ex);
        } catch (IOException ex) {
            log.error("redirect to orders.jsp err", ex);
        } 
    } 
    
    private void badFwd(HttpServletRequest request, HttpServletResponse response){
        RequestDispatcher rd = request.getRequestDispatcher("/bad.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException ex) {
            log.error("redirect to bad.jsp err", ex);
        } catch (IOException ex) {
            log.error("redirect to bad.jsp err", ex);
        }
    }
}
