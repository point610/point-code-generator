package com.point.picocli.command;

import cn.hutool.core.bean.BeanUtil;
import com.point.model.NKWConfig;
import com.point.utils.Utils;
import lombok.Data;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.File;
import java.util.concurrent.Callable;

@Command(name = "generate", description = "生成代码", mixinStandardHelpOptions = true)
@Data
public class GenerateCommand implements Callable<Integer> {

    @Option(names = {"-l", "--loop"}, arity = "0..1", description = "是否循环", interactive = true, echo = true)
    private boolean loop;

    @Option(names = {"-pl", "--printList"}, arity = "0..1", description = "是否添加输出数组的方法", interactive = true, echo = true)
    private boolean printList = true;

    @Option(names = {"-a", "--author"}, arity = "0..1", description = "作者", interactive = true, echo = true)
    private String author = "point";

    public Integer call() throws Exception {

        // 将文件目录都复制到对应的位置
        String fromDir = new File(System.getProperty("user.dir")).getParent() + File.separator + "demo-project/nkw-demo";
        String toDir = System.getProperty("user.dir") + File.separator + ".temp";
        File toDirPath = new File(toDir);
        if (toDirPath.exists()) {
            toDirPath.mkdirs();
        }

        System.out.println(fromDir);
        System.out.println(toDir);
        Utils.copyStaticDir(fromDir, toDir);

        // 替换动态的模板文件
        String fromFile = System.getProperty("user.dir") + File.separator + "src/main/resources/templates/NiuKeWangPro/Main.java.ftl";
        String toFile = toDir + "/nkw-demo/src/main/java/com/point/Main.java";

        // 生成配置文件
        NKWConfig model = new NKWConfig();
        BeanUtil.copyProperties(this, model);
        Utils.doGenerate(fromFile, toFile, model);
        return 0;
    }
}