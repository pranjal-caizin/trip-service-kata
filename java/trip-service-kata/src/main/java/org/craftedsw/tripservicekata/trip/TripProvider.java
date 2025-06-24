package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.trip.model.Trip;
import org.craftedsw.tripservicekata.user.model.User;
import java.util.List;

public interface TripProvider {
    List<Trip> getTrips(User user);
}
