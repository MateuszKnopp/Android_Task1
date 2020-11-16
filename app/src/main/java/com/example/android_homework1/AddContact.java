package com.example.android_homework1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.android_homework1.contact.ContactContent;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddContact#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddContact extends Fragment {



    public AddContact() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static AddContact newInstance() {
        AddContact fragment = new AddContact();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_add_contact, container, false);
        view.findViewById(R.id.addButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ((EditText)view.findViewById(R.id.contactNameEditText)).getText().toString();
                String surname = ((EditText)view.findViewById(R.id.contactSurnameEditText)).getText().toString();
                String nameSurname = name + " " + surname;
                String phoneNumber = ((EditText)view.findViewById(R.id.contactPhoneEditText)).getText().toString();
                String ringtone = ((Spinner)view.findViewById(R.id.contactRingtoneSpinner)).getSelectedItem().toString();
                String avatar = ((Spinner)view.findViewById(R.id.contactAvatarSpinner)).getSelectedItem().toString();

                if(name.isEmpty())
                    nameSurname = "John Doe";
                if(phoneNumber.isEmpty())
                    phoneNumber = "123123123";

                ContactContent.addItem(new ContactContent.Contact(String.valueOf(ContactContent.ITEMS.size()+1), nameSurname, phoneNumber, ringtone, avatar));
                NavHostFragment.findNavController(AddContact.this).navigate(R.id.action_addContactFragment_to_contactFragment);
            }
        });
        view.findViewById(R.id.cancelButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(AddContact.this).navigate(R.id.action_addContactFragment_to_contactFragment);
            }
        });

        return view;
    }
}