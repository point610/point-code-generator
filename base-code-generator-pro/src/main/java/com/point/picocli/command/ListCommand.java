package com.point.picocli.command;

import com.point.meta.Meta;
import com.point.meta.MetaManager;
import com.point.utils.Utils;
import picocli.CommandLine.Command;

import java.io.File;

@Command(name = "list", description = "查看文件列表", mixinStandardHelpOptions = true)
public class ListCommand implements Runnable {

    public void run() {

        // 输入路径
        String inputPath = MetaManager.getMeta().getFileConfig().getInputRootPath();

        // 输出文件树形结构
        Utils.genDirTree(inputPath, -1, "");
    }
}