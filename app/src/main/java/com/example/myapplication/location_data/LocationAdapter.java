package com.example.myapplication.location_data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.activity.MainActivity;
import com.example.myapplication.friends.Friend;

import java.util.Collections;
import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {
    private List<LocationData> friends = Collections.emptyList();

    public void setLocationData(List<LocationData> newFriends) {
        this.friends.clear();
        this.friends = newFriends;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.friend_tag, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationAdapter.ViewHolder holder, int position) {
        holder.setFriend(friends.get(position));
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }

    @Override
    public long getItemId(int position) { return friends.get(position).public_code.hashCode(); }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView textView;
        private LocationData friend;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.name_tag);
        }

        public LocationData getFriend() { return friend; }

        public void setFriend(LocationData friend) {
            this.friend = friend;
            this.textView.setText((friend.label));
        }
    }
}
