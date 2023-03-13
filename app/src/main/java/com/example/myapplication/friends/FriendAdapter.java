package com.example.myapplication.friends;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.Collections;
import java.util.List;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.ViewHolder> {
    private List<Friend> friends = Collections.emptyList();

    public void setFriends(List<Friend> newFriends) {
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
    public void onBindViewHolder(@NonNull FriendAdapter.ViewHolder holder, int position) {
        holder.setFriend(friends.get(position));
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }

    @Override
    public long getItemId(int position) { return friends.get(position).id; }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView textView;
        private Friend friend;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.name_tag);
        }

        public Friend getFriend() { return friend; }

        public void setFriend(Friend friend) {
            this.friend = friend;
            this.textView.setText((friend.getUsername()));
        }
    }
}
