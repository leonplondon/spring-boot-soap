package pro.darkgod.soap.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.darkgod.api.wsdl.Friend;
import pro.darkgod.soap.client.FriendClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
public class ConsumeSoapController {

    private final FriendClient friendClient;

    public ConsumeSoapController(FriendClient friendClient) {
        this.friendClient = friendClient;
    }

    @GetMapping(value = "/friends", produces = {MediaType.APPLICATION_STREAM_JSON_VALUE})
    public Mono<Friend> friends() {
        return friendClient
            .getFriendList()
            .log();
    }

    @GetMapping(value = "/friend", produces = {MediaType.APPLICATION_STREAM_JSON_VALUE})
    public Flux<Friend> friend() {
        return friendClient
            .getFriends()
            .flux()
            .flatMap(e -> Flux.fromIterable(e.getFriend()))
            .log();
    }

}
