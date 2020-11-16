package com.example.android_homework1;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android_homework1.contact.ContactContent;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DisplayContact#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DisplayContact extends Fragment {
    TextView contactNameSurnameTextView;
    TextView contactPhoneTextView;
    TextView contactRingtoneTextView;
    ImageView contactAvatarImageView;

    public DisplayContact() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DisplayContact newInstance() {
        DisplayContact fragment = new DisplayContact();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_display_contact, container, false);
        contactNameSurnameTextView = view.findViewById(R.id.dispNameSurnameTextView);
        contactPhoneTextView = view.findViewById(R.id.dispPhoneTextView);
        contactRingtoneTextView = view.findViewById(R.id.dispRingtoneTextView);
        contactAvatarImageView = view.findViewById(R.id.dispAvatarImageView);

        Bundle arguments = getArguments();
        if(arguments != null){
            if(arguments.containsKey(getString(R.string.contact_position_key))){
                int position = arguments.getInt(getString(R.string.contact_position_key));
                displayContact(position);
            }
        }
        return view;
    }

    public void displayContact (int position){
        ContactContent.Contact contact = ContactContent.ITEMS.get(position);
        contactNameSurnameTextView.setText(contact.nameSurname);
        contactPhoneTextView.setText(contact.phoneNumber);
        String picPath = contact.picPath;
        String soundPath = contact.soundPath;
        Context context = getContext();
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

            contactAvatarImageView.setImageDrawable(draw_avatar);
        }else {
            contactAvatarImageView.setImageDrawable(context.getDrawable(R.drawable.avatar_1));
        }

        if(soundPath != null && !soundPath.isEmpty()){

            switch (soundPath){
                case "Ringtone 2":
                    contactRingtoneTextView.setText(R.string.ringtone_2);
                    break;
                case "Ringtone 3":
                    contactRingtoneTextView.setText(R.string.ringtone_3);
                    break;
                case "Ringtone 1":
                default:
                    contactRingtoneTextView.setText(R.string.ringtone_1);
            }
        }else {
            contactRingtoneTextView.setText(R.string.ringtone_1);
        }
    }
}