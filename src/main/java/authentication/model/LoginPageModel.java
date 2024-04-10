package authentication.model;

import jdbc.AuthenticationJDBC;
import jdbc.FanJDBC;
import shared.User;

public class LoginPageModel {
    private String username;
    private String password;


    public boolean login() throws Exception{
        User userLogin = new User(username, password);

        return AuthenticationJDBC.login(userLogin);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
