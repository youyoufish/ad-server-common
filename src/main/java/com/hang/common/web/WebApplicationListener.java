package com.hang.common.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


/**
 * A simulation of Global.asax.cs
 * @author quangang.sheng@adchina.com
 * @version Tue Apr 23 11:08:49     2013
 */
public abstract class WebApplicationListener implements ServletContextListener, HttpSessionListener
{

    static volatile ServletContext servletContext;

    public static ServletContext getServletContext() {
        return servletContext;
    }

    public void contextInitialized(ServletContextEvent event) {
        servletContext = event.getServletContext();
        applicationOnStart(servletContext, event);
    }
    public void contextDestroyed(ServletContextEvent event) {
        applicationOnEnd(event.getServletContext(), event);
        servletContext = null;
    }

    public void sessionDestroyed(HttpSessionEvent event) {
        sessionOnEnd(event.getSession(), event);
    }
    public void sessionCreated(HttpSessionEvent event) {
        sessionOnStart(event.getSession(), event);
    }

    protected abstract void applicationOnStart(ServletContext context, ServletContextEvent event);
    protected abstract void applicationOnEnd(ServletContext context, ServletContextEvent event);
    protected abstract void sessionOnStart(HttpSession session, HttpSessionEvent event);
    protected abstract void sessionOnEnd(HttpSession session, HttpSessionEvent event);
}
