package ${basePackage};

import ${basePackage}.picocli.CommandExecutor;
import ${basePackage}.utils.Utils;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        CommandTest(args);
    }

    /**
     * 使用命令行生成代码
     */
    private static void CommandTest(String[] args) {

        CommandExecutor commandExecutor = new CommandExecutor();
        commandExecutor.doExecute(args);
    }

}