package com.hang.common.workqueue;

/**
 * 工作执行器
 * @author hang on 2020/5/14
 */
public interface WorkHandle {
    /**
     * 执行
     * @return
     */
    boolean process(WorkInfo workInfo);
}
