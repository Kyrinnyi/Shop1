package ua.unif.commands;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import ua.unif.dao.BlackDAO;
import ua.unif.dao.DAOFactory;
import ua.unif.dao.entity.Client;

/*
puts client to black list using id
*/
public class SetBlackCommand implements Command{
    static final Logger log = Logger.getLogger(SetBlackCommand.class);
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        //geting parameter from request
        String clientId = request.getParameter("client");
        if (clientId == null){
            badFwd(request, response);
            return;
        }
        if (clientId.isEmpty()){
            badFwd(request, response);
            return;
        }
   
        //tries to set black using dao
        BlackDAO adao = DAOFactory.getBlackDAO();
        try {
           adao.setBlack(new Integer(clientId));
        } catch (SQLException ex) {
            log.error("seting black eror", ex);
        }   
        
        //sets black to client in session if it is black
        HttpSession hs = request.getSession(true);
        Client o = (Client)hs.getAttribute("client");
        if (o != null){
            o.setBlack(true);
        }
        
        RequestDispatcher rd = request.getRequestDispatcher("/order.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException ex) {
            log.error("redirect to order.jsp err", ex);
        } catch (IOException ex) {
            log.error("redirect to order.jsp err", ex);
        }
    }
    
    private void badFwd(HttpServletRequest request, HttpServletResponse response){
        RequestDispatcher rd = request.getRequestDispatcher("/page404.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException ex) {
            log.error("redirect to page404.jsp err", ex);
        } catch (IOException ex) {
            log.error("redirect to page404.jsp err", ex);
        }
    }       
}
