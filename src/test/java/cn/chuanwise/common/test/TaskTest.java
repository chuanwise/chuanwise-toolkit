package cn.chuanwise.common.test;

import cn.chuanwise.common.concurrent.ThreadPeriodicTask;
import cn.chuanwise.common.concurrent.ThreadTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TaskTest {
    
    public static void main(String[] args) throws InterruptedException {
        final ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(5);
        
        final ThreadTask task1 = new ThreadTask(() -> {
            while (true) {
                System.out.println("passed 1 sec, cur = " + System.currentTimeMillis());
                TimeUnit.SECONDS.sleep(1);
            }
        });
    
        final ThreadPeriodicTask task2 = new ThreadPeriodicTask(() -> {
            System.out.println("time millis is " + System.currentTimeMillis());
        });
        task2.registerDoneListener(task -> {
            System.out.println("periodic task done");
        });
        task2.registerPeriodicListener(task -> {
            System.out.println("periodic task periodic");
        });
    
        task1.registerDoneListener(t -> {
            System.out.println("task done! " + t + ", executor = " + Thread.currentThread().getName());
        });
        
        threadPool.submit(task1);
        threadPool.scheduleWithFixedDelay(task2, 0, 1, TimeUnit.SECONDS);
        
        TimeUnit.SECONDS.sleep(10);
        System.out.println("will cancel the task!");
        System.out.println("cancel 1: " + task1.cancel(true));
        System.out.println("cancel 2: " + task2.cancel(true));
        
        TimeUnit.SECONDS.sleep(5);
    }
}
