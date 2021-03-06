/*
 * Copyright (c) 2019 AlexIIL
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package alexiil.mc.lib.attributes.misc;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

/** A {@link Reference} that delegates to a {@link Supplier} for {@link #get()}, {@link Consumer} for
 * {@link #set(Object)}, and a {@link Predicate} for {@link #isValid(Object)}. */
public final class CallableRef<T> implements Reference<T> {

    private final Supplier<T> getter;
    private final Consumer<T> setter;
    private final Predicate<T> canSet;

    public CallableRef(Supplier<T> getter, Consumer<T> setter, Predicate<T> canSet) {
        this.getter = getter;
        this.setter = setter;
        this.canSet = canSet;
    }

    @Override
    public T get() {
        return getter.get();
    }

    @Override
    public boolean set(T value) {
        if (canSet.test(value)) {
            setter.accept(value);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isValid(T value) {
        return canSet.test(value);
    }
}
