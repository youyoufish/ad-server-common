package com.upsmart.server.common.definition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by yuhang
 */
public abstract class ThreadLoop extends Thread{
    public final static int DEFAULT_SLEEP_TIME_FOR_TASK = 1000; //1000 milliseconds

    // 循环标识,true保持while循环
    private AtomicBoolean runningFlag = new AtomicBoolean(true);

    // 每次调用mainLoop方法后的睡眠时间（毫秒）
    private int sleepMilliseconds;

    private static Logger log = LoggerFactory.getLogger(ThreadLoop.class);

    /**
     * 构造函数，设置缺省的mainLoop方法调用后的睡眠时间
     */
    public ThreadLoop()
    {
        sleepMilliseconds = DEFAULT_SLEEP_TIME_FOR_TASK;
    }

    public ThreadLoop(int milliSecond)
    {
        sleepMilliseconds = milliSecond;
    }

    /**
     * 初始化方法
     * @return  初始化成功返回true，否则返回false
     */
    public boolean initialize()
    {
        //default initialization
        return true;
    }

    /**
     * 抽象方法，子类重写mainLoop方法完成具体的任务
     * @throws InterruptedException
     */
    public abstract boolean mainLoop() throws InterruptedException;

    /**
     * 线程执行函数，不断调用mainLoop方法，直到runningFlag设置为false或者捕获到InterruptedException
     */
    public void run()
    {
        final String mainLoopThreadName = String.format("MainLoopThread[%s]", this.getClass().getName());
        while(runningFlag.get())
        {
            try
            {
                mainLoop();
                if (sleepMilliseconds > 0)
                {
                    Thread.sleep(sleepMilliseconds);
                }
            }
            catch(InterruptedException e)
            {
                log.warn(String.format("%s run method has been interruped: %s", mainLoopThreadName, e.getMessage()));
                runningFlag.set(false);
            }
            catch(Exception e)
            {
                log.error(String.format("%s run method got exception: %s", mainLoopThreadName, e.getMessage()));
                // runningFlag.set(false);线程不能停止，否则只要有一条请求记文件异常，就会导致其他文件也无法记录！
            }
        }
    }

    /**
     * 停止线程运行
     */
    public void shutdown(boolean immediate)
    {
        runningFlag.set(false);
        if (immediate)
        {
            this.interrupt();
        }
    }

    public boolean waitUntilExitOrTimeout(int timeoutMilliseconds, int checkInterval)
    {
        int elapsed = checkInterval;
        while(elapsed <= timeoutMilliseconds)
        {
            if(!isAlive())
            {
                return true;
            }
            try
            {
                Thread.sleep(checkInterval);
            }
            catch(InterruptedException e)
            {
            }
            elapsed += checkInterval;
        }
        return !isAlive();
    }
}
