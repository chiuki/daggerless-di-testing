package com.sqisland.espresso.battery;

public class MockInjector implements Injector<MainActivityTest> {
    @Override
    public void inject(Injection injection, MainActivityTest object) {
        object.batteryReader = injection.provideBatteryReader();
    }
}
