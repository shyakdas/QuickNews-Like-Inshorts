package com.example.iosadview.quicknews.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.iosadview.quicknews.R;
import com.example.iosadview.quicknews.model.BaseResponse;

import java.util.ArrayList;
import java.util.List;

public class NewsChannelAdapter extends RecyclerView.Adapter<NewsChannelAdapter.NewChannelViewHolder> {

    private Context context;
    private List<BaseResponse.Article> mChannelList;

    public NewsChannelAdapter(Context context) {
        this.context = context;
        mChannelList = new ArrayList<>();
    }

    public void addAll(List<BaseResponse.Article> categories) {
        this.mChannelList.clear();
        this.mChannelList.addAll(categories);
        Log.e("TAG", "listSize==" + mChannelList.size());
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewChannelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewChannelViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_channel, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewChannelViewHolder holder, int position) {
        Log.e("TAG", "onBindCalled");
        holder.mChannelName.setText(mChannelList.get(position).getSource().getName());
        Glide.with(context).load(mChannelList.get(position).getUrlToImage()).centerCrop().into(holder.mChannelImage);
    }

    @Override
    public int getItemCount() {
        return mChannelList.size();
    }

    public class NewChannelViewHolder extends RecyclerView.ViewHolder {

        private ImageView mChannelImage;
        private TextView mChannelName;

        public NewChannelViewHolder(View itemView) {
            super(itemView);
            mChannelImage = itemView.findViewById(R.id.channelImage);
            mChannelName = itemView.findViewById(R.id.channelName);
        }
    }
}
