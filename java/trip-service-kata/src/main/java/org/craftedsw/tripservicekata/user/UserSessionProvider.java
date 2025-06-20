package org.craftedsw.tripservicekata.user;

import org.craftedsw.tripservicekata.user.model.User;

public interface UserSessionProvider {
    User getLoggedUser();
}
