package com.sqisland.espresso.battery;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Injection {

    private Map<String, Injector> injectorMap;

    private final Context context;
    private BatteryReader batteryReader = null;

    public Injection(Context context) {
        injectorMap = new HashMap<>();
        this.context = context;
        addInjectTor(MainActivity.class, new MainActivityInjector());
    }

    /**
     * Add a new {@linkplain Injector} to this Injection.
     *
     * @param toInject
     * @param injector
     * @param <T>
     * @see Injector
     */
    public <T> void addInjector(Class<T> toInject, Injector<T> injector) {
        injectorMap.put(toInject.getSimpleName(), injector);
    }

    public void inject(Object o) {
        String key = o.getClass().getSimpleName();
        if (injectorMap.containsKey(key)) {
            injectorMap.get(key).inject(this, o);
        } else {
            throw new IllegalStateException("Injector for " + key + " is not provided");
        }

    }

    protected BatteryReader createBatteryReader() {
        return new BatteryReader(provideContext());
    }

    /*****
     * Public provide methods
     *****/

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