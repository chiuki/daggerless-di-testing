package com.sqisland.espresso.battery;

import org.mockito.Mockito;

public class MockInjection extends Injection {
  public MockInjection() {
    super(null);
  }

  @Override
  protected BatteryReader createBatteryReader() {
    return Mockito.mock(BatteryReader.class);
  }
}