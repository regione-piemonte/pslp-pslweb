/******************************************************
 * Copyright Regione Piemonte - 2021
 * SPDX-License-Identifier: EUPL-1.2-or-later
 ******************************************************/
package it.csi.pslp.pslweb.business;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.csi.pslp.pslweb.util.SpringSupportedResource;
import it.csi.pslp.pslweb.util.listener.AppServletContextListener;

/**
 * The Class SpringApplicationContextHelper.
 */
@Component
public class SpringApplicationContextHelper implements ApplicationContextAware {

    /** The app context. */
    private static ApplicationContext appContext;

    /** The bean cache. */
    private static Map<String, Object> beanCache = new HashMap<>();

    /** The rest easy service cache. */
    private static Map<String, Object> restEasyServiceCache = new HashMap<>();

    /**
     * Instantiates a new spring application context helper.
     */
    // Private constructor prevents instantiation from other classes
    private SpringApplicationContextHelper() {
    }

    /**
     * Sets the application context.
     *
     * @param applicationContext the new application context
     * @throws BeansException the beans exception
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        appContext = applicationContext;
    }

    /**
     * Inject spring beans into rest easy service.
     *
     * @param ps the ps
     */
    public static void injectSpringBeansIntoRestEasyService(SpringSupportedResource ps) {
        ServletContext sc = AppServletContextListener.getServletContext();
        if (!ps.isSpringBeansInjected()) {
            ps.contextInitialized(sc);
            SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(ps, sc);
            ps.setSpringBeansInjected(true);
        }
    }

    /**
     * Inject spring beans into rest easy service.
     *
     * @param resteasyServiceName nome della resource class in cui iniettare i bean
     *                            di spring
     */
    public static void injectSpringBeansIntoRestEasyService(String resteasyServiceName) {
        for (Object service : restEasyServiceCache.values()) {
            if (service instanceof SpringSupportedResource && ((SpringSupportedResource) service).matchesServiceName(resteasyServiceName)) {
                SpringSupportedResource ps = ((SpringSupportedResource) service);
                injectSpringBeansIntoRestEasyService(ps);
                return;
            }
        }
    }

    /**
     * Register rest easy controller.
     *
     * @param service the service
     */
    public static void registerRestEasyController(Object service) {
        restEasyServiceCache.put(service.getClass().getSimpleName(), service);
    }

    /**
     * Gets the bean.
     *
     * @param beanName the bean name
     * @param cacheable the cacheable
     * @return the bean
     */
    public static Object getBean(String beanName, boolean cacheable) {

        if (cacheable && beanCache.containsKey(beanName)) {
            return beanCache.get(beanName);
        }

        Object bean = null;

        if (appContext.containsBean(beanName)) {
            bean = appContext.getBean(beanName);
        } else {
            bean = appContext.getBean(beanName.substring(0, 1).toLowerCase() + beanName.substring(1));
        }

        if (cacheable) {
            beanCache.put(beanName, bean);
        }

        return bean;
    }

    /**
     * Gets the bean.
     *
     * @param beanName the bean name
     * @return the bean
     */
    public static Object getBean(String beanName) {
        return getBean(beanName, true);
    }

}
