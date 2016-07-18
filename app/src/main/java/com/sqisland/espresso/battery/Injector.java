package com.sqisland.espresso.battery;

/**
 * Responsible for injecting values into objects
 */

public interface Injector<T> {

    /**
     * Injects the values into the object.
     * For the injector to work the 'injected' fields must be either protected or public
     *
     * @param injection The injection providing values
     * @param object    The instance to inject
     * @see Injection
     */
    void inject(Injection injection, T object);
}
