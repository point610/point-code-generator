package com.point.picocli;

import com.point.picocli.command.ConfigCommand;
import com.point.picocli.command.GenerateCommand;
import com.point.picocli.command.ListCommand;
import picocli.CommandLine;
import picocli.CommandLine.Command;

/**
 * 命令执行器
 */
@Command(name = "point", version = "nkw-demo 1.0", mixinStandardHelpOptions = true)
public class CommandExecutor implements Runnable {

    private final CommandLine commandLine;

    {
        commandLine = new CommandLine(this).addSubcommand(new GenerateCommand()).addSubcommand(new ConfigCommand()).addSubcommand(new ListCommand());
    }

    @Override
    public void run() {
        // 不输入子命令时，给出友好提示
        System.out.println("请输入具体命令，或者输入 --help 查看命令提示");
    }

    /**
     * 执行命令
     */
    public Integer doExecute(String[] args) {
        return commandLine.execute(args);
    }
}
