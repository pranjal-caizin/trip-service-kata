package org.craftedsw.tripservicekata.trip.service;

import java.util.Collections;
import java.util.List;
import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.trip.TripDAO;
import org.craftedsw.tripservicekata.trip.model.Trip;
import org.craftedsw.tripservicekata.user.UserSession;
import org.craftedsw.tripservicekata.user.model.User;

public class TripService {

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		User loggedUser = getLoggedUser();

		if(loggedUser == null) {
			throw new UserNotLoggedInException();
		}

		if (areFriends(user, loggedUser)) {
			return findTripsByUser(user);
		}

		return Collections.emptyList();
	}

	private boolean areFriends(User user, User loggedUser) {
		return user.getFriends().contains(loggedUser);
	}

	protected List<Trip> findTripsByUser(User user) {
		return TripDAO.findTripsByUser(user);
	}

	protected User getLoggedUser() {
		return UserSession.getInstance().getLoggedUser();
	}
}
