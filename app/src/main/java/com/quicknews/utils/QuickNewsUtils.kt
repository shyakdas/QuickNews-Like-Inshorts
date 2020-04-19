package com.quicknews.utils

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Build
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.graphics.drawable.DrawableCompat
import com.example.iosadview.quicknews.R
import saschpe.android.customtabs.CustomTabsHelper
import saschpe.android.customtabs.WebViewFallback

class QuickNewsUtils {

    companion object {
        fun creatorCustomTabs(activity: Activity, URL: String?) {
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

        private fun getDefaultCustomTabsIntentBuilder(activity: Activity): CustomTabsIntent.Builder {
            val backArrow = getBitmapFromVectorDrawable(R.drawable.ic_arrow_back_white, activity)
            return CustomTabsIntent.Builder()
                    .addDefaultShareMenuItem()
                    .setToolbarColor(activity.resources.getColor(R.color.colorPrimary))
                    .setShowTitle(true)
                    .setCloseButtonIcon(backArrow!!)
        }

        private fun getBitmapFromVectorDrawable(@DrawableRes drawableId: Int, activity: Activity?): Bitmap? {
            var drawable = AppCompatResources.getDrawable(activity!!, drawableId)
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