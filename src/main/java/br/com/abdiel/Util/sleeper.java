package br.com.abdiel.Util;


import org.awaitility.Awaitility;

import java.time.Duration;
import java.util.concurrent.Callable;

public abstract class sleeper<T extends sleeper<T>> {


    @SuppressWarnings("unchecked")
    public T waitUntilTrue(Callable<Boolean> callable) {
        Awaitility.await()
                .pollInSameThread()
                .ignoreExceptions()
                .timeout(Duration.ofSeconds(30))
                .until(callable);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T sleep(Duration duration) {
        Awaitility.await().pollDelay(duration).forever().until(() -> true);
        return (T) this;
    }

}
