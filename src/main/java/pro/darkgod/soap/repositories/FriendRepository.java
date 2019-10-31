package pro.darkgod.soap.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import org.springframework.stereotype.Component;
import pro.darkgod.api.soap.Friend;

@Component
public class FriendRepository {

    private final Random random = new Random();

    public List<Friend> findAll() {
        ArrayList<Friend> friends = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            friends.add(getFriend());
        }

        return friends;
    }

    public Friend getFriend() {
        Friend friend = new Friend();
        friend.setName(UUID.randomUUID().toString());
        friend.setAge(random.nextInt(22));
        friend.setFriends(random.nextInt(100));
        return friend;
    }
}
