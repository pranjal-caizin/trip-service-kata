package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.trip.model.Trip;
import org.craftedsw.tripservicekata.trip.service.TripService;
import org.craftedsw.tripservicekata.user.model.User;
import org.craftedsw.tripservicekata.user.UserSessionProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TripServiceTest {

    private UserSessionProvider userSessionProvider;
    private TripProvider tripProvider;
    private TripService tripService;

    @BeforeEach
    public void setUp() {
        userSessionProvider = mock(UserSessionProvider.class);
        tripProvider = mock(TripProvider.class);
        tripService = new TripService(userSessionProvider, tripProvider);
    }

    @Test
    public void shouldFailWhenUserIsNotLoggedIn() {
        when(userSessionProvider.getLoggedUser()).thenReturn(null);

        User user = new User();

        assertThrows(UserNotLoggedInException.class, () -> {
            tripService.getTripsByUser(user);
        });
    }

    @Test
    public void shouldReturnTripsListWhenLoggedInUserIsAFriendWithOtherUser() {
        User loggedInUser = new User();
        User otherUser = new User();
        otherUser.addFriend(loggedInUser);

        Trip trip1 = new Trip();
        Trip trip2 = new Trip();
        List<Trip> expectedTrips = List.of(trip1, trip2);

        when(userSessionProvider.getLoggedUser()).thenReturn(loggedInUser);
        when(tripProvider.getTrips(otherUser)).thenReturn(expectedTrips);

        List<Trip> actualTrips = tripService.getTripsByUser(otherUser);

        assertEquals(expectedTrips, actualTrips);
    }

    @Test
    public void shouldNotReturnTripsListWhenUserIsNotFriendWithOtherUser() {
        User loggedInUser = new User();
        User otherUser = new User();

        when(userSessionProvider.getLoggedUser()).thenReturn(loggedInUser);

        List<Trip> result = tripService.getTripsByUser(otherUser);

        assertTrue(result.isEmpty());
    }
}
