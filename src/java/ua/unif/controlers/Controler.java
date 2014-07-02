package ua.unif.controlers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import ua.unif.commands.Command;
import ua.unif.commands.CommandFactory;

/*
Gets every request from jsp 
gets command using command factory
initialize logger
*/
public class Controler extends HttpServlet {
    static Logger logger = Logger.getLogger(Controler.class);
  
    @Override
    public void init() throws ServletException {
        //init loger with properties file
        String prefix = getServletContext().getRealPath("/");
        String file = getInitParameter("log4j");
        PropertyConfigurator.configure(prefix + file);
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        //getting command from factory and execute it
        Command c = CommandFactory.getCommand(request);
        c.execute(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
