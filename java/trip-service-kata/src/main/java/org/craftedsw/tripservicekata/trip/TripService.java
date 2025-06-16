package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class TripService {

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		List<Trip> tripList = new ArrayList<>();
		User loggedUser = getLoggedUser();

		if(loggedUser ==  null) {
			throw new UserNotLoggedInException();
		}

		if (isFriend(user, loggedUser)) {
			tripList = TripDAO.findTripsByUser(user);
		}

		return tripList;
	}

	private static boolean isFriend(User user, User loggedUser) {
		return user.getFriends().contains(loggedUser);
	}

	private static User getLoggedUser() {
		return UserSession.getInstance().getLoggedUser();
	}

}
