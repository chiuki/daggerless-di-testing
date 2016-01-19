package com.sqisland.espresso.battery;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

public class BatteryReader {
  private final Context context;

  public BatteryReader(Context context) {
    this.context = context;
  }

  public float getBatteryPercent() {
    IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
    Intent batteryStatus = context.registerReceiver(null, intentFilter);
    if (batteryStatus == null) {
      return 0;
    }
    int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
    int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, 100);
    return level * 100f / scale;
  }
}