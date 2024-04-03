package shared;

public class User {
    private int userID;
    private String username;
    private String name;
    private String password;
    private String email;
    private String userType;
    private String status;
    private String bio;
    private byte[] profilePicture;

    // Constructor
    public User(int userID, String username, String name, String password, String email, String userType, String status, byte[] profilePicture, String bio) {
        this.userID = userID;
        this.username = username;
        this.name = name;
        this.password = password;
        this.email = email;
        this.userType = userType;
        this.status = status;
        this.profilePicture = profilePicture;
        this.bio = bio;
    }

    // Constructor
    public User(String username, String name, String password, String email, String userType, String status, byte[] profilePicture, String bio) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.email = email;
        this.userType = userType;
        this.status = status;
        this.bio = bio;
        this.profilePicture = profilePicture;
    }

    // Constructor
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    // Getters and setters
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }
}
