package ${basePackage}.model;

import lombok.Data;

/**
* 数据模型
*/
@Data
public class NKWConfig {
<#list modelConfig.models as modelInfo>

    <#if modelInfo.groupKey??>
        /**
        * ${modelInfo.groupName}
        */
        public ${modelInfo.type} ${modelInfo.groupKey} = new ${modelInfo.type}();

        <#if modelInfo.description??>
            /**
            * ${modelInfo.description}
            */
        </#if>
        @Data
        public static class ${modelInfo.type} {
        <#list modelInfo.models as model>
            <#if model.defaultValue??>
                public ${model.type} ${model.fieldName} = ${model.defaultValue?c};
            <#else>
                public ${model.type} ${model.fieldName};
            </#if>
        </#list>
        }




    <#else>
        <#if modelInfo.description??>
            /**
            * ${modelInfo.description}
            */
        </#if>
        <#if modelInfo.defaultValue??>
            public ${modelInfo.type} ${modelInfo.fieldName} = ${modelInfo.defaultValue?c};
        <#else>
            public ${modelInfo.type} ${modelInfo.fieldName};
        </#if>
    </#if>



</#list>
}
