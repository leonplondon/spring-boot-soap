package pro.darkgod.soap.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;
import reactor.core.publisher.Mono;

@Component
public class SoapReactiveHelper {

    @Autowired
    private WebServiceTemplate webServiceTemplate;

    public <T> Mono<T> callSoap(String uri, Object request) {
        return Mono.just((T) webServiceTemplate.marshalSendAndReceive(uri, request));
    }
}
