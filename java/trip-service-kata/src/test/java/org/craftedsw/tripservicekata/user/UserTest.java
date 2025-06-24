package org.craftedsw.tripservicekata.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {

    @Test
    public void shouldRecognizesAFriend() {
        User user = new User();
        User friend = new User();
        user.addFriend(friend);

        assertTrue(user.isFriendsWith(friend));
    }

    @Test
    public void shouldNotRecognizesAStranger() {
        User user = new User();
        User stranger = new User();

        assertFalse(user.isFriendsWith(stranger));
    }
}
