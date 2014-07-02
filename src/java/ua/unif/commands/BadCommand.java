

package ua.unif.commands;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class BadCommand implements Command{
    private final static Logger log = Logger.getLogger(BadCommand.class);
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
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
