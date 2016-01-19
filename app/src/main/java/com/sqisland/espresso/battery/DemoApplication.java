package com.sqisland.espresso.battery;

import android.app.Application;

public class DemoApplication extends Application {
  private final Injection injection = createInjection();

  protected Injection createInjection() {
    return new Injection(this);
  }

  public Injection getInjection() {
    return injection;
  }
}