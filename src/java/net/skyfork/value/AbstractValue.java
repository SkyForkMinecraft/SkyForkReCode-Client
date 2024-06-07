package net.skyfork.value;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.function.BooleanSupplier;

@Getter
@Setter
public abstract class AbstractValue<T> {
    private String name;
    private T value;
    protected ArrayList<BooleanSupplier> hideSuppliers;

    protected AbstractValue(String name) {
        this.name = name;
    }

    public void addSupplier(BooleanSupplier hide) {
        this.hideSuppliers.add(hide);
    }

    public boolean isHidden() {
        for(BooleanSupplier supplier : hideSuppliers){
            if(!supplier.getAsBoolean())
                return false;
        }
        return true;
    }
}
