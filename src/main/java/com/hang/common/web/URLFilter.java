package com.hang.common.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Filter to allow case-insensitive URL request 
 *  
 *  如果配置了这个filter，那么请求中只能通过.htm, .xml, .js格式的路径
 *  
 * @author hang.yu
 * @version 2014-8-27
 *
 */
// @WebFilter(urlPatterns = "/*")
public class URLFilter implements Filter
{
    private FilterConfig filterConfig;
    
    public void init(FilterConfig filterConfig) 
    {
        this.filterConfig = filterConfig;
    }
    
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) 
    {
        try 
        {
            HttpServletRequest httpRequest = (HttpServletRequest)request;
            String uri= httpRequest.getRequestURI().toLowerCase();
            if(uri.indexOf(".htm")>=0 || uri.indexOf(".xml") >= 0 || uri.indexOf(".js") >= 0)
            {
                httpRequest.getRequestDispatcher(httpRequest.getServletPath().toLowerCase()).forward(request, response);
            }
        }            
        catch(ServletException sx)
        {
            filterConfig.getServletContext().log(sx.getMessage());
        }
        catch(IOException iox)
        {
            filterConfig.getServletContext().log(iox.getMessage());
        }
    }
    
    //Clean up resources
    public void destroy() 
    {
    }
}
