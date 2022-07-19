
package com.example.plan;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author admin
 */
@Configuration
public class ImgConfig implements WebMvcConfigurer{

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry); 
        registry.addResourceHandler("/productos/**").addResourceLocations("file:/C:/imgmeraki/productos/");
        registry.addResourceHandler("/emple/**").addResourceLocations("file:/C:/imgmeraki/emple/");
    }
    
}
