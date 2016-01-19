package com.sqisland.espresso.battery;

import android.support.annotation.ColorInt;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

public abstract class CustomViewMatchers {
  public static Matcher<View> withTextColor(@ColorInt final int color) {
    return new BoundedMatcher<View, TextView>(TextView.class) {
      @Override public void describeTo(Description description) {
        description.appendText("has text color " + String.format("#%06X", (0xFFFFFF & color)));
      }
      @Override public boolean matchesSafely(TextView textView) {
        Log.e("chiuki", String.format("#%06X", (0xFFFFFF & textView.getCurrentTextColor())));
        return textView.getCurrentTextColor() == color;
      }
    };
  }
}