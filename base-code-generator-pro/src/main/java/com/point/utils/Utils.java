package com.point.utils;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Utils {
    /**
     * 复制文件
     */
    public static void copyStaticFiles(String from, String to) {
        File file = new File(to);
        if (!file.exists()) {
            file.mkdirs();
        }
        FileUtil.copy(from, to, true);
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

    public static void doGenerate(String form, String to, Object model) throws IOException, TemplateException {
        System.out.println(form);
        System.out.println(to);
        // new 出 Configuration 对象，参数为 FreeMarker 版本号
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);

        // 指定模板文件所在的路径
        File templateDir = new File(form).getParentFile();
        configuration.setDirectoryForTemplateLoading(templateDir);

        // 设置模板文件使用的字符集
        configuration.setDefaultEncoding("utf-8");

        // 创建模板对象，加载指定模板
        String templateName = new File(form).getName();
        Template template = configuration.getTemplate(templateName);

        // 判断是否存在目录
        File file = new File(FileSystems.getDefault().getPath(to).getParent().toString());
        if (!file.exists()) {
            file.mkdirs();
        }

        // 生成
        Writer out = new FileWriter(to);
        template.process(model, out);

        // 生成文件后别忘了关闭哦
        out.close();
    }

    /**
     * 输出目录的树形结构
     */
    public static void genDirTree(String path, int level, String dir) {
        level++;
        File file = new File(path);
        File[] files = file.listFiles();
        if (!file.exists()) {
            System.out.println("文件不存在");
            return;
        }
        if (files.length != 0) {
            for (File f : files) {
                if (f.isDirectory()) {
                    dir = f.getName();
                    System.out.println(levelSign(level) + dir);
                    genDirTree(f.getAbsolutePath(), level, dir);
                } else {
                    System.out.println(levelSign(level) + f.getName());
                }
            }
        }
    }
    //文件层级信息
    private static String levelSign(int level) {
        StringBuilder sb = new StringBuilder();
        sb.append(" ├─");
        for (int x = 0; x < level; x++) {
            sb.insert(0, " │   ");
        }
        return sb.toString();
    }
}
