package pro.darkgod.soap.client;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pro.darkgod.api.wsdl.Friend;
import pro.darkgod.api.wsdl.FriendList;
import pro.darkgod.api.wsdl.FriendRequest;
import pro.darkgod.api.wsdl.FriendsRequest;
import pro.darkgod.soap.util.SoapReactiveHelper;
import reactor.core.publisher.Mono;

@Service
public class FriendClient {

    @Value("${soap.service}")
    private String soapService;

    @Autowired
    private SoapReactiveHelper reactorWrapper;

    public Mono<Friend> getFriendList() {
        FriendRequest friendRequest = new FriendRequest();
        friendRequest.setName(UUID.randomUUID().toString());

        return reactorWrapper.callSoap(soapService, friendRequest);
    }

    public Mono<FriendList> getFriends() {
        return reactorWrapper.callSoap(soapService, new FriendsRequest());
    }
}
