package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSessionProvider;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TripServiceTest {

    private final UserSessionProvider userSessionProvider = mock(UserSessionProvider.class);
    private final TripProvider tripProvider = mock(TripProvider.class);
    private final TripService tripService = new TripService(userSessionProvider, tripProvider);

    @Test
    public void shouldThrowExceptionWhenUserNotLoggedIn() {
        when(userSessionProvider.getLoggedUser()).thenReturn(null);

        User user = new User();

        assertThrows(UserNotLoggedInException.class, () -> {
            tripService.getTripsByUser(user);
        });
    }

    @Test
    public void shouldReturnTripsWhenLoggedUserIsAFriend() {
        User loggedUser = new User();
        User otherUser = new User();
        otherUser.addFriend(loggedUser);

        Trip trip1 = new Trip();
        Trip trip2 = new Trip();
        List<Trip> expectedTrips = List.of(trip1, trip2);

        when(userSessionProvider.getLoggedUser()).thenReturn(loggedUser);
        when(tripProvider.findTripsByUser(otherUser)).thenReturn(expectedTrips);

        List<Trip> actualTrips = tripService.getTripsByUser(otherUser);

        assertEquals(expectedTrips, actualTrips);
    }

    @Test
    public void shouldReturnEmptyListWhenUsersAreNotFriends() {
        User loggedUser = new User();
        User otherUser = new User();

        when(userSessionProvider.getLoggedUser()).thenReturn(loggedUser);

        List<Trip> result = tripService.getTripsByUser(otherUser);

        assertTrue(result.isEmpty());
    }
}
