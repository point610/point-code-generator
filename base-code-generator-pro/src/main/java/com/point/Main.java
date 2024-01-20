package com.point;

import cn.hutool.core.io.resource.ClassPathResource;
import com.point.meta.Meta;
import com.point.meta.MetaManager;
import com.point.utils.Utils;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws TemplateException, IOException, InterruptedException {
        Meta meta = MetaManager.getMeta();
        System.out.println(meta);

        // 获取项目根路径
        String projectPath = System.getProperty("user.dir");

        Meta.FileConfigDTO fileConfig = meta.getFileConfig();
        Meta.ModelConfigDTO modelConfig = meta.getModelConfig();

        // 移动模板项目的其他组件-resource中的各种文件
        // 获取项目路径
        ClassPathResource classPathResource = new ClassPathResource("");
        String inputPath = classPathResource.getAbsolutePath() + File.separator + "templates" + File.separator;
        String outputPath = projectPath + File.separator + ".generated" + File.separator + meta.getName() + File.separator + "src" + File.separator + "main" + File.separator;

        // 移动项目中的所有文件
        String filePath;
        // java/com/point/model/NKWConfig.java.ftl
        filePath = "java/com/point/model/NKWConfig.java.ftl";
        generateFile(inputPath, outputPath, filePath, meta);

        // java/com/point/picocli/command/ConfigCommand.java.ftl
        filePath = "java/com/point/picocli/command/ConfigCommand.java.ftl";
        generateFile(inputPath, outputPath, filePath, meta);

        // java/com/point/picocli/command/GenerateCommand.java.ftl
        filePath = "java/com/point/picocli/command/GenerateCommand.java.ftl";
        generateFile(inputPath, outputPath, filePath, meta);

        // java/com/point/picocli/command/ListCommand.java.ftl
        filePath = "java/com/point/picocli/command/ListCommand.java.ftl";
        generateFile(inputPath, outputPath, filePath, meta);

        // java/com/point/picocli/CommandExecutor.java.ftl
        filePath = "java/com/point/picocli/CommandExecutor.java.ftl";
        generateFile(inputPath, outputPath, filePath, meta);

        // java/com/point/utils/Utils.java.ftl
        filePath = "java/com/point/utils/Utils.java.ftl";
        generateFile(inputPath, outputPath, filePath, meta);

        // java/com/point/Main.java.ftl
        filePath = "java/com/point/Main.java.ftl";
        generateFile(inputPath, outputPath, filePath, meta);

        // templates/pom.xml.ftl
        filePath = "pom.xml.ftl";
        String mavenOutputPath = projectPath + File.separator + ".generated" + File.separator + meta.getName() + File.separator;
        generateFile(inputPath, mavenOutputPath, filePath, meta);

        // 使用maven打包
        System.out.println("mavenOutputPath " + mavenOutputPath);
        Utils.mavenBuilder(mavenOutputPath);

        // 编写脚本
        String jarName = String.format("%s-%s-jar-with-dependencies.jar", meta.getName(), meta.getVersion());
        Utils.generateBat(mavenOutputPath, "target" + File.separator + jarName);

    }

    private static void generateFile(String inputPath, String outputPath, String filePath, Meta meta) throws TemplateException, IOException {
        Utils.doGenerate(inputPath + filePath, outputPath + filePath.substring(0, filePath.length() - 4), meta);
    }
}