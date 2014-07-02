package ua.unif.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import org.apache.log4j.Logger;

/*
Filters sets utf-8 encoding to request
*/
@WebFilter(filterName = "EncodingFilter", servletNames = {"Controler"})
public class EncodingFilter implements Filter {
    private final static Logger log = Logger.getLogger(EncodingFilter.class);   
    private FilterConfig filterConfig = null;
   
    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
    }    
    
    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        doBeforeProcessing(request, response);
        
        try {
            chain.doFilter(request, response);
        } catch (IOException t) {
            log.error("filter err", t);
        } catch (ServletException t) {
            log.error("filter err", t);
        }
        
        doAfterProcessing(request, response);
    }

    @Override
    public void destroy() {        
    }

    @Override
    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
    }
}
