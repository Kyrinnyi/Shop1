package ua.unif.commands;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
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
gets list of goods in group
sets it to request
*/

public class GoodsCommand implements Command{
    static final Logger log = Logger.getLogger(GoodsCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {      
        //getting group parameter
        String group = request.getParameter("group");
        if (group == null){
            badFwd(request, response);
            return;
        }
        if (group.isEmpty()){
            badFwd(request, response);
            return;
        }
        
        //creating list of goods and list of groups
        //fills it with information from dao
        List<Good> goods = null;
        List<String> groups = null;
        GoodDAO gdao =  DAOFactory.getGoodDAO();
        //locale is for getting right translated goods and groups
        Locale l = request.getLocale();
        try {
            goods = gdao.getAll(group, l.getCountry());
            groups = gdao.getGroups(l.getCountry());
        } catch (SQLException ex) {
            log.error("getting goods or groups err", ex);
        }
        
        //setting results to request
        request.setAttribute("goodlist", goods);
        request.setAttribute("groups", groups);
            
        RequestDispatcher rd = request.getRequestDispatcher("/goods.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException ex) { 
            log.error("redirect to goods.jsp err", ex);
        } catch (IOException ex) {
            log.error("redirect to goods.jsp err", ex);
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
