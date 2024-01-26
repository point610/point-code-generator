package ${basePackage}.picocli.command;

import cn.hutool.core.util.ReflectUtil;
import ${basePackage}.model.NKWConfig;
import picocli.CommandLine.Command;

import java.lang.reflect.Field;

@Command(name = "config", description = "查看参数信息", mixinStandardHelpOptions = true)
public class ConfigCommand implements Runnable {

    public void run() {
        // 实现 config 命令的逻辑
        System.out.println("查看参数信息");

        // 获取类中的所有属性
        Field[] fields = ReflectUtil.getFields(NKWConfig.class);

        // 遍历并打印每个字段的信息
        for (Field field : fields) {
            System.out.println(field.getType() + " -- " + field.getName());
        }
    }
}
