package pers.darren.springboot.initializer;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;

public class CustomWebApplicationInitializer implements WebApplicationInitializer {

    private static final Logger logger = LoggerFactory.getLogger(CustomWebApplicationInitializer.class);

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        logger.info("pers.darren.springboot.initializer.CustomWebApplicationInitializer.onStartup");
    }
}
