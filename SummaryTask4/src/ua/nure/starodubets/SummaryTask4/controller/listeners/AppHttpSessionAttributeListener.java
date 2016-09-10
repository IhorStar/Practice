package ua.nure.starodubets.SummaryTask4.controller.listeners;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import org.apache.log4j.Logger;

/**
 * Application Lifecycle Listener implementation class AppHttpSessionAttributeListener
 *
 */
public class AppHttpSessionAttributeListener implements HttpSessionAttributeListener {

	private static final Logger LOGGER = Logger.getLogger(AppHttpSessionAttributeListener.class);

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent event)  {
    	LOGGER.info("Attribute " + event.getName() + " added to the HttpSession object ");
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent event)  {
    	LOGGER.info("Attribute " + event.getName() + " removed from the HttpSession object");
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent event)  {
    	LOGGER.info("Attribute " + event.getName() + " replaced in the HttpSession object");
    }

}
