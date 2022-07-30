package com.evo.core.manager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.evo.util.internal.Wrapper;

public abstract class BaseManager implements Wrapper {
    protected final Logger LOGGER = LogManager.getLogger(getClass().getSimpleName());

    public abstract void init();
}
