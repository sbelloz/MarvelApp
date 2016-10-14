package com.android.simone.github.marvelapp.domain.executor;

import rx.Scheduler;

/**
 * @author Simone Bellotti
 */

public interface ThreadExecution {
    Scheduler getScheduler();
}
