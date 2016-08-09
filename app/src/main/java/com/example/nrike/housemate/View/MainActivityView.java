package com.example.nrike.housemate.View;

import com.example.nrike.housemate.Model.Entity.User;

import java.util.List;

/**
 * Created by develapps15 on 9/8/16.
 */
public interface MainActivityView {

    public void LoadUser(List<User> users);

    public void updateUsers(List<User> users);

}
