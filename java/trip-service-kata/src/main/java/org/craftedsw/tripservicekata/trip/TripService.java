package org.craftedsw.tripservicekata.trip;

import java.util.Collections;
import java.util.List;
import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.UserSession;
import org.craftedsw.tripservicekata.user.User;

public class TripService {

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		User loggedUser = getLoggedUser();

		if(loggedUser == null) {
			throw new UserNotLoggedInException();
		}

		if (user.isFriendsWith(loggedUser)) {
			return findTripsByUser(user);
		}

		return Collections.emptyList();
	}

	protected List<Trip> findTripsByUser(User user) {
		return TripDAO.findTripsByUser(user);
	}

	protected User getLoggedUser() {
		return UserSession.getInstance().getLoggedUser();
	}
}
