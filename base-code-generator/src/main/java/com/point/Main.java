package com.point;

import com.point.model.NKWConfig;
import com.point.picocli.CommandExecutor;
import com.point.utils.Utils;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
//        moveStaticFile();
//        moveStaticDir();
//        generatorNKW();

        CommandTest(args);
    }

    /**
     * 使用命令行生成代码
     */
    private static void CommandTest(String[] args) {
        //args = new String[]{"config"};
        //args = new String[]{"list"};
        //args = new String[]{"generate", "-l", "-a", "-pl"};
        //args = new String[]{"--help"};
        args = new String[]{"--version"};

        CommandExecutor commandExecutor = new CommandExecutor();
        commandExecutor.doExecute(args);
    }

    /**
     * 动态生成牛客网Java模板
     */
    private static void generatorNKW() throws TemplateException, IOException {
        // 路径
        String from = System.getProperty("user.dir") + File.separator + "src/main/resources/templates/NiuKeWangPro/Main.java.ftl";
        String to = System.getProperty("user.dir") + File.separator + ".temp/NiuKeWangPro/Main.java";

        // 初始化对象
        NKWConfig nkwConfig = new NKWConfig();
        nkwConfig.setLoop(true);
        nkwConfig.setPrintList(true);
        nkwConfig.setAuthor("");

        // 生成代码
        Utils.doGenerate(from, to, nkwConfig);
    }

    /**
     * 生成静态牛客网Java模板
     */
    private static void moveStaticFile() {
        String from = System.getProperty("user.dir") + File.separator + "src/main/resources/templates/NiuKeWang/Main.java";
        String to = System.getProperty("user.dir") + File.separator + ".temp";
        System.out.println(from);
        System.out.println(to);
        Utils.copyStaticFiles(from, to);
    }

    /**
     * 生成静态牛客网Java模板目录
     */
    private static void moveStaticDir() {
        String from = System.getProperty("user.dir") + File.separator + "src/main/resources/templates/NiuKeWang/";
        String to = System.getProperty("user.dir") + File.separator + ".temp";
        System.out.println(from);
        System.out.println(to);
        Utils.copyStaticDir(from, to);
    }

}