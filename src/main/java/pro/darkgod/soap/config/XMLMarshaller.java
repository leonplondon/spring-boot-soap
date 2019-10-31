package pro.darkgod.soap.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import pro.darkgod.soap.client.FriendClient;

@Configuration
public class XMLMarshaller {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("pro.darkgod.api.wsdl");
        return marshaller;
    }

    @Bean
    public FriendClient friendClient(Jaxb2Marshaller marshaller) {
        FriendClient client = new FriendClient();
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

}
