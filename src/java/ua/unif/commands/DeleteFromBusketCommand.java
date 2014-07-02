package ua.unif.commands;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import ua.unif.dao.entity.OrderedGoood;

/*
delete ordered good from busket list in session
*/
public class DeleteFromBusketCommand implements Command{
    private final static Logger log = Logger.getLogger(ChangeOrderStatusCommand.class);
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        //getting id from request
        String idOg = request.getParameter("idog");
        if (idOg == null){
            badFwd(request, response);
            return;
        }
        if (idOg.isEmpty()){
            badFwd(request, response);
            return;
        }
        //creating ordered good equal to that must be deleted
        OrderedGoood og = new OrderedGoood();
        og.setIdog(new Integer(idOg));
        
        //getting busket list from session
        HttpSession session = request.getSession(true);
        List<OrderedGoood> busket = (List<OrderedGoood>) session.getAttribute("busket");
        if (busket == null){
            badFwd(request, response);
            return;
        }    
        //removing ordered good
        busket.remove(og);
        session.setAttribute("busket", busket);
        
        //redirects back to busket
        RequestDispatcher rd = request.getRequestDispatcher("/busket.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException ex) {
            log.error("redirect to busket.jsp err", ex);
        } catch (IOException ex) {
            log.error("redirect to busket.jsp err", ex);
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
