package authentication.model;

import jdbc.AuthenticationJDBC;
import jdbc.FanJDBC;
import shared.Fanmeet;
import shared.User;

import javax.xml.transform.sax.SAXResult;

public class SignUpModel {
    private String name;
    private String username;
    private String email;
    private String password;
    private String userType;

    public boolean signUp() throws Exception{
        User userSignUp = new User(username, name, password, email, userType, "Active", null, null);

        AuthenticationJDBC.signUp(userSignUp);
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
