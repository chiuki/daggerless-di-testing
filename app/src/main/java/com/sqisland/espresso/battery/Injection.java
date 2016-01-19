package com.sqisland.espresso.battery;

import android.content.Context;

public class Injection {
  private final Context context;
  private BatteryReader batteryReader = null;

  public Injection(Context context) {
    this.context = context;
  }

  protected BatteryReader createBatteryReader() {
    return new BatteryReader(provideContext());
  }

  /***** Public provide methods *****/

  public Context provideContext() {
    return context;
  }

  public BatteryReader provideBatteryReader() {
    if (batteryReader == null) {
      batteryReader = createBatteryReader();
    }
    return batteryReader;
  }
}