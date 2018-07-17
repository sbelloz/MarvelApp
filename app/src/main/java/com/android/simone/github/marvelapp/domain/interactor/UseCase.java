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

public abstract class UseCase<T, Params> {

    private final ThreadExecution threadExecution;
    private final PostExecutionThread postExecutionThread;

    private Subscription subscription = Subscriptions.empty();

    protected UseCase(ThreadExecution threadExecutor,
                      PostExecutionThread postExecutionThread) {
        this.threadExecution = threadExecutor;
        this.postExecutionThread = postExecutionThread;
    }

    protected abstract Observable<T> buildObservable(Params params);

    public void execute(Subscriber<T> subscriber, Params params) {
        this.subscription = buildObservable(params)
                .subscribeOn(threadExecution.getScheduler())
                .observeOn(postExecutionThread.getScheduler())
                .subscribe(subscriber);
    }

    public void execute(Action1<T> onNext, Action1<Throwable> onError, Params params) {
        execute(onNext, onError, Actions.empty(), params);
    }

    public void execute(Action1<T> onNext, Params params) {
        execute(onNext, InternalObservableUtils.ERROR_NOT_IMPLEMENTED, Actions.empty(), params);
    }

    public void execute(Action1<T> onNext, Action1<Throwable> onError, Action0 onCompleted, Params params) {
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
