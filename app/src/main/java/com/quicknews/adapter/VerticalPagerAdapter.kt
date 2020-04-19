package com.quicknews.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.iosadview.quicknews.R
import com.quicknews.listener.ItemClickListener
import com.quicknews.model.ArticleData
import kotlinx.android.synthetic.main.item_channel_news.view.*
import java.util.*


class VerticalPagerAdapter(var context: Context, var mTotalList: ArrayList<ArticleData>,
                           var listener: ItemClickListener) : PagerAdapter() {

    private var mLayoutInflater: LayoutInflater? = null

    init {
        mLayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getCount(): Int {
        return mTotalList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): View {
        val itemView = mLayoutInflater?.inflate(R.layout.item_channel_news, container, false)
        itemView?.title?.text = mTotalList[position].description
        Glide.with(context).load(mTotalList[position].urlToImage).centerCrop().into(itemView!!.imageView)
        itemView.title?.text = mTotalList[position].title
        container.addView(itemView)
        itemView.title?.setOnClickListener { listener.onClick(position, mTotalList[position].url!!) }
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView((`object`) as LinearLayout)
    }
}