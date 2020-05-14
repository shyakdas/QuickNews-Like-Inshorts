package com.quicknews.fragment.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * BaseFragment will contain all the common function of which can be used in all the fragment of the
 * application. Those method which can be called from any of the fragment in Quick news should be
 * placed here
 */

abstract class BaseFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initViewModel()
        return bindView(inflater, container, savedInstanceState)
    }

    /**
     * This bindView is placed here, which will help us to implement data binding into fragments
     */

    open fun bindView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return layoutInflater.inflate(getLayout(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    /**
     * initView(view) will be override  by every class which will extend this base fragment class
     */
    open fun initView(view: View) {
    }

    /**
     * initViewModel() will be override  by every class which will extend this base fragment class
     */
    open fun initViewModel() {

    }

    /**
     * getLayout() will help us to get the layout id. As this is an abstract function,
     * those class will extend this base fragment class need to implement this method
     */
    abstract fun getLayout(): Int
}