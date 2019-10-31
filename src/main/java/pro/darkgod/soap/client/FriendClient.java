package pro.darkgod.soap.client;

import java.util.UUID;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import pro.darkgod.api.wsdl.Friend;
import pro.darkgod.api.wsdl.FriendRequest;

public class FriendClient extends WebServiceGatewaySupport {

    public Friend getFriendList() {
        FriendRequest friendRequest = new FriendRequest();
        friendRequest.setName(UUID.randomUUID().toString());

        return (Friend) getWebServiceTemplate()
            .marshalSendAndReceive("http://localhost:8080/api/soap", friendRequest,
                new SoapActionCallback("http://darkgod.pro/api/soap/friendRequest"));
    }
}
