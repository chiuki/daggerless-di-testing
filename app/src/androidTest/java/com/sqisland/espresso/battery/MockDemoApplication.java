package com.sqisland.espresso.battery;

public class MockDemoApplication extends DemoApplication {
  @Override
  protected Injection createInjection() {
    return new MockInjection();
  }
}