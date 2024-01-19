package com.point.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Utils {
    /**
     * 复制文件
     */
    public static void copyStaticFiles(String from, String to) {
        FileUtil.copy(from, to, false);
    }

    /**
     * 递归拷贝文件（递归实现，会将输入目录完整拷贝到输出目录下）
     */
    public static void copyStaticDir(String from, String to) {
        File inputFile = new File(from);
        File outputFile = new File(to);
        try {
            copyFileByRecursive(inputFile, outputFile);
        } catch (Exception e) {
            System.err.println("文件复制失败");
            e.printStackTrace();
        }
    }

    /**
     * 复制目录
     */
    public static void copyFileByRecursive(File from, File to) throws IOException {
        // 区分是文件还是目录
        if (from.isDirectory()) {
            System.out.println(from.getName());
            File destOutputFile = new File(to, from.getName());
            // 如果是目录，首先创建目标目录
            if (!destOutputFile.exists()) {
                destOutputFile.mkdirs();
            }
            // 获取目录下的所有文件和子目录
            File[] files = from.listFiles();
            // 无子文件，直接结束
            if (ArrayUtil.isEmpty(files)) {
                return;
            }
            for (File file : files) {
                // 递归拷贝下一层文件
                copyFileByRecursive(file, destOutputFile);
            }
        } else {
            // 是文件，直接复制到目标目录下
            Path destPath = to.toPath().resolve(from.getName());
            Files.copy(from.toPath(), destPath, StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
