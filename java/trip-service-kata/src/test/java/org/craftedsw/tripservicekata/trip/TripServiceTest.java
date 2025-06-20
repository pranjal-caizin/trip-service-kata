package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSessionProvider;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TripServiceTest {

    private final UserSessionProvider userSessionProvider = mock(UserSessionProvider.class);
    private final TripService tripService = new TripService(userSessionProvider);

    @Test
    public void shouldThrowExceptionWhenUserNotLoggedIn() {
        when(userSessionProvider.getLoggedUser()).thenReturn(null);

        User user = new User();

        assertThrows(UserNotLoggedInException.class, () -> {
            tripService.getTripsByUser(user);
        });
    }
}
