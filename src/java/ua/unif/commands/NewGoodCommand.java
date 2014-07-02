package ua.unif.commands;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class NewGoodCommand implements Command{
    static final Logger log = Logger.getLogger(NewGoodCommand.class);
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher rd = request.getRequestDispatcher("/newgood.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException ex) {
            log.error("redirect to newgood.jsp err", ex);
        } catch (IOException ex) {
            log.error("redirect to newgood.jsp err", ex);
        }
    }
    
}
