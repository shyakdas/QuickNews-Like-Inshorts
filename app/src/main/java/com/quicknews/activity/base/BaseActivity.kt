package com.quicknews.activity.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * BaseActivity will contain all the common function of which can be used in all the Activity of the
 * application. Those method which can be called from any of the Activity in Quick news should be
 * placed here
 */

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutIt())
    }

    /**
     * getLayout() will help us to get the layout id. As this is an abstract function,
     * those class will extend this base Activity class need to implement this method
     */

    abstract fun getLayoutIt(): Int

    /**
     * initViewModel() will be override  by every class which will extend this base fragment class
     */
    open fun initViewModel() {
        // Subclass will implement this method
    }
}