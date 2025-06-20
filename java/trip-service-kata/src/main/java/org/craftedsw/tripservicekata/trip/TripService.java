package org.craftedsw.tripservicekata.trip;

import java.util.Collections;
import java.util.List;
import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSessionProvider;

public class TripService {

	private final UserSessionProvider userSessionProvider;
	private final TripProvider tripProvider;

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
			return tripProvider.findTripsByUser(user);
		}

		return Collections.emptyList();
	}
}
