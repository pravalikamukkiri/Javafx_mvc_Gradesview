package application;

/*******
 * <p> Title: User Class. </p>
 * 
 * <p>  Description: The User class represents a user in the GradeTracker application. It includes information
		about the user's unique ID, password, and role within the system</p>
 * 
 * <p> Copyright: Pravalika Mukkiri © 2023 </p>
 * 
 * @author Pravalika Mukkiri
 * 
 * @version 1.00	2023-08-01 The JavaFX-based GradeTracker
 * 
 */
public class User {
    private String userID; // A String that represents the unique ID of the user.
    private String password; // A String that stores the password of the user for authentication.
    private UserRole role; //  A UserRole enum that represents the role of the user in the system (e.g., professor or student).

    /***
     *  Initializes a new User with the specified userID, password, and role.
     * @param userID
     * @param password
     * @param role
     */
    public User(String userID, String password, UserRole role) {
        this.userID = userID;
        this.password = password;
        this.role = role;
    }

    //  Returns the unique ID of the user.
    public String getUserID() {
        return userID;
    }

    // Sets the unique ID of the user to the specified value.
    public void setUsername(String userID) {
        this.userID = userID;
    }

    // Returns the password of the user.
    public String getPassword() {
        return password;
    }

    //  Sets the password of the user to the specified value.
    public void setPassword(String password) {
        this.password = password;
    }

    //  Returns the role of the user (e.g., professor or student) as a UserRole enum.
    public UserRole getRole() {
        return role;
    }

    // Sets the role of the user to the specified value (e.g., professor or student).
    public void setRole(UserRole role) {
        this.role = role;
    }
}

