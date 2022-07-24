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

    @Value("${keep.note.static.file.minimize}")
    private String minimizedFiles;

    @Value("${keep.note.static.file.full}")
    private String fullFiles;

    @Value("${keep.note.files.location}")
    private String filesLocation;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(filePattern)
                .addResourceLocations(minimizedFiles, fullFiles, filesLocation)
                .setCachePeriod(0)
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }
}
