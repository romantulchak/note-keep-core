package com.notekeep.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class FileConfig implements WebMvcConfigurer {

    @Value("${keep.note.file.pattern}")
    private String filePattern;

    @Value("${keep.note.static.file}")
    private String staticLocation;

    @Value("${keep.note.files.location}")
    private String filesLocation;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(filePattern)
                .addResourceLocations(staticLocation, filesLocation)
                .setCachePeriod(0)
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }
}
