package com.milaboratory.mitoola.domain;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Alexei Zakharov (ayza)
 */
@Component
public class StatefulCounter {
    private AtomicInteger counter = new AtomicInteger(0);

    public int incrementAndGet() {
        return counter.incrementAndGet();
    }

    public int get() {
        return counter.get();
    }
}
