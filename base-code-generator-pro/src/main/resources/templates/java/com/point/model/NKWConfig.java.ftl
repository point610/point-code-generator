package ${basePackage}.model;

import lombok.Data;

/**
* 数据模型
*/
@Data
public class NKWConfig {
<#list modelConfig.models as modelInfo>

<#if modelInfo.defaultValue??>
    private ${modelInfo.type} ${modelInfo.fieldName} = ${modelInfo.defaultValue?c};
<#else>
    private ${modelInfo.type} ${modelInfo.fieldName};
</#if>

</#list>
}
