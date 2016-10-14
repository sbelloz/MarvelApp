package com.android.simone.github.marvelapp.domain.interactor;

import com.android.simone.github.marvelapp.domain.executor.PostExecutionThread;
import com.android.simone.github.marvelapp.domain.executor.ThreadExecution;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * @author Simone Bellotti
 */

public abstract class UseCase<T> {

    private final ThreadExecution threadExecution;
    private final PostExecutionThread postExecutionThread;

    private Subscription subscription = Subscriptions.empty();

    protected UseCase(ThreadExecution threadExecutor,
                      PostExecutionThread postExecutionThread) {
        this.threadExecution = threadExecutor;
        this.postExecutionThread = postExecutionThread;
    }

    @SuppressWarnings("unchecked")
    protected abstract Observable buildObservable(T... params);

    @SuppressWarnings("unchecked")
    public void execute(Subscriber useCaseSubscriber, T... params) {
        this.subscription = buildObservable(params)
                .subscribeOn(threadExecution.getScheduler())
                .observeOn(postExecutionThread.getScheduler())
                .subscribe(useCaseSubscriber);
    }

    public void unsubscribe() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
