package ua.unif.commands;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import ua.unif.dao.DAOFactory;
import ua.unif.dao.GoodDAO;
import ua.unif.dao.entity.Good;

/*
Gets good from busket and redirects to order page
*/
public class GoodCommand implements Command{
    static final Logger log = Logger.getLogger(GoodCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        //getting goodid parameter
        String id = request.getParameter("goodid");
        if (id == null){
            badFwd(request, response);
            return;
        }
        if (id.isEmpty()){
            badFwd(request, response);
            return;
        }
        
        //getting good with dao
        GoodDAO gdao = DAOFactory.getGoodDAO();
        Good good = null;
        //locale needs to get right translated good
        Locale l = request.getLocale();
        try {
            good = gdao.getGood(new Integer(id), l.getCountry());
        } catch (SQLException ex) {
            log.error("geting good eror", ex);
        }
        
        request.setAttribute("good", good);
        RequestDispatcher rd = request.getRequestDispatcher("/good.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException ex) {
            log.error("redirect to good.jsp err", ex);
        } catch (IOException ex) {
            log.error("redirect to good.jsp err", ex);
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
    

