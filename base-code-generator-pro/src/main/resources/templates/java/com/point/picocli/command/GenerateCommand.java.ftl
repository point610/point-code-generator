package ${basePackage}.picocli.command;

import cn.hutool.core.bean.BeanUtil;
import ${basePackage}.model.NKWConfig;
import ${basePackage}.utils.Utils;
import lombok.Data;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.File;
import java.util.concurrent.Callable;

@Command(name = "generate", description = "生成代码", mixinStandardHelpOptions = true)
@Data
public class GenerateCommand implements Callable<Integer> {

    <#list modelConfig.models as modelInfo>

        @Option(names = {<#if modelInfo.abbr??>"-${modelInfo.abbr}", </#if>"--${modelInfo.fieldName}"}, arity = "0..1", <#if modelInfo.description??>description = "${modelInfo.description}", </#if>interactive = true, echo = true)
        private ${modelInfo.type} ${modelInfo.fieldName}<#if modelInfo.defaultValue??> = ${modelInfo.defaultValue?c}</#if>;
    </#list>

    public Integer call() throws Exception {

    // 将文件目录都复制到对应的位置
    String fromDir = "${fileConfig.inputRootPath}";
    String toDir ="${fileConfig.outputRootPath}";
    File toDirPath = new File(toDir);
    if (toDirPath.exists()) {
        toDirPath.mkdirs();
    }

    System.out.println(fromDir);
    System.out.println(toDir);
    // Utils.copyStaticDir(fromDir, toDir);

    // 替换动态的模板文件
    String fromFile;
    String toFile;

    // 生成配置文件
    NKWConfig nkwConfig = new NKWConfig();
    BeanUtil.copyProperties(this, nkwConfig);
    System.out.println("配置信息：" + nkwConfig);
    // Utils.doGenerate(fromFile, toFile, nkwConfig);

    <#list fileConfig.files as fileInfo>

    fromFile = new File(fromDir, "${fileInfo.inputPath}").getAbsolutePath();
    toFile = new File(toDir, "${fileInfo.outputPath}").getAbsolutePath();
    <#if fileInfo.generateType == "static">
    Utils.copyStaticFiles(fromFile, toFile);
    <#else>
    Utils.doGenerate(fromFile, toFile, nkwConfig);
    </#if>
    </#list>

        return 0;
    }
}