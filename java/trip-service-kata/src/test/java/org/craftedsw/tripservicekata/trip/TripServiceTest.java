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
        TestableTripService testableTripService = new TestableTripService(null, List.of());

        assertThrows(UserNotLoggedInException.class, () -> testableTripService.getTripsByUser(user));
    }

    @Test
    void shouldReturnTripsWhenUsersAreFriends() {
        User loggedInUser = new User();
        User otherUser = new User();
        otherUser.addFriend(loggedInUser);
        otherUser.addTrip(new Trip());

        TestableTripService testableTripService = new TestableTripService(loggedInUser, otherUser.trips());

        List<Trip> trips = testableTripService.getTripsByUser(otherUser);

        assertEquals(1, trips.size());
    }

    @Test
    void shouldNotReturnTripsWhenUsersAreNotFriends() {
        User loggedInUser = new User();
        User otherUser = new User();
        otherUser.addTrip(new Trip());

        TestableTripService testableTripService = new TestableTripService(loggedInUser, otherUser.trips());

        List<Trip> trips = testableTripService.getTripsByUser(otherUser);

        assertEquals(0, trips.size());
    }

    private static class TestableTripService extends TripService {

        private final User dummyLoggedUser;
        private final List<Trip> dummyTrips;

        public TestableTripService(User dummyLoggedUser, List<Trip> dummyTrips) {
            this.dummyLoggedUser = dummyLoggedUser;
            this.dummyTrips = dummyTrips;
        }

        @Override
        protected User getLoggedUser() {
            return dummyLoggedUser;
        }

        @Override
        protected List<Trip> findTripsByUser(User user) {
            return dummyTrips;
        }
    }
}
