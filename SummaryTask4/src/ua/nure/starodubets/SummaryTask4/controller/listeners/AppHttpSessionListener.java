package ua.nure.starodubets.SummaryTask4.controller.listeners;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

/**
 * Application Lifecycle Listener implementation class AppHttpSessionListener
 *
 */
public class AppHttpSessionListener implements HttpSessionListener {

	private static final Logger LOGGER = Logger.getLogger(AppHttpSessionListener.class);

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent event)  {
        LOGGER.info("Session " + event.getSession().getId() + " created");
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent event)  {
    	 LOGGER.info("Session " + event.getSession().getId() + " destroyed");
    }

}
