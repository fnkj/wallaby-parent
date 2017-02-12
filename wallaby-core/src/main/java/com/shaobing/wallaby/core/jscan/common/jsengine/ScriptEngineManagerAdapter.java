package com.shaobing.wallaby.core.jscan.common.jsengine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * JS脚本引擎
 *
 * @author luyb@servyou.com.cn
 * @version 2016-09-09 17:36
 */
public class ScriptEngineManagerAdapter {
    private static final Logger              logger               = LoggerFactory
        .getLogger(ScriptEngineManagerAdapter.class);

    private static final ScriptEngineManager SCRIPTENGINE_MANAGER = new ScriptEngineManager();

    private ScriptEngineManagerAdapter() {
    }

    public static ScriptEngine getScriptEngine(String shortName) {
        return SCRIPTENGINE_MANAGER.getEngineByName(shortName);
    }

}
