package com.example.android_homework1;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android_homework1.contact.ContactContent.Contact;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Contact}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyContactRecyclerViewAdapter extends RecyclerView.Adapter<MyContactRecyclerViewAdapter.ViewHolder> {

    private int selectedRingtone = -1;
    private final List<Contact> mValues;
    public interface InputEventsListener{
        void onItemClick(int position);
        void onItemLongClick(int position);
        void onItemDeleteClick(int position);


    }
    private InputEventsListener mListener;
    public void setInputEventsListener (InputEventsListener listener){
        mListener = listener;
    }

    public MyContactRecyclerViewAdapter(List<Contact> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mNameSurnameView.setText(mValues.get(position).nameSurname);
        //String soundPath = holder.mItem.soundPath;
        String picPath = holder.mItem.picPath;
        Context context = holder.mView.getContext();
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
            holder.mImageView.setImageDrawable(draw_avatar);
        }else {
            holder.mImageView.setImageDrawable(context.getDrawable(R.drawable.avatar_1));
        }


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(position);
            }

        });
        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mListener != null) {
                    mListener.onItemLongClick(position);
                }
                return true;
            }
        });

        holder.mDeleteButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemDeleteClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mNameSurnameView;
        public final ImageView mImageView;
        public final ImageButton mDeleteButtonView;
        public Contact mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.contact_id);
            mNameSurnameView = (TextView) view.findViewById(R.id.name_surname_textView);
            mImageView = view.findViewById(R.id.contact_imageView);
            mDeleteButtonView = view.findViewById(R.id.del_contact_imageButton);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mNameSurnameView.getText() + "'";
        }
    }
}