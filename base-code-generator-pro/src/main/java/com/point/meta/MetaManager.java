package com.point.meta;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.json.JSONUtil;

public class MetaManager {
    private static volatile Meta meta;

    public MetaManager() {
    }

    /**
     * 使用双检锁
     */
    public static Meta getMeta() {
        if (meta == null) {
            synchronized (MetaManager.class) {
                if (meta == null) {
                    meta = initMeta();
                }
            }
        }
        return meta;
    }

    /**
     * 初始化meta信息
     */
    private static Meta initMeta() {
        //String metaJson = ResourceUtil.readUtf8Str("meta.json");
        String metaJson = ResourceUtil.readUtf8Str("springinitmeta.json");
        Meta newMeta = JSONUtil.toBean(metaJson, Meta.class);
        // 校验meta
        MetaValidator.doValidAndFill(newMeta);
        return newMeta;
    }
}
