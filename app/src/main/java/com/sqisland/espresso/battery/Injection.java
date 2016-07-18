package com.sqisland.espresso.battery;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

public class Injection {
  private Map<String, Injector> injectorMap;
  private final Context context;
  private BatteryReader batteryReader = null;

  public Injection(Context context) {
    this.context = context;
    this.injectorMap = new HashMap<>();
    addInjector(MainActivity.class, new MainActivityInjector());
  }

  protected BatteryReader createBatteryReader() {
    return new BatteryReader(provideContext());
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

  public void inject(Object o) {
    String key = o.getClass().getSimpleName();
    if(injectorMap.containsKey(key)) {
      injectorMap.get(key).inject(this,o);
    }
    else {
      throw new IllegalStateException("Injector for "+key+" is not provided");
    }
  }
}