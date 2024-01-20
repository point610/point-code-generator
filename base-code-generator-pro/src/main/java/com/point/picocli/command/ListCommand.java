package com.point.picocli.command;

import com.point.utils.Utils;
import picocli.CommandLine.Command;

import java.io.File;

@Command(name = "list", description = "查看文件列表", mixinStandardHelpOptions = true)
public class ListCommand implements Runnable {

    public void run() {
        String projectPath = System.getProperty("user.dir");
        // 整个项目的根路径
        File parentFile = new File(projectPath).getParentFile();

        // 输入路径
        String inputPath = new File(parentFile, "demo-project").getAbsolutePath();

        // 输出文件树形结构
        Utils.genDirTree(inputPath, -1, "");
    }
}