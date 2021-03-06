package com.whitesoft.pinmap.config.initializer;

import com.whitesoft.pinmap.config.AppConfiguration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created by borisbondarenko on 19.12.15.
 *
 * Initializer for spring context. Stores a root context {@link AppConfiguration}
 *
 * @author brzzbr
 */
public class SpringConfigurationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{AppConfiguration.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}
