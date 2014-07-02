package ua.unif.commands;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import ua.unif.dao.DAOFactory;
import ua.unif.dao.OrderedGoodDAO;
import ua.unif.dao.entity.Client;
import ua.unif.dao.entity.Order;
import ua.unif.dao.entity.OrderedGoood;

/*
Creating client with parameters from form
creating new order
creating ordered goods with busket list
puts it all into database
*/
public class ConfirmOrderCommand implements Command{
    private final static Logger log = Logger.getLogger(ConfirmOrderCommand.class);
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        //geting busket list from session
        HttpSession session = request.getSession(true);
        List<OrderedGoood> busket = (List<OrderedGoood>) session.getAttribute("busket");
        //if it not exist or empty redirects to thank you page
        if (busket == null){
            redirect(request, response);
            return;
        } else if(busket.isEmpty()){
            redirect(request, response);
            return;
        }
        
        //Criating client 
        Client c = new Client();
        Order o = null;
        c.setAddres(request.getParameter("addres"));
        c.setName(request.getParameter("name"));
        c.setSurename(request.getParameter("surename"));
        c.setPhone(request.getParameter("phone"));
        c.setMail(request.getParameter("mail"));
        //adding it to database 
        //creating new order in database
        try {
            c = DAOFactory.getClientDAO().getClient(c);
            o = DAOFactory.getOrderDAO().newOrder(c);
        } catch (SQLException ex) {
            log.error("geting client with client or new order err",  ex);
        }
        
        //if something goes wrong redirect        
        if (o == null){
            redirect(request, response);
            return;
        }
        
        //creting ordered goods with busket list
        int orderid = o.getId();     
        OrderedGoodDAO ogdao = DAOFactory.getOrderedGoodDAO();       
        for (OrderedGoood og : busket){
            try {
                ogdao.addOrderedGood(orderid,og.getGood(),og.getAmount());
            } catch (SQLException ex) {
                log.error("adding ordered ood err",  ex);
            }
        }
        
        //removes busket from session
        session.removeAttribute("busket");
        redirect(request, response);
    }
    
    public void redirect(HttpServletRequest request, HttpServletResponse response){
        RequestDispatcher rd = request.getRequestDispatcher("/thnk.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException ex) {
            log.error("redirect to thnk.jsp err", ex);
        } catch (IOException ex) {
            log.error("redirect to thnk.jsp err", ex);
        }       
    } 
}
