package com.android.simone.github.marvelapp.domain.executor;

import rx.Scheduler;

/**
 * @author Simone Bellotti <simone.bellotti@immobiliare.it>
 */

public interface ThreadExecution {
    Scheduler getScheduler();
}
