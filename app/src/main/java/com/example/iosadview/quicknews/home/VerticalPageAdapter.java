package com.example.iosadview.quicknews.home;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.iosadview.quicknews.Interface.PostItemClickListener;
import com.example.iosadview.quicknews.R;
import com.example.iosadview.quicknews.model.BaseResponse;

import java.util.ArrayList;

class VerticlePagerAdapter extends PagerAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<BaseResponse.Article> mList;
    private TextView label, title;
    private ImageView imageView;
    private PostItemClickListener postItemClickListener;

    public VerticlePagerAdapter(Context context, ArrayList<BaseResponse.Article> mTotalList,
                                PostItemClickListener listener) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mList = mTotalList;
        this.postItemClickListener = listener;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = mLayoutInflater.inflate(R.layout.item_channel, container, false);
        label = itemView.findViewById(R.id.textView);
        imageView = itemView.findViewById(R.id.imageView);
        title = itemView.findViewById(R.id.title);
        label.setText(mList.get(position).getDescription());
        Glide.with(mContext).load(mList.get(position).getUrlToImage()).centerCrop().into(imageView);
        title.setText(mList.get(position).getTitle());
        container.addView(itemView);
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postItemClickListener.postClick(position, mList.get(position).getUrl());
            }
        });
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
