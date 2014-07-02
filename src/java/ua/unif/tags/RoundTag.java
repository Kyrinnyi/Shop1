package ua.unif.tags;

import java.io.IOException;
import java.text.DecimalFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import static javax.servlet.jsp.tagext.Tag.SKIP_BODY;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.log4j.Logger;

/*
formats double 
*/
public class RoundTag extends TagSupport{
    private final static Logger log = Logger.getLogger(RoundTag.class);
    
    String number;

    public void setNumber(String number) {
        this.number = number;
    }


    @Override
    public int doStartTag(){
        HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
        JspWriter out = pageContext.getOut();

        double n = new Double(this.number);
        DecimalFormat df = new DecimalFormat("###.##");
        try {
            out.write(df.format(n));
        } catch (IOException ex) {
            log.error("round tag err", ex);
        }
        return SKIP_BODY;   
    }
}
