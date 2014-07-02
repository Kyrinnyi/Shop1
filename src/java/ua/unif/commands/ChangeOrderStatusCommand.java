package ua.unif.commands;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import ua.unif.dao.DAOFactory;
import ua.unif.dao.OrderDAO;
import ua.unif.dao.entity.Order;


/*
Changes order status ysing rder dao
and parameters from request.
*/
public class ChangeOrderStatusCommand implements Command{
    private final static Logger log = Logger.getLogger(ChangeOrderStatusCommand.class);
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        //geting parameters from request
        String orderId = request.getParameter("order");
        String com  = request.getParameter("com");
        //Cheacks if them right
        if (orderId == null || com == null){
            badFwd(request, response);
            return;
        }
        if (orderId.isEmpty() || com.isEmpty()){
            badFwd(request, response);
            return;
        }
        
        //getting order from session
        HttpSession hs = request.getSession(true);
        Order o = (Order)hs.getAttribute("order");
       
        //changes status with dao
        OrderDAO adao = DAOFactory.getOrderDAO();  
        if (o != null){
            try {
                //what exactly must be changed
                if (com.equals("close")){
                    adao.setClosed(new Integer(orderId));
                    o.setClosed(true);
                } else if (com.equals("set paid")){
                     adao.setPaid(new Integer(orderId));
                     o.setPaid(true);
                } else if (com.equals("confirm")){
                    adao.setConfirmed(new Integer(orderId));
                    o.setConfirmed(true);
                }
            } catch (SQLException ex) {
                log.error("changing order " + com + " err", ex);
            }   
        }
        
        //redirect to order
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
