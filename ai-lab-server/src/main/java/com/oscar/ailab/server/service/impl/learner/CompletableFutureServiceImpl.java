package com.oscar.ailab.server.service.impl.learner;

import com.oscar.ailab.server.service.learner.CompletableFutureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CompletableFutureServiceImpl implements CompletableFutureService {

    @Override
    public void hisFunc() {
        CompletableFuture<String> future = CompletableFuture.completedFuture("Hello World")
                .thenApplyAsync(ret -> {
                    ret += "; then Async Processing";
                    log.info(ret);
                    return ret;
                })
                .thenApply(ret -> {
                    ret += "; then Processing";
                    log.info(ret);
                    return ret;
                })
                .thenApplyAsync(ret -> {
                    ret += "; then Async Again";
                    log.info(ret);
                    return ret;
                });

        future.whenCompleteAsync((ret, e) -> {
            log.info("whenCompleteAsync: {}", ret);
        }).whenComplete((ret, e) -> {
            log.info("whenComplete: {}", ret);
        });

        log.info("future.join: {}", future.join());
    }

    @Override
    public void asyncList(int c) {
        List<CompletableFuture<String>> futureList = new ArrayList<>();
        for (int i = 0; i < c; i++) {
            int finalI = i;
            CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
                log.info("CompletableFuture {} is running", finalI);
                return "CompletableFuture " + finalI;
            });
            futureList.add(future);
        }
        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[0]))
                .thenAccept(r -> log.info(
                        futureList.stream().map(CompletableFuture::join).map(Object::toString).collect(Collectors.joining("; "))
                ));
    }


    @Override
    public void then() {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            log.info("future: Async Start: {}", Thread.currentThread().getName());
        }).thenRunAsync(() -> {
            log.info("future: Async ThenRunAsync: {}", Thread.currentThread().getName());
        });

        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            log.info("future1: Async Start: {}", Thread.currentThread().getName());
        }).thenCompose(ret -> {
            log.info("future1: Async Compose: {}", Thread.currentThread().getName());
            return null;
        });
    }
}
