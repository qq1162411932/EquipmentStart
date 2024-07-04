package com.sms.easymock.thread;

import org.eclipse.jetty.util.thread.ExecutorThreadPool;
import org.eclipse.jetty.util.thread.ThreadPool;

import java.util.concurrent.*;

/**
 * @author Mr.Wu
 * @create 2024-07-03 下午5:29
 */
public class ThreadPoolTest {

    ThreadPoolExecutor executor = new ThreadPoolExecutor(
            12, 24, 60, TimeUnit.SECONDS,
            new LinkedBlockingQueue(100),
            new ThreadPoolExecutor.AbortPolicy()  // 拒绝策略：抛出RejectedExecutionException
    );

    /**
     * 忽略返回值 参数Runnable接口实现类
     */
    public void testExecutorRunnable(){
        executor.execute(()->{
            System.out.println("test 123 " + Thread.currentThread().getName());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     *  带参数返回
     * @param args
     *
     * CompletableFuture 内部使用了ForkJoinPool来处理异步
     */
    public void testCallable() throws ExecutionException, InterruptedException {
        Future<String> task = executor.submit(() -> {
            System.out.println("this is a test");
            return "abc";
        });

        String s = task.get();

        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + " cf1 do something....");
            return 1;
        });
        CompletableFuture<Void> cf2 = cf1.thenRunAsync(() -> {
            System.out.println(Thread.currentThread() + " cf2 do something...");
        });
        //等待任务1执行完成
        System.out.println("cf1结果->" + cf1.get());
        //等待任务2执行完成
        System.out.println("cf2结果->" + cf2.get());
    }

    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//            new ThreadPoolTest().testExecutorRunnable();
//        }

//        Callable;
//
//        Future;
//
//        FutureTask;
//        CompletableFuture
    }
}
