package com.example.nrike.housemate.Presenter.MainActivity;

import com.example.nrike.housemate.Model.Entity.User;

import java.util.List;

/**
 * Created by develapps15 on 9/8/16.
 */
public interface MainActivityPresenterView {

    public void loadUserList(List<User> users);

    public void updateUsers(List<User> users);

}
