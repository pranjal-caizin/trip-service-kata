package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;
import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSessionProvider;

public class TripService {

	private final UserSessionProvider userSessionProvider;

	public TripService(UserSessionProvider userSessionProvider) {
		this.userSessionProvider = userSessionProvider;
	}

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		List<Trip> tripList = new ArrayList<>();
		User loggedUser = userSessionProvider.getLoggedUser();

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

}
