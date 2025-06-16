package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class TripService {

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		List<Trip> tripList = new ArrayList<>();
		User loggedUser = UserSession.getInstance().getLoggedUser();

		if(loggedUser ==  null) {
			throw new UserNotLoggedInException();
		}

		boolean isFriend = user.getFriends().contains(loggedUser);
		if (isFriend) {
			tripList = TripDAO.findTripsByUser(user);
		}

		return tripList;
	}

}
