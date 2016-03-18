package com.example.nikolaj.myfriends;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by Nikolaj on 3/3/2016.
 */
public class Friends {
    public ArrayList<Friend> friendList;

    public Friends(){
        friendList = new ArrayList<Friend>();

        seedFriendlist();
    }

    public ArrayList<Friend> getFriendList(){
        return friendList;
    }

    private void seedFriendlist() {
        /*friendList.add(new Friend("Thomas", "12 34 45 56"));
        friendList.add(new Friend("Kevin", "21 43 65 87"));
        friendList.add(new Friend("Dilan", "32 54 26 12"));
        friendList.add(new Friend("Morten", "18 69 26 32"));
        friendList.add(new Friend("Rimon", "28 79 26 42"));*/

        friendList.add(new Friend("Thomas", "12 34 45 56", 55.468705, 8.445958));
        friendList.add(new Friend("Kevin", "21 43 65 87", 55.485472, 8.453828));
        friendList.add(new Friend("Dilan", "32 54 26 12", 55.475116, 8.414973));
        friendList.add(new Friend("Morten", "18 69 26 32", 55.471607, 8.449576));
        friendList.add(new Friend("Rimon", "28 79 26 42", 55.483337, 8.494026));
    }
}
