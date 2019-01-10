package com.caox.job;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * 定时任务Job
 * @author nazi
 * @date 2019/1/10
 */
@Slf4j
public class QuartzJob {

    /**
     * 是否启用该job
     */
    private boolean startUp;

    /**
     * 设置是否启用该job
     * <p>NOTE:获取Apollo的配置在xml文件中设置值<p/>
     */
    public void setStartUp(boolean startUp) {
        log.info("Setting startUp=[{}]", startUp);
        this.startUp = startUp;
    }

    /**
     * 获取是否启用该job
     * @return
     */
    public boolean isStartUp(){
        return this.startUp;
    }

    /**
     * 处理定时任务方法
     */
    public void autoExcluteJobTask () {
        if (!isStartUp()) {
            log.info("不启用该job");
            return;
        }
        try {
            log.info("定时任务开始处理");
            long start = System.currentTimeMillis();

            log.info("定时任务开始处理中......");

            log.info("定时任务开始处理结束, 耗时: {}", System.currentTimeMillis() - start);
        } catch (Throwable t) {
            log.error("定时任务开始处理异常: {}", t.getMessage(), t);
        }
    }
}
