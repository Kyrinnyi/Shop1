package ua.unif.commands;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import ua.unif.dao.DAOFactory;
import ua.unif.dao.GoodDAO;

/*
Sets amount of available good in database with id
*/
public class SetGoodAvailable implements Command{
    static final Logger log = Logger.getLogger(SetGoodAvailable.class);
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        //geting parameters from request
        String id = request.getParameter("goodid");
        String amount = request.getParameter("availible");
        if (id == null || amount == null){
            badFwd(request, response);
            return;
        }
        if (id.isEmpty() || amount.isEmpty()){
            badFwd(request, response);
            return;
        }
        
        //tries to set amount
        GoodDAO gdao = DAOFactory.getGoodDAO();
        try {
            gdao.setAvailable(new Integer(id), new Integer(amount));
        } catch (SQLException ex) {
            log.error("set available err", ex);
        }
        
        //redirect to good command
        CommandFactory.getCommand("good").execute(request, response);
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
