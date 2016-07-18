package com.sqisland.espresso.battery;

import org.mockito.Mockito;

public class MockInjection extends Injection {
    public MockInjection() {
        super(null);
        addInjectTor(MainActivityTest.class, new MockInjector());
    }

    @Override
    protected BatteryReader createBatteryReader() {
        return Mockito.mock(BatteryReader.class);
    }
}