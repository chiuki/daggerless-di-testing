package com.sqisland.espresso.battery;

import android.app.Instrumentation;
import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.v4.content.ContextCompat;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.sqisland.espresso.battery.CustomViewMatchers.withTextColor;
import static org.mockito.Mockito.times;

public class MainActivityTest {
  @Rule
  public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(
      MainActivity.class,
      true,     // initialTouchMode
      false);   // launchActivity. False so we can set up mocks per test method

  protected BatteryReader batteryReader;

  @Before
  public void setUp() throws Exception {
    Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
    MockDemoApplication app
        = (MockDemoApplication) instrumentation.getTargetContext().getApplicationContext();
    Injection injection = app.getInjection();
    injection.inject(this);
    Mockito.reset(batteryReader);
  }

  @Test
  public void half() {
    Mockito.when(batteryReader.getBatteryPercent()).thenReturn(50f);

    activityRule.launchActivity(null);
    verifyTextAndColor("50%", R.color.battery_ok);

    Mockito.verify(batteryReader, times(1)).getBatteryPercent();
  }

  @Test
  public void low() {
    Mockito.when(batteryReader.getBatteryPercent()).thenReturn(15f);

    activityRule.launchActivity(null);
    verifyTextAndColor("15%", R.color.battery_low);

    Mockito.verify(batteryReader, times(1)).getBatteryPercent();
  }

  @Test
  public void dropping() {
    Mockito.when(batteryReader.getBatteryPercent())
        .thenReturn(20f)
        .thenReturn(10f);

    activityRule.launchActivity(null);

    verifyTextAndColor("20%", R.color.battery_ok);

    onView(withId(R.id.battery))
        .perform(click());

    verifyTextAndColor("10%", R.color.battery_low);

    Mockito.verify(batteryReader, times(2)).getBatteryPercent();
  }

  private void verifyTextAndColor(String text, @ColorRes int colorId) {
    Context context = InstrumentationRegistry.getTargetContext();
    int expectedColor = ContextCompat.getColor(context, colorId);

    onView(withId(R.id.battery))
        .check(matches(withText(text)))
        .check(matches(withTextColor(expectedColor)));
  }
}