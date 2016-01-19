package com.sqisland.espresso.battery;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
  private TextView batteryView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    batteryView = (TextView) findViewById(R.id.battery);
    batteryView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        refresh();
      }
    });
  }

  @Override
  protected void onResume() {
    super.onResume();
    refresh();
  }

  private void refresh() {
    float percent = getBatteryPercent();

    batteryView.setText(getString(R.string.percentage, percent));

    int color = ContextCompat.getColor(
        this, percent > 15f ? R.color.battery_ok : R.color.battery_low);
    batteryView.setTextColor(color);
  }

  private float getBatteryPercent() {
    IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
    Intent batteryStatus = registerReceiver(null, intentFilter);
    if (batteryStatus == null) {
      return 0;
    }
    int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
    int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, 100);
    return level * 100f / scale;
  }
}