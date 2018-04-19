package com.example.iosadview.quicknews.Utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.content.res.AppCompatResources;

import com.example.iosadview.quicknews.R;

import saschpe.android.customtabs.CustomTabsHelper;
import saschpe.android.customtabs.WebViewFallback;

public abstract class AppUtis {

    public static void creatorCustomTabs(Activity activity, String URL) {
        CustomTabsIntent customTabsIntent = getDefaultCustomTabsIntentBuilder(activity)
                .setStartAnimations(activity, R.anim.enter_from_right, R.anim.exit_to_left)
                .setExitAnimations(activity, R.anim.enter_from_left, R.anim.exit_to_right)
                .build();

        CustomTabsHelper.addKeepAliveExtra(activity, customTabsIntent.intent);

        CustomTabsHelper.openCustomTab(
                activity, customTabsIntent,
                Uri.parse(URL),
                new WebViewFallback());
    }

    public static CustomTabsIntent.Builder getDefaultCustomTabsIntentBuilder(Activity activity) {
        Bitmap backArrow = getBitmapFromVectorDrawable(R.drawable.ic_arrow_back_white, activity);
        return new CustomTabsIntent.Builder()
                .addDefaultShareMenuItem()
                .setToolbarColor(activity.getResources().getColor(R.color.colorPrimary))
                .setShowTitle(true)
                .setCloseButtonIcon(backArrow);
    }

    public static Bitmap getBitmapFromVectorDrawable(final @DrawableRes int drawableId, Activity activity) {
        Drawable drawable = AppCompatResources.getDrawable(activity, drawableId);
        if (drawable == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = (DrawableCompat.wrap(drawable)).mutate();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }
}
