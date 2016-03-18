package com.example.nikolaj.myfriends;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Friend> friendList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        friendList = new Friends().getFriendList();

        TextView txtFriends = (TextView) findViewById(R.id.txtFriends);
        txtFriends.setText("Friends: " + friendList.size());

        Button btnMain = (Button) findViewById(R.id.btnMain);

        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMainClick(v);
            }
        });
    }

    public void onMainClick(View view){
        Intent intent = new Intent(getApplicationContext(), FriendlistActivity.class);

        intent.putExtra("friendlist", friendList);

        startActivity(intent);
    }
}
