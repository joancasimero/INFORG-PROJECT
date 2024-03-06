package models;

public class SharedData {
    private static SharedData instance;
    private String loggedInUsername;

    private SharedData() {
        // Private constructor to ensure only one instance of SharedData
    }

    public static SharedData getInstance() {
        if (instance == null) {
            instance = new SharedData();
        }
        return instance;
    }

    public String getLoggedInUsername() {
        return loggedInUsername;
    }

    public void setLoggedInUsername(String username) {
        loggedInUsername = username;
    }
}