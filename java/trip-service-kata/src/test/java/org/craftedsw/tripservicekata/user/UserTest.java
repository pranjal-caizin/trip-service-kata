package org.craftedsw.tripservicekata.user;

import org.craftedsw.tripservicekata.user.model.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {
    @Test
    public void shouldReturnTrueIfUsersAreFriends() { //TODO: Think of good and simple names
        User user1 = new User();
        User user2 = new User();

        user1.addFriend(user2);

        assertTrue(user1.isFriendWith(user2));
    }

    @Test
    public void shouldReturnFalseIfUsersAreNotFriends() {
        User user1 = new User();
        User user2 = new User();

        assertFalse(user1.isFriendWith(user2));
    }
}
