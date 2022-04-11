package cn.chuanwise.common.test;

import cn.chuanwise.common.concurrent.AbstractTask;
import cn.chuanwise.common.concurrent.Cancellable;
import cn.chuanwise.common.concurrent.Task;
import cn.chuanwise.common.concurrent.ThreadTask;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class TaskTest {
    
    static Task task;
    
    static ExecutorService threadPool = Executors.newCachedThreadPool();
    
    static AtomicInteger flag = new AtomicInteger();
    
    static AtomicBoolean shouldFailed = new AtomicBoolean();
    
    @BeforeAll
    static void buildTask() {
        ThreadTask task = new ThreadTask(() -> {
            TimeUnit.SECONDS.sleep(5);
            if (shouldFailed.get()) {
                throw new Exception();
            }
    
            flag.set(1);
        });
        TaskTest.task = task;
        
        threadPool.submit(task);
    }
    
    @Test
    void testSync() throws InterruptedException {
        Assertions.assertEquals(0, flag.get());
        task.sync();
        Assertions.assertEquals(1, flag.get());
    }
    
    @Test
    void testIsDone() throws InterruptedException {
        Assertions.assertFalse(task.isDone());
        task.sync();
        Assertions.assertTrue(task.isDone());
    }
    
    @Test
    void testIsSucceed() throws InterruptedException {
        Assertions.assertFalse(task.isSucceed());
        task.sync();
        Assertions.assertTrue(task.isSucceed());
        Assertions.assertFalse(task.isFailed());
    }
    
    @Test
    void testIsFailed() throws InterruptedException {
        Assertions.assertFalse(task.isFailed());
        shouldFailed.set(true);
        task.sync();
        Assertions.assertTrue(task.isDone());
        Assertions.assertTrue(task.isFailed());
        Assertions.assertFalse(task.isSucceed());
        
        Assertions.assertInstanceOf(Exception.class, task.getCause());
    }
    
    @Test
    void testCancelled() {
        final Cancellable cancellable = (Cancellable) task;
        Assertions.assertFalse(cancellable.isCancelled());
        cancellable.cancel(true);
        Assertions.assertTrue(cancellable.isCancelled());
    }
}
