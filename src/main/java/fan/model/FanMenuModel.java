package fan.model;

import jdbc.FanJDBC;
import shared.User;

import java.util.List;

public class FanMenuModel {

    public FanMenuModel() {}

    public List<User> getIdolList() {
        return FanJDBC.getIdolList();
    }
} // end of FanMenuModel class
