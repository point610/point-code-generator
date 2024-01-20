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
        String metaJson = ResourceUtil.readUtf8Str("meta.json");
        Meta newMeta = JSONUtil.toBean(metaJson, Meta.class);
        Meta.FileConfigDTO fileConfig = newMeta.getFileConfig();
        // todo 校验和处理默认值
        return newMeta;
    }
}
