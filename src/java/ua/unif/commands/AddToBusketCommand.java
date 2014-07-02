package ua.unif.commands;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import ua.unif.dao.DAOFactory;
import ua.unif.dao.entity.Good;
import ua.unif.dao.entity.OrderedGoood;

/*
Creates order good with goodid got from request
and put it into session busket list.
*/
public class AddToBusketCommand implements Command{
    private final static Logger log = Logger.getLogger(AddToBusketCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String goodId = request.getParameter("goodid");
        String amount = request.getParameter("amount");
        
        // if request contain goodid and amount then add it to busket
        // else just forward to busket without adding
        if (goodId != null && amount!= null){ 
            
            //Creating ordered good geting good from database            
            OrderedGoood og = new OrderedGoood();
            og.setAmount(new Integer(amount));
            Good g = null;
            Locale l = request.getLocale();
            try {
                g = DAOFactory.getGoodDAO().getGood(new Integer(goodId), l.getCountry());
            } catch (SQLException ex) {
                log.error("geting good eror", ex);
            }
            og.setGood(g);

            //Geting busket list from session or creating if it not exist
            HttpSession session = request.getSession(true);
            List<OrderedGoood> busket = (List<OrderedGoood>) session.getAttribute("busket");
            if (busket == null){
                busket = new ArrayList<OrderedGoood>();
            }
            //sets id to ordered good so it may be deleted in future
            og.setIdog(busket.size());
           
            //ading odered good to busket
            busket.add(og);
            //counts sum
            float sum = 0;
            for (OrderedGoood og1 : busket){
                sum += og1.getGood().getPrice() * og1.getAmount();
            }
            //seting busket list and sum to session
            request.setAttribute("sum", sum);
            session.setAttribute("busket", busket);
        }
        //redirect to busket
        RequestDispatcher rd = request.getRequestDispatcher("/busket.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException ex) {
            log.error("redirect to busket.jsp err", ex);
        } catch (IOException ex) {
            log.error("redirect to busket.jsp err", ex);
        }
    }
}
