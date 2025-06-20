package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.user.User;

import java.util.List;

public interface TripProvider {
    List<Trip> findTripsByUser(User user);
}
