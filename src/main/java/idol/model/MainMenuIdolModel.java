package idol.model;

import shared.User;

public class MainMenuIdolModel {
    private User user;

    public MainMenuIdolModel(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
