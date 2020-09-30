package ru.cardservice.CardService;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;


@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext){
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/cardservice/ws/*");
    }

    @Bean(name = "card")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema cardSchema){
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("CardPort");
        wsdl11Definition.setLocationUri("/cardservice/ws");
        //wsdl11Definition.setTargetNamespace("http://localhost/cardservice");
        wsdl11Definition.setTargetNamespace("http://spring.io/guides/gs-producing-web-service");
        wsdl11Definition.setSchema(cardSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema CardSchema(){
        return new SimpleXsdSchema(new ClassPathResource("card.xsd"));
    }

}
