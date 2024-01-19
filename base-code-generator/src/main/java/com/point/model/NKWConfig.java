package com.point.model;

import lombok.Data;

/**
 * 牛客网动态模版配置
 */
@Data
public class NKWConfig {

    /**
     * 是否生成循环
     */
    private boolean loop = true;

    /**
     * 是否输出数组信息
     */
    private boolean printList = true;

    /**
     * 作者注释
     */
    private String author = "point";
}
