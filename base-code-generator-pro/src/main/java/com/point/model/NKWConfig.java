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
    public boolean loop = true;

    /**
     * 是否输出数组信息
     */
    public boolean printList = true;

    /**
     * 作者注释
     */
    public String author = "point";

    /**
     * 是否生成.gitignore文件
     */
    public boolean neetGit = true;
}
