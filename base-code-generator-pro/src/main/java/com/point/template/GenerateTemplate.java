package com.point.template;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.ZipUtil;
import com.point.meta.Meta;
import com.point.meta.MetaManager;
import com.point.utils.Utils;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

public class GenerateTemplate {
    public void generate() throws TemplateException, IOException, InterruptedException {
        Meta meta = MetaManager.getMeta();
        System.out.println(meta);

        // 获取项目根路径
        String projectPath = System.getProperty("user.dir");
        String projectOutputPath = projectPath + File.separator + ".generated" + File.separator + meta.getName() + File.separator;

        // 获取项目路径
        ClassPathResource classPathResource = new ClassPathResource("");
        String inputPath = classPathResource.getAbsolutePath() + File.separator + "templates" + File.separator;
        String outputPath = projectPath + File.separator + ".generated" + File.separator + meta.getName() + File.separator + "src" + File.separator + "main" + File.separator;

        // 移动模板项目代码
        copySourceCode(meta, projectPath);

        // 动态生成代码生成器
        generateCode(inputPath, outputPath, meta, projectOutputPath);

        // 使用maven打包
        mavenBuilder(projectOutputPath);

        // 编写脚本
        String jarName = generateScript(meta, projectOutputPath);

        // 代码空间优化
        //SimplifyPoject(projectOutputPath, jarName);

        // 5、生成精简版的程序（产物包）
        buildDist(projectPath + File.separator + ".generated" + File.separator + meta.getName(), projectOutputPath, projectOutputPath + "/target" + File.separator + jarName, projectOutputPath);
    }

    protected String generateScript(Meta meta, String projectOutputPath) throws IOException {
        String jarName = String.format("%s-%s-jar-with-dependencies.jar", meta.getName(), meta.getVersion());
        String jarPath = "target" + File.separator + jarName;
        Utils.generateBat(projectOutputPath, jarPath);
        return jarName;
    }

    protected void SimplifyPoject(String projectOutputPath, String jarName) {
        String jarPath = "target" + File.separator + jarName;

        // 删除生成代码的源代码
        String srcPath = projectOutputPath + File.separator + "src";
        Utils.deleteFileorDir(srcPath);

        // 删除pom.xml文件
        String mvnxmlPath = projectOutputPath + File.separator + "pom.xml";
        Utils.deleteFileorDir(mvnxmlPath);

        // 删除target，除了jar包
        String oldJarPath = projectOutputPath + jarPath;
        String newJarPath = projectOutputPath + jarName;
        System.out.println("oldJarPath  " + oldJarPath);
        System.out.println("newJarPath  " + newJarPath);
        // 移动jar包
        Utils.copyStaticFiles(oldJarPath, newJarPath);
        // 删除target
        String targetPath = projectOutputPath + File.separator + "target";
        Utils.deleteFileorDir(targetPath);
        // 移动jar包
        Utils.copyStaticFiles(newJarPath, oldJarPath);
        Utils.deleteFileorDir(newJarPath);
    }

    protected void mavenBuilder(String projectOutputPath) throws IOException, InterruptedException {
        System.out.println("projectOutputPath " + projectOutputPath);
        Utils.mavenBuilder(projectOutputPath);
    }

    protected void generateCode(String inputPath, String outputPath, Meta meta, String projectOutputPath) throws TemplateException, IOException {
        // 移动项目中的所有文件
        String filePath;
        // java/com/point/model/NKWConfig.java.ftl
        filePath = "java/com/point/model/NKWConfig.java.ftl";
        filePath = "java/com/point/model/DataModel.java.ftl";
        generateFile(inputPath, outputPath, filePath, meta);

        // java/com/point/picocli/mycommand/ConfigCommand.java.ftl
        filePath = "java/com/point/picocli/mycommand/ConfigCommand.java.ftl";
        filePath = "java/com/point/picocli/command/ConfigCommand.java.ftl";
        generateFile(inputPath, outputPath, filePath, meta);

        // java/com/point/picocli/mycommand/GenerateCommand.java.ftl
        filePath = "java/com/point/picocli/mycommand/GenerateCommand.java.ftl";
        filePath = "java/com/point/picocli/command/GenerateCommand.java.ftl";
        generateFile(inputPath, outputPath, filePath, meta);

        // java/com/point/picocli/mycommand/ListCommand.java.ftl
        filePath = "java/com/point/picocli/mycommand/ListCommand.java.ftl";
        filePath = "java/com/point/picocli/command/ListCommand.java.ftl";
        generateFile(inputPath, outputPath, filePath, meta);

        // java/com/point/picocli/CommandExecutor.java.ftl
        filePath = "java/com/point/picocli/CommandExecutor.java.ftl";
        generateFile(inputPath, outputPath, filePath, meta);

        // java/com/point/utils/Utils.java.ftl
        filePath = "java/com/point/utils/Utils.java.ftl";
        generateFile(inputPath, outputPath, filePath, meta);

        // java/com/point/picocli/generator/DynamicGenerator.java.ftl
        filePath = "java/com/point/picocli/generator/DynamicGenerator.java.ftl";
        generateFile(inputPath, outputPath, filePath, meta);

        // java/com/point/picocli/generator/MainGenerator.java.ftl
        filePath = "java/com/point/picocli/generator/MainGenerator.java.ftl";
        generateFile(inputPath, outputPath, filePath, meta);

        // java/com/point/picocli/generator/StaticGenerator.java.ftl
        filePath = "java/com/point/picocli/generator/StaticGenerator.java.ftl";
        generateFile(inputPath, outputPath, filePath, meta);

        // java/com/point/Main.java.ftl
        filePath = "java/com/point/Main.java.ftl";
        generateFile(inputPath, outputPath, filePath, meta);

        // templates/pom.xml.ftl
        filePath = "pom.xml.ftl";
        generateFile(inputPath, projectOutputPath, filePath, meta);
    }

    protected void copySourceCode(Meta meta, String projectPath) {
        Utils.copyStaticDir(meta.getFileConfig().getSourceRootPath(), projectPath + File.separator + ".generated" + File.separator + meta.getName() + File.separator + new File(meta.getFileConfig().getInputRootPath()).getParent());
    }

    private void generateFile(String inputPath, String outputPath, String filePath, Meta meta) throws TemplateException, IOException {
        Utils.doGenerate(inputPath + filePath, outputPath + filePath.substring(0, filePath.length() - 4), meta);
    }

    /**
     * 生成精简版程序
     *
     * @param outputPath
     * @param sourceCopyDestPath
     * @param jarPath
     * @param shellOutputFilePath
     * @return 产物包路径
     */
    protected String buildDist(String outputPath, String sourceCopyDestPath, String jarPath, String shellOutputFilePath) {
        String distOutputPath = outputPath + "-dist";
        // 拷贝 jar 包
        String targetAbsolutePath = distOutputPath + File.separator + "target";
        FileUtil.mkdir(targetAbsolutePath);
        String jarAbsolutePath = jarPath;
        FileUtil.copy(jarAbsolutePath, targetAbsolutePath, true);
        // 拷贝脚本文件
        FileUtil.copy(shellOutputFilePath, distOutputPath, true);
        // 拷贝源模板文件
        FileUtil.copy(sourceCopyDestPath, distOutputPath, true);
        return distOutputPath;
    }

    /**
     * 制作压缩包
     *
     * @param outputPath
     * @return 压缩包路径
     */
    protected String buildZip(String outputPath) {
        String zipPath = outputPath + ".zip";
        ZipUtil.zip(outputPath, zipPath);
        return zipPath;
    }
}
