package com.sqisland.espresso.battery;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
  private TextView batteryView;

  private BatteryReader batteryReader;

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

    inject();
  }

  private void inject() {
    DemoApplication application = (DemoApplication) getApplication();
    Injection injection = application.getInjection();

    batteryReader = injection.provideBatteryReader();
  }

  @Override
  protected void onResume() {
    super.onResume();
    refresh();
  }

  private void refresh() {
    float percent = batteryReader.getBatteryPercent();

    batteryView.setText(getString(R.string.percentage, percent));

    int color = ContextCompat.getColor(
        this, percent > 15f ? R.color.battery_ok : R.color.battery_low);
    batteryView.setTextColor(color);
  }
}
