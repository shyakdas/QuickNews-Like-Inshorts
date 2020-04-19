package com.quicknews.adapter.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.iosadview.quicknews.R
import com.quicknews.listener.ItemClickListener
import com.quicknews.model.ArticleData
import kotlinx.android.synthetic.main.item_channel_news.view.*
import java.util.*

/**
 * VerticalPagerAdapter(context, listOfData, listener), this class is responsible to create a
 * swipe recycle View as like (InShorts), For this we have implemented PagerAdapter with a View Pager.
 */

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
        // Inflate the item view here
        val itemView = mLayoutInflater?.inflate(R.layout.item_channel_news, container, false)
        setView(itemView, position, container)
        return itemView!!
    }

    /**
     * setView(itemView, position,Your Container)
     * This method, will set the data into the view;
     */
    private fun setView(itemView: View?, position: Int, container: ViewGroup) {
        itemView?.news_description?.text = mTotalList[position].description
        itemView?.news_text?.text = mTotalList[position].content
        Glide.with(context).load(mTotalList[position].urlToImage).centerCrop().into(itemView!!.news_image)
        itemView.news_description?.text = mTotalList[position].title
        container.addView(itemView)
        itemView.news_description?.setOnClickListener { listener.onClick(position, mTotalList[position].url!!) }
    }

    /**
     * destroyItem(container,position,object);
     * To remove view from list, you can call this method
     */
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView((`object`) as ConstraintLayout)
    }
}