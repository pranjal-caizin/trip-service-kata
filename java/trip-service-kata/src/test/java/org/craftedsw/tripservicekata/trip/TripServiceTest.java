package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.trip.service.TripService;
import org.craftedsw.tripservicekata.user.model.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TripServiceTest {

    @Test
    void shouldFailWhenUserNotLoggedIn() {
        User user = new User();
        TestableTripService testableTripService = new TestableTripService();

        assertThrows(UserNotLoggedInException.class, () -> testableTripService.getTripsByUser(user));
    }

    private static class TestableTripService extends TripService {

        @Override
        protected User getLoggedUser() {
            return null;
        }
    }
}
