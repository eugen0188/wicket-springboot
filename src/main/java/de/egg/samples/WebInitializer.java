package de.egg.samples;

import org.apache.wicket.protocol.http.WicketFilter;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Wicket request handling configuration for Servlet 3.0+.
 */
@SuppressWarnings("unused")
@Configuration
public class WebInitializer implements ServletContextInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        final FilterRegistration filter = servletContext.addFilter("wicket-filter", WicketFilter.class);
        filter.setInitParameter("applicationClassName", "de.egg.samples.SampleApplication");
        filter.setInitParameter(WicketFilter.FILTER_MAPPING_PARAM, "/*");
        filter.addMappingForUrlPatterns(null, false, "/*");
        filter.setInitParameter("configuration", "development");
    }
}