package application;

public class User {
    private String userID;
    private String password;
    private UserRole role;

    public User(String userID, String password, UserRole role) {
        this.userID = userID;
        this.password = password;
        this.role = role;
    }

    // Getters and Setters
    public String getUserID() {
        return userID;
    }

    public void setUsername(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}

