package com.android.simone.github.marvelapp.domain.executor;

import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * @author Simone Bellotti <simone.bellotti@immobiliare.it>
 */

public class JobExecutor implements ThreadExecution {

    @Override
    public Scheduler getScheduler() {
        return Schedulers.io();
    }
}
