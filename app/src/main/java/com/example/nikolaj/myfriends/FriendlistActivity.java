package com.example.nikolaj.myfriends;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class FriendlistActivity extends ListActivity {
    public ArrayList<Friend> friendList;
    //public ListView lvFriends;
    public FriendArrayAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_friendlist);

        //Retrieve data from our intent
        Intent intent = getIntent();
        friendList = (ArrayList<Friend>) intent.getSerializableExtra("friendlist");
        myAdapter = new FriendArrayAdapter(friendList);

        //lvFriends = (ListView) findViewById(R.id.listFriends);
    //ArrayA            dapter<Friend> friendArrayAdapter = new ArrayAdapter<Friend>(this, android.R.layout.simple_list_item_1, friendList);

        setListAdapter(myAdapter);
        //lvFriends.setAdapter(myAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Friend friend = myAdapter.getItem(position);

        Intent intent = new Intent(getApplicationContext(), FriendDetailsActivity.class);

        intent.putExtra("friend", friend);

        startActivity(intent);
        //setContentView(R.layout.activity_friend_details, friend);
    }

    private class FriendArrayAdapter extends BaseAdapter{

        private ArrayList<Friend> myFriendList;
        private LayoutInflater inflater;

        public FriendArrayAdapter(ArrayList<Friend> friendList){
            myFriendList = friendList;
            inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE); // takes the XML-files and creates different View-objects from its contents.
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            if( view == null ){
                //Create the view
                view = inflater.inflate(R.layout.activity_friendlist, parent, false);
            }

            //Set changes, text etc.
            TextView friendName = (TextView) view.findViewById(R.id.txtFLname);
            TextView friendNumber = (TextView) view.findViewById(R.id.txtFLnumber);
            ImageView friendPhoto = (ImageView) view.findViewById(R.id.imgIcon);

            Friend temp = friendList.get(position);

            friendName.setText(temp.name);
            friendNumber.setText(temp.phoneNumber);

            if(temp.pictureFileName != null){
                File f = new File(temp.pictureFileName);
                friendPhoto.setImageURI(Uri.fromFile(f));
            }else{
                friendPhoto.setBackgroundResource(R.drawable.friend);
            }

            return view;
        }

        @Override
        public int getCount() {
            return myFriendList.size();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public Friend getItem(int position) {
            return myFriendList.get(position);
        }
    }
}
