package me.lv.thread;

import java.util.concurrent.*;

/**
 * @author plume
 * @date 2021/6/4 14:34
 */
public class Task implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        Thread.sleep(1000);
        return 1;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        Task task = new Task();
        Future<Integer> submit = service.submit(task);
        System.out.println(submit.get());

        FutureTask<Integer> futureTask = new FutureTask(task);
        service.submit(futureTask);
        System.out.println(futureTask.get());
        Thread.currentThread().getThreadGroup().activeCount();
    }
}
