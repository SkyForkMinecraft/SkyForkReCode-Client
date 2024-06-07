package net.skyfork.value.impl;

import net.skyfork.value.AbstractValue;

public class BooleanValue extends AbstractValue<Boolean> {

    public BooleanValue(String name, boolean enabled) {
        super(name);
        this.setValue(enabled);
    }

}
