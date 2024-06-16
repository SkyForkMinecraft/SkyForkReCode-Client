package net.skyfork.event.impl;

import lombok.Getter;

/**
 * The most basic form of a stoppable Event.
 * Stoppable events are called separate from other events and the calling of methods is stopped
 * as soon as the EventStoppable is stopped.
 *
 * @author DarkMagician6
 * @since 26-9-13
 */
@Getter
public abstract class EventStoppable {

    /**
     * -- GETTER --
     *  Checks the stopped boolean.
     *
     */
    private boolean stopped;

    /**
     * No need for the constructor to be public.
     */
    protected EventStoppable() {
    }

    /**
     * Sets the stopped state to true.
     */
    public void stop() {
        stopped = true;
    }

}
