package pro.darkgod.soap.endpoints;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import pro.darkgod.api.soap.Friend;
import pro.darkgod.api.soap.FriendList;
import pro.darkgod.api.soap.FriendRequest;
import pro.darkgod.soap.repositories.FriendRepository;

@Endpoint
public class FriendEndpoint {

    private static final String NAMESPACE_URI = "http://darkgod.pro/api/soap";

    private final FriendRepository friendRepository;

    public FriendEndpoint(FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "friendsRequest")
    @ResponsePayload
    public FriendList getFriends() {
        FriendList friendList = new FriendList();

        for (int i = 0; i < 10; i++) {
            friendList.getFriend().add(new Friend());
        }

        return friendList;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "friendRequest")
    @ResponsePayload
    public Friend requestFriend(@RequestPayload FriendRequest friendRequest) {
        Friend friend = friendRepository.getFriend();
        friend.setName(friendRequest.getName());
        return friend;
    }

}
