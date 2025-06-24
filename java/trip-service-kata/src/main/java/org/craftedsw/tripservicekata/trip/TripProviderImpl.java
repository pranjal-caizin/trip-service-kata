package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.trip.model.Trip;
import org.craftedsw.tripservicekata.user.model.User;
import java.util.List;

public class TripProviderImpl implements TripProvider{

    @Override
    public List<Trip> getTrips(User user) {
        return TripDAO.findTripsByUser(user);
    }
}
