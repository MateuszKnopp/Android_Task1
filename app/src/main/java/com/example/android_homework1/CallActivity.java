package com.example.android_homework1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android_homework1.contact.ContactContent;

public class CallActivity extends AppCompatActivity implements View.OnClickListener {
    private MediaPlayer callSignalPlayer;
    static private Uri [] ringtones;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        Intent receivedIntent = getIntent();
        int position = receivedIntent.getIntExtra(getString(R.string.contact_position_key),0 );
        ContactContent.Contact contact = ContactContent.ITEMS.get(position);


        TextView callTextView = findViewById(R.id.callTextView);
        callTextView.setText(getString(R.string.calling_msg) + contact.nameSurname);

        ImageView callAvatarImageView = findViewById(R.id.callAvatarImageView);
        ImageButton dismissCallImageButton = findViewById(R.id.dismissCallImageButton);
        dismissCallImageButton.setOnClickListener(this);

        String picPath = contact.picPath;
        String soundPath = contact.soundPath;
        Context context = getApplicationContext();
        if(picPath != null && !picPath.isEmpty()){
            Drawable draw_avatar;
            switch (picPath){
                case "Avatar 2":
                    draw_avatar = context.getDrawable(R.drawable.avatar_2);
                    break;
                case "Avatar 3":
                    draw_avatar = context.getDrawable(R.drawable.avatar_3);
                    break;
                case "Avatar 1":
                default:
                    draw_avatar = context.getDrawable(R.drawable.avatar_1);
            }

            callAvatarImageView.setImageDrawable(draw_avatar);
        }else {
            callAvatarImageView.setImageDrawable(context.getDrawable(R.drawable.avatar_1));
        }

        ringtones = new Uri[3];
        ringtones[0] = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.ring01);
        ringtones[1] = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.ring04);
        ringtones[2] = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.ring03);
        if(soundPath != null && !soundPath.isEmpty()){

            switch (soundPath){
                case "Ringtone 2":
                    callSignalPlayer = MediaPlayer.create(this, ringtones[1]);
                    break;
                case "Ringtone 3":
                    callSignalPlayer = MediaPlayer.create(this, ringtones[2]);
                    break;
                case "Ringtone 1":
                default:
                    callSignalPlayer = MediaPlayer.create(this, ringtones[0]);
            }
        }else {
            callSignalPlayer = MediaPlayer.create(this, ringtones[0]);
        }

        callSignalPlayer.start();
        callSignalPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                callSignalPlayer.start();
            }
        });
    }
    @Override
    public void onPause() {
        super.onPause();
        callSignalPlayer.reset();
    }
    @Override
    public void onClick(View v) {
        callSignalPlayer.reset();
        finish();
    }
}