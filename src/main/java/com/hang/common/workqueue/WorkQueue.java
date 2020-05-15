package com.hang.common.workqueue;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 工作队列: 用于执行异步工作任务
 *
 * 1) 入队时根据workinfo的md5进行去重，防止重复工作
 * 2) 工作队列单线程异步执行
 *
 * @author hang on 2020/5/14
 */
public abstract class WorkQueue<T extends WorkInfo> {

    protected ConcurrentLinkedQueue<T> theQueue = new ConcurrentLinkedQueue<>();
    protected ConcurrentHashMap<String, String> theHash = new ConcurrentHashMap<>();

    private Thread thread;

    public WorkQueue(){
        this(10000);
    }
    public WorkQueue(int thrSleepTime){
        thread =  new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try{
                        Thread.sleep(thrSleepTime);
                        work();
                    }
                    catch (Exception ex){
                    }
                }
            }
        });
        thread.start();
    }

    /**
     * 入队
     * @param t
     */
    public void offer(T t){
        if(null != t){
            return;
        }
        String md5 = t.md5();
        if(!theHash.containsKey(md5)){
            theQueue.offer(t);
            theHash.put(md5, "");
        }
    }

    /**
     * 任务执行
     */
    private void work(){
        while(!theQueue.isEmpty()){
            T t = theQueue.poll();
            if(null == t){
                continue;
            }
            if(null != t.md5()){
                theHash.remove(t.md5());
            }
            if(null != getWorkHandle()){
                getWorkHandle().process(t);
            }
        }
    }

    /**
     * 获得工作执行器
     * @return
     */
    public abstract WorkHandle getWorkHandle();
}
