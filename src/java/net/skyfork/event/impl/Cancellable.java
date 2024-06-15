package net.skyfork.event.impl;

public interface Cancellable extends Event {

    boolean isCancelled();

    void setCancelled(boolean cancel);
}
