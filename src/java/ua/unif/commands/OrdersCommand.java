package ua.unif.commands;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;
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
Gets order, clietn with dao
*/
public class OrdersCommand implements Command{
    static final Logger log = Logger.getLogger(OrdersCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        //geting parameter from request
        String orderId = request.getParameter("order");
        if (orderId == null){
            badFwd(request, response);
            return;
        }
        if (orderId.isEmpty()){
            badFwd(request, response);
            return;
        }

        //geting order from dao
        OrderDAO adao = DAOFactory.getOrderDAO();
        Order order = null;
        //locale needs to get right translated good
        Locale l = request.getLocale();
        try {
            order = adao.getOrder(new Integer(orderId), l.getCountry());
        } catch (SQLException ex) {
            log.error("geting order eror", ex);
        }
        
        //if something goes wrong redirect
        if (order == null){
            badFwd(request, response);
            return;
        }
        //sets paramer to session
        HttpSession hs = request.getSession(true);
        hs.setAttribute("order", order);
        hs.setAttribute("client", order.getClient());
        hs.setAttribute("listgoods", order.getOrderedGooods());

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
