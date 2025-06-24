package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.trip.model.Trip;
import org.craftedsw.tripservicekata.trip.service.TripService;
import org.craftedsw.tripservicekata.user.model.User;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TripServiceTest {

    @Test
    void shouldFailWhenUserNotLoggedIn() {
        User user = new User();
        TestableTripService testableTripService = new TestableTripService();

        assertThrows(UserNotLoggedInException.class, () -> testableTripService.getTripsByUser(user));
    }

    @Test
    void shouldReturnTripsWhenUsersAreFriends() {
        User loggedInUser = new User();
        User otherUser = new User();
        TestableTripService testableTripService = new TestableTripService();

        otherUser.addFriend(loggedInUser);
        otherUser.addTrip(new Trip());

        List<Trip> trips = testableTripService.findTripsByUser(otherUser);

        assertEquals(1, trips.size());
    }

    private static class TestableTripService extends TripService {

        @Override
        protected User getLoggedUser() {
            return null;
        }

        @Override
        protected List<Trip> findTripsByUser(User user) {
            return user.trips();
        }
    }
}
