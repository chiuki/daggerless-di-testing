package com.sqisland.espresso.battery;

public class MainActivityInjector implements Injector<MainActivity> {
  @Override
  public void inject(Injection injection, MainActivity object) {
    object.batteryReader = injection.provideBatteryReader();
  }
}
