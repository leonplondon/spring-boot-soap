package pro.darkgod.soap.config;

import static pro.darkgod.soap.Constants.VALUE_DEFAULT_SOAP_NAMESPACE;
import static pro.darkgod.soap.Constants.VALUE_PORT_TYPE_NAME;
import static pro.darkgod.soap.Constants.VALUE_SOAP_ENDPOINT;

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
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, VALUE_SOAP_ENDPOINT + "/*");
    }

    @Bean(name = "services")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema servicesSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName(VALUE_PORT_TYPE_NAME);
        wsdl11Definition.setLocationUri(VALUE_SOAP_ENDPOINT);
        wsdl11Definition.setTargetNamespace(VALUE_DEFAULT_SOAP_NAMESPACE);
        wsdl11Definition.setSchema(servicesSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema servicesSchema() {
        ClassPathResource serviceResource = new ClassPathResource("service-schema.xsd");
        SimpleXsdSchema simpleXsdSchema = new SimpleXsdSchema();
        simpleXsdSchema.setXsd(serviceResource);
        return simpleXsdSchema;
    }
}