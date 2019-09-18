package com.amvpe.cloud.entity;

import org.springframework.stereotype.Component;

/**
 * 统计上传多少次图片，暂时使用单例模式变量实现
 * 后期改造为redis实现
 * @author lidonghai
 */
@Component
public class Counter {
    private long count = 0;
    private static Counter instance = new Counter();
    private Counter() {};
    public static Counter getInstance() {
        return instance;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
