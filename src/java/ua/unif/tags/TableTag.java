package ua.unif.tags;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.log4j.Logger;
import ua.unif.dao.entity.Good;

/*
creates table of goods 
*/
public class TableTag extends TagSupport{
    private final static Logger log = Logger.getLogger(TableTag.class);
    
    String group;
    String page;
    String sizex;
    String sizey;

    public void setGroup(String group) {
        this.group = group;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public void setSizex(String sizex) {
        this.sizex = sizex;
    }

    public void setSizey(String sizey) {
        this.sizey = sizey;
    }
    
    @Override
    public int doStartTag(){
        HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
        List<Good> goods = (ArrayList<Good>)request.getAttribute("goodlist");
        JspWriter out = pageContext.getOut();
        
        
        try {
            out.write("<table  cellspacing=\"20\"> ");
            int size = goods.size();
            int sizex = new Integer(this.sizex);
            int sizey = new Integer(this.sizey);
            
            int p;
            String page = request.getParameter("page");
            if (page == null || page.isEmpty()){
                p = 1;
            } else{
                p = new Integer(page);
            }  
            
            int count = (p - 1) * sizex * sizey;
            outer:  for (int j = 0; j < sizey; j++){
                out.write("<tr>");
                for (int i = 0; i < sizex; i++){
                   if (count++ >= size) break outer;
                   Good g = goods.get(count - 1);
                   out.write("<td>");
                   out.write("<h3><a href=\"controler?auth=good&goodid="+ g.getId()+ "\">");
                   out.write(g.getName() + "</a><br></h3>");
                   out.write("<a href=\"controler?auth=good&goodid="+ g.getId()+"\">");
                   out.write("<img src=\"");
                   out.write(request.getContextPath());
                   out.write("/images/");
                   out.write(g.getPicture());
                   out.write("\" alt=\"no picture\" height=\"170\"></a><br><h3>");
                   out.write(g.getPrice()+ "<br></h3>");
                   out.write("</td>");
                }         
                out.write("</tr>");
            }
            out.write("</table>");
            int pages = goods.size()/(sizex*sizey);
            if (goods.size()%(sizex*sizey) !=0){
                pages++;
            }
            String group = request.getParameter("group");
            out.write("&nbsp;&nbsp;&nbsp;&nbsp;");
            for (int i = 1; i <= pages; i++){
                out.write("<a href=\"controler?auth=goods&group=" +
                        group + "&page=" + i + "\">");
                out.write(i + " " + "</a>");
            }
        } catch (IOException ex) {
            log.error("table tag err", ex);
        }
        return SKIP_BODY;   
    }
}
