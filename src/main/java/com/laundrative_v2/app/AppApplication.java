package com.laundrative_v2.app;

import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

import javax.naming.Context;

@SpringBootApplication
public class AppApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(AppApplication.class, args);
    }

    /**

     @Bean
     public ServletWebServerFactory servletWebServerContainer()
     {
     TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory()
     {
     @Override
     protected void postProcessContext(Context context)
     {
     SecurityConstraint securityConstraint = new SecurityConstraint();
     securityConstraint.setUserConstraint("CONFIDENTIAL");
     SecurityCollection collection = new SecurityCollection();
     collection.addPattern("/*");
     securityConstraint.addCollection(collection);
     // context.addConstraint(securityConstraint);
     }
     };

     tomcat.addAdditionalTomcatConnectors(httpToHttpsRedirectConnector());

     return tomcat;
     }

     * */

    private Connector httpToHttpsRedirectConnector()
    {
        Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
        connector.setScheme("http");
        connector.setPort(8082);
        connector.setSecure(false);
        connector.setRedirectPort(23000);
        return connector;
    }


}
