package gr.aueb.android.barista.core.model;

public interface Command {

    /**
     * the emulatorId injected in the android test client
     * @return
     */
    String getSessionToken();

    void setSessionToken(String sessionToken);


}
