package com.levralabs.Jobboard.config;

import com.levralabs.Jobboard.entity.Job;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class JobConfig implements RepositoryRestConfigurer {
    /**
     * Disable the POST and DELETE http method
     * @param config
     * @param cors
     */
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        HttpMethod[] unsupportedMethod = {HttpMethod.POST, HttpMethod.DELETE};
        config.getExposureConfiguration().forDomainType(Job.class)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedMethod))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedMethod));

    }
}
