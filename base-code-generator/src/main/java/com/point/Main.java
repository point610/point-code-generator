package com.point;

import com.point.model.NKWConfig;
import com.point.utils.Utils;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws TemplateException, IOException {
//        moveStaticFile();
//        moveStaticDir();
        generatorNKW();
    }

    private static void generatorNKW() throws TemplateException, IOException {
        // 路径
        String from = System.getProperty("user.dir") + File.separator + "src/main/resources/templates/NiuKeWangPro/Main.java.ftl";
        String to = System.getProperty("user.dir") + File.separator + ".temp/NiuKeWangPro/Main.java";

        // 初始化对象
        NKWConfig nkwConfig = new NKWConfig();
        nkwConfig.setLoop(true);
        nkwConfig.setPrintList(true);
        nkwConfig.setAuthor("");

        Utils.doGenerate(from, to, nkwConfig);
    }

    private static void moveStaticFile() {
        String from = System.getProperty("user.dir") + File.separator + "src/main/resources/templates/NiuKeWang/Main.java.ftl";
        String to = System.getProperty("user.dir") + File.separator + ".temp";
        System.out.println(from);
        System.out.println(to);
        Utils.copyStaticFiles(from, to);
    }

    private static void moveStaticDir() {
        String from = System.getProperty("user.dir") + File.separator + "src/main/resources/templates/NiuKeWang/";
        String to = System.getProperty("user.dir") + File.separator + ".temp";
        System.out.println(from);
        System.out.println(to);
        Utils.copyStaticDir(from, to);
    }

}