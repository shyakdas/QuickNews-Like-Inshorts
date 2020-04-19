package com.quicknews.utils

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Build
import androidx.appcompat.content.res.AppCompatResources
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.graphics.drawable.DrawableCompat
import com.example.iosadview.quicknews.R
import saschpe.android.customtabs.CustomTabsHelper
import saschpe.android.customtabs.WebViewFallback

/**
 * QuickNewsUtils class will contain all the common methods of QuickNews Applications.
 */

class QuickNewsUtils {

    companion object {

        /**
         * openUrlInCustomTab(passYourActivity, String Url), this is a custom tab helper method,
         * By this we can open any URL of the application
         */
        fun openUrlInCustomTab(activity: Activity, URL: String?) {
            val customTabsIntent = getDefaultCustomTabsIntentBuilder(activity)
                    .setStartAnimations(activity, R.anim.enter_from_right, R.anim.exit_to_left)
                    .setExitAnimations(activity, R.anim.enter_from_left, R.anim.exit_to_right)
                    .build()
            CustomTabsHelper.addKeepAliveExtra(activity, customTabsIntent.intent)
            CustomTabsHelper.openCustomTab(
                    activity, customTabsIntent,
                    Uri.parse(URL),
                    WebViewFallback())
        }

        /**
         * getDefaultCustomTabsIntentBuilder(activity), will handle the toolbar function,
         * like, setting the tool bar title and tool bar color.
         */

        private fun getDefaultCustomTabsIntentBuilder(activity: Activity): CustomTabsIntent.Builder {
            val backArrow = getBitmapFromVectorDrawable(activity)
            return CustomTabsIntent.Builder()
                    .addDefaultShareMenuItem()
                    .setToolbarColor(activity.resources.getColor(R.color.colorPrimary))
                    .setShowTitle(true)
                    .setCloseButtonIcon(backArrow!!)
        }

        /**
         * getBitmapFromVectorDrawable(activity), to set back button or any icons in custom tab,
         * use this method to set.
         */

        private fun getBitmapFromVectorDrawable(activity: Activity?): Bitmap? {
            var drawable = AppCompatResources.getDrawable(activity!!, R.drawable.ic_arrow_back_white)
                    ?: return null
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                drawable = DrawableCompat.wrap(drawable).mutate()
            }
            val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth,
                    drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
            return bitmap
        }
    }
}