package com.example.rxjava;

import io.reactivex.rxjava3.core.Observable;

public class RxJavaMain {
    public static void main(String[] args) {
        // Creating an Observable that emits a sequence of integers
        Observable<Integer> observable = Observable.fromArray(1, 2, 3, 4, 5);

// Subscribing to the Observable
        observable.subscribe(
                item -> System.out.println("Received: " + item),  // onNext
                error -> System.err.println("Error: " + error),   // onError
                () -> System.out.println("Completed")             // onComplete
        );
    }
}
