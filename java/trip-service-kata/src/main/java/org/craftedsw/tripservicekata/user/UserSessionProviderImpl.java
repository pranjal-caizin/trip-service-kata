package org.craftedsw.tripservicekata.user;

import org.craftedsw.tripservicekata.user.model.User;

public class UserSessionProviderImpl implements UserSessionProvider {

    @Override
    public User getLoggedUser() {
        return UserSession.getInstance().getLoggedUser();
    }
}
