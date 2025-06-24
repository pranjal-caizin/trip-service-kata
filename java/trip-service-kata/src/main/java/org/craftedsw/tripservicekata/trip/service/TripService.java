package org.craftedsw.tripservicekata.trip.service;

import java.util.Collections;
import java.util.List;
import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.trip.TripProvider;
import org.craftedsw.tripservicekata.trip.model.Trip;
import org.craftedsw.tripservicekata.user.model.User;
import org.craftedsw.tripservicekata.user.UserSessionProvider;

public class TripService {

	private UserSessionProvider userSessionProvider;
	private TripProvider tripProvider;

	//TODO: Add default constructor

	public TripService() {}

	public TripService(UserSessionProvider userSessionProvider, TripProvider tripProvider) {
		this.userSessionProvider = userSessionProvider;
		this.tripProvider = tripProvider;
	}

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		User loggedUser = userSessionProvider.getLoggedUser();

		if(loggedUser ==  null) {
			throw new UserNotLoggedInException();
		}

		if (user.isFriendWith(loggedUser)) {
			return tripProvider.getTrips(user);
		}

		return Collections.emptyList();
	}
}
