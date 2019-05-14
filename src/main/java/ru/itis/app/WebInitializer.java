package ru.itis.app;

import ru.itis.config.AppConfig;
import org.springframework.web.WebApplicationInitializer;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import ru.itis.filters.LogFilter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext container) throws ServletException {

//This time we will use an annotation based context so that we can use Java
// and annotations for configuration and remove the need for XML files like dispatcher-config.xml
        AnnotationConfigWebApplicationContext context
                = new AnnotationConfigWebApplicationContext();
//This type of context can then be configured registering a configuration class:
        context.register(AppConfig.class);

        context.setServletContext(container);
        DispatcherServlet dispatcherServlet = new DispatcherServlet(context);
//The next step is creating and registering our dispatcher servlet:
        ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", dispatcherServlet);
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");
//        container.addFilter("logFilter", LogFilter.class).addMappingForUrlPatterns(null, false, "/basket","/deleteproduct", "/mail", "/home","/shop");
    }
}
