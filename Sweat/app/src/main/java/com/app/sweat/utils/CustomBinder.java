package com.app.sweat.utils;

import android.animation.ValueAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by mayank on January 08 2020
 */
public class CustomBinder {
    /**
     * This method animates the progress for horizontal progressbar.
     *
     * @param progressBar Progressbar view
     * @param value       The value of progress that needs to be animated
     * @param max         The maximum value for the given progress bar
     */
    @BindingAdapter({"setProgress", "maxValue"})
    public static void animateProgress(final ProgressBar progressBar, int value, int max) {
        progressBar.setMax(max * 1000);
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, value * 1000);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (int) valueAnimator.getAnimatedValue();
                progressBar.setProgress(value);
            }
        });

        valueAnimator.start();
    }

    /**
     * This method displays image from the url directly to the given imageview.
     *
     * @param imageView ImageView where the image needs to be displayed
     * @param url       Image url
     */
    @BindingAdapter("setImage")
    public static void setTrainerImage(final ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .priority(Priority.IMMEDIATE)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }
}
