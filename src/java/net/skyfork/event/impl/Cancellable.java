package net.skyfork.event.impl;

public interface Cancellable {

    boolean isCancelled();

    void setCancelled(boolean cancel);
}
