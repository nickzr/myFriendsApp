package com.example.nikolaj.myfriends;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Nikolaj on 3/4/2016.
 */
public class FriendDetailsActivity extends Activity {
    private enum MediaType { IMAGE}

    private static final String LOGTAG = "FriendDetailsActivity";
    private final static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    //private final static int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 200;

    TextView f_name;
    TextView f_number;
    ImageView f_pic;
    Button f_takePic;
    Button f_home;

    Friend friend;
    File m_file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_details);

        Intent intent = getIntent();
        friend = (Friend) intent.getSerializableExtra("friend");

        setLayout();
    }

    private void setLayout() {
        f_name = (TextView) findViewById(R.id.txtFname);
        f_name.setText(friend.name);

        f_number = (TextView) findViewById(R.id.txtFnumber);
        f_number.setText(friend.phoneNumber);

        f_pic = (ImageView) findViewById(R.id.imgPic);

        if(friend.pictureFileName != null){
            File f = new File(friend.pictureFileName);
            f_pic.setImageURI(Uri.fromFile(f));
        }else{
            f_pic.setBackgroundResource(R.drawable.friend);
        }

        f_takePic = (Button) findViewById(R.id.btnPicture);
        f_takePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickTakePic();
            }
        });

        f_home = (Button) findViewById(R.id.btnFriendHome);
        f_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickShowFriendHome();
            }
        });
    }

    private void onClickShowFriendHome(){
                Intent intent = new Intent(FriendDetailsActivity.this, MapsActivity.class);
                intent.putExtra("friend", friend);
                startActivity(intent);
    }

    private void onClickTakePic(){
        // create Intent to take a picture and return control to the calling application
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        m_file = getOutputMediaFile(MediaType.IMAGE); // create a file to save the image
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(m_file)); // set the image file name

        log("file uri = " + Uri.fromFile(m_file).toString());

        // start the image capture Intent
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    /** Create a File for saving an image or video */
    private File getOutputMediaFile(MediaType mediaType){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "Camera01");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                log("failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String postfix = mediaType == MediaType.IMAGE ? "jpg" : "mp4";
        String prefix = mediaType == MediaType.IMAGE ? "IMG" : "VID";

        File mediaFile = new File(mediaStorageDir.getPath() +
                File.separator + prefix +
                "_"+ timeStamp + "." + postfix);

        return mediaFile;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                String filename = m_file.toString();
                showPictureTaken(filename);

            } else
            if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Canceled...", Toast.LENGTH_LONG).show();
                return;

            } else
                Toast.makeText(this, "Picture NOT taken - unknown error...", Toast.LENGTH_LONG).show();
        }
    }

    private void showPictureTaken(String filename) {

        File f = new File(filename);
        if (!f.exists()) {
            Log.d(LOGTAG, "file " + filename + " does not exist!");
            return;
        }
        f_pic.setBackgroundResource(0);
        f_pic.setImageURI(Uri.fromFile(f));
        //friend.pictureFileName = filename;
        //f_pic.setBackgroundColor(Color.RED);
        //m_filename.setText(filename);
        //scaleImage();
    }


    void log(String s)
    { Log.d(LOGTAG, s); }
}
