package com.example.android_homework1.contact;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.android_homework1.DeleteDialog;
import com.example.android_homework1.R;

import static com.example.android_homework1.R.string.confirm_delete;

public class CallDialog extends DialogFragment {
    public interface OnCallDialogInteractionListener{
        void onCallPositiveClick();
        void onCallNegativeClick();
    }
    private CallDialog.OnCallDialogInteractionListener mListener;

    public CallDialog(){

    }
    public CallDialog(CallDialog.OnCallDialogInteractionListener listener){
        mListener = listener;
    }

    public static CallDialog newInstance(CallDialog.OnCallDialogInteractionListener listener){
        CallDialog fragment = new CallDialog(listener);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(R.string.calling_msg);
        builder.setPositiveButton(R.string.confirm_call, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(mListener != null)
                    mListener.onCallPositiveClick();
            }
        });
        builder.setNegativeButton(R.string.cancel_call, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(mListener != null)
                    mListener.onCallNegativeClick();
            }
        });

        return builder.create();
    }
}