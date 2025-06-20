package org.craftedsw.tripservicekata.user;

public class RealUserSessionProvider implements UserSessionProvider {

    @Override
    public User getLoggedUser() {
        return UserSession.getInstance().getLoggedUser();
    }
}
