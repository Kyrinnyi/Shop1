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
import ua.unif.dao.entity.Good;

/*
adds new good usinf parametrs from fields on newgood.jsp
*/
public class AddGoodCommand implements Command{
    private final static Logger log = Logger.getLogger(AddGoodCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        Good good = new Good();
        Good goodUa = new Good();
        
        //geting parametr from request and set it to good
        String tmp = request.getParameter("name");
        good.setName(tmp);
        tmp = request.getParameter("namer");
        goodUa.setName(tmp);
        
        tmp = request.getParameter("description");
        good.setDescription(tmp);
        tmp = request.getParameter("descriptionr");
        goodUa.setDescription(tmp);
            
        tmp = request.getParameter("picture");
        good.setPicture(tmp);
        goodUa.setPicture(tmp);
          
        tmp = request.getParameter("price");
        good.setPrice(new Float(tmp));
        goodUa.setPrice(new Float(tmp));
     
        tmp = request.getParameter("availible");
        good.setAvailable(new Integer(tmp));
        goodUa.setAvailable(new Integer(tmp));
        
        tmp = request.getParameter("group");
        good.setGroup(tmp);
        tmp = request.getParameter("groupr");
        goodUa.setGroup(tmp);
        
        //adding good to database 
        GoodDAO gdao = DAOFactory.getGoodDAO();     
        try {
            gdao.addGood(good, goodUa);
        } catch (SQLException ex) {
            log.error("Adding good error", ex);
        }
        //redirect to jsp
        RequestDispatcher rd = request.getRequestDispatcher("/orders.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException ex) {
            log.error("redirect to orders.jsp err", ex);
        } catch (IOException ex) {
            log.error("redirect to orders.jsp err", ex);
        }   
    }   
}
