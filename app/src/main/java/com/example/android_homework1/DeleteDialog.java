package com.example.android_homework1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import static com.example.android_homework1.R.string.confirm_delete;

public class DeleteDialog extends DialogFragment {
    public interface OnDeleteDialogInteractionListener{
        void onDialogPositiveClick();
        void onDialogNegativeClick();
    }
    private OnDeleteDialogInteractionListener mListener;

    public DeleteDialog(){

    }
    public DeleteDialog(OnDeleteDialogInteractionListener listener){
        mListener = listener;
    }

    public static DeleteDialog newInstance(OnDeleteDialogInteractionListener listener){
        DeleteDialog fragment = new DeleteDialog(listener);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(R.string.delete_contact_msg);
        builder.setPositiveButton(confirm_delete, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(mListener != null)
                    mListener.onDialogPositiveClick();
            }
        });
        builder.setNegativeButton(R.string.cancel_delete, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(mListener != null)
                    mListener.onDialogNegativeClick();
            }
        });

        return builder.create();
    }
}
