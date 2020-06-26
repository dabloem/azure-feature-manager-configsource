package com.abnamro.coesd.azure;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.config.spi.ConfigSource;

/**
 * TestBean
 */
@ApplicationScoped
public class TestBean {

    @Inject
    @ConfigProperty(name = "test")
    private String test;

    public void test(){
        System.out.println(test.toUpperCase());
    }
    
}