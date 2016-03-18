package com.example.nikolaj.myfriends;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

/**
 * Created by Nikolaj on 3/3/2016.
 */
public class Friend implements Serializable {
    public String name;
    public String phoneNumber;
    public String pictureFileName;
    //public LatLng friendHome;
    public double latitudeHome;
    public double longitudeHome;

    public Friend(String name, String phoneNumber){
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public Friend(String name, String phoneNumber, double latitudeHome, double longitudeHome){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.latitudeHome = latitudeHome;
        this.longitudeHome = longitudeHome;
    }

    /*public Friend(String name, String phoneNumber, LatLng home){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.friendHome = home;
    }*/

    public void setPictureFileName(String fileName){
        pictureFileName = fileName;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Number: " + phoneNumber;
    }
}
