package org.craftedsw.tripservicekata.user.model;

import java.util.ArrayList;
import java.util.List;
import org.craftedsw.tripservicekata.trip.model.Trip;

public class User {

	private final List<Trip> trips = new ArrayList<>();
	private final List<User> friends = new ArrayList<>();

	public boolean isFriendWith(User otherUser) {
		return friends.contains(otherUser);
	}

	public List<User> getFriends() {
		return friends;
	}
	
	public void addFriend(User user) {
		friends.add(user);
	}

	public void addTrip(Trip trip) {
		trips.add(trip);
	}
	
	public List<Trip> trips() {
		return trips;
	}

}
