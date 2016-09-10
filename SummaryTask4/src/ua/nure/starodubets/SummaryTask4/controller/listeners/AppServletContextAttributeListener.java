package ua.nure.starodubets.SummaryTask4.controller.listeners;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

import org.apache.log4j.Logger;

/**
 * Application Lifecycle Listener implementation class AppServletContextAttributeListener
 *
 */
public class AppServletContextAttributeListener implements ServletContextAttributeListener {

	private static final Logger LOGGER = Logger.getLogger(AppServletContextAttributeListener.class);

	/**
     * @see ServletContextAttributeListener#attributeAdded(ServletContextAttributeEvent)
     */
    public void attributeAdded(ServletContextAttributeEvent event)  {
         LOGGER.info("Attribute " + event.getName() + " added to the ServletContext object");
    }

	/**
     * @see ServletContextAttributeListener#attributeRemoved(ServletContextAttributeEvent)
     */
    public void attributeRemoved(ServletContextAttributeEvent event)  {
    	LOGGER.info("Attribute " + event.getName() + " removed from the ServletContext object");
    }

	/**
     * @see ServletContextAttributeListener#attributeReplaced(ServletContextAttributeEvent)
     */
    public void attributeReplaced(ServletContextAttributeEvent event)  {
    	LOGGER.info("Attribute " + event.getName() + " replaced in the ServletContext object");
    }

}
