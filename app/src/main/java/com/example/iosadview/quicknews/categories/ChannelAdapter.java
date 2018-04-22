package com.example.iosadview.quicknews.categories;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.iosadview.quicknews.R;
import com.example.iosadview.quicknews.model.BaseResponse;

import java.util.ArrayList;

public class ChannelAdapter extends RecyclerView.Adapter<ChannelAdapter.ChannelViewHolder> {

    private Context mContext;
    private ArrayList<BaseResponse.Article> mChannelList;

    public ChannelAdapter(Context context, ArrayList<BaseResponse.Article> channellist) {
        this.mContext = context;
        this.mChannelList = channellist;
    }

    @NonNull
    @Override
    public ChannelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChannelViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_channel_name, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChannelViewHolder holder, int position) {
        Glide.with(mContext).load(mChannelList.get(position).getUrlToImage()).centerCrop()
                .into(holder.mChannelImage);
        holder.mChannelName.setText(mChannelList.get(position).getSource().getName());
    }

    @Override
    public int getItemCount() {
        return mChannelList.size();
    }

    public class ChannelViewHolder extends RecyclerView.ViewHolder {

        private ImageView mChannelImage;
        private TextView mChannelName;

        public ChannelViewHolder(View itemView) {
            super(itemView);
            mChannelImage = itemView.findViewById(R.id.img_android);
            mChannelName = itemView.findViewById(R.id.channel_name);
        }
    }
}
