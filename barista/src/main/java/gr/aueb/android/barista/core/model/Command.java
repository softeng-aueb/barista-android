package gr.aueb.android.barista.core.model;

public interface Command {

    /**
     * the emulatorId injected in the android test client
     * @return
     */
    String getSessionToken();

    //String getCommandString();

    void setSessionToken(String sessionToken);


    CommandDTO getResetCommand();

    int getDelay();
    //void accept(CommandExecutor executor);

    //boolean isCompleted(CommandClient client);

    //void parseResult(Stream<String> resultLines);

}
