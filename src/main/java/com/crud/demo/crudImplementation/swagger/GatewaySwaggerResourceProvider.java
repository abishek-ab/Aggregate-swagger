package com.crud.demo.crudImplementation.swagger;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

@Component
@Primary
@EnableAutoConfiguration
public class GatewaySwaggerResourceProvider implements SwaggerResourcesProvider {

    @Autowired
    private SwaggerServicesConfig swaggerServiceList;

    private final Logger log = LoggerFactory.getLogger(GatewaySwaggerResourceProvider.class);
    
    public GatewaySwaggerResourceProvider() {

    }

	@Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        
        resources.add(swaggerResource("Default","/", "2.0"));
        swaggerServiceList.getServices().forEach(service -> {
        	resources.add(swaggerResource(service.getName(),service.getUrl(), service.getVersion()));
        	System.out.println(service.getName()+" "+service.getUrl()+" "+ service.getVersion());
        });

        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;	
    }
    

}