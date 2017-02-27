package com.android.simone.github.marvelapp.domain.interactor;

import com.android.simone.github.marvelapp.domain.executor.PostExecutionThread;
import com.android.simone.github.marvelapp.domain.executor.ThreadExecution;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Actions;
import rx.internal.util.InternalObservableUtils;
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
    public void execute(Subscriber subscriber, T... params) {
        this.subscription = buildObservable(params)
                .subscribeOn(threadExecution.getScheduler())
                .observeOn(postExecutionThread.getScheduler())
                .subscribe(subscriber);
    }

    @SuppressWarnings("unchecked")
    public void execute(Action1<T> onNext, Action1<Throwable> onError, T... params) {
        execute(onNext, onError, Actions.empty(), params);
    }

    @SuppressWarnings("unchecked")
    public void execute(Action1<T> onNext, T... params) {
        execute(onNext, InternalObservableUtils.ERROR_NOT_IMPLEMENTED, Actions.empty(), params);
    }

    @SuppressWarnings("unchecked")
    public void execute(Action1<T> onNext, Action1<Throwable> onError, Action0 onCompleted, T... params) {
        this.subscription = buildObservable(params)
                .subscribeOn(threadExecution.getScheduler())
                .observeOn(postExecutionThread.getScheduler())
                .subscribe(onNext, onError, onCompleted);
    }

    public void unsubscribe() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
