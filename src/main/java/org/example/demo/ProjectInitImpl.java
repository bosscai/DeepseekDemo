package org.example.demo;

import com.alibaba.fastjson2.JSONObject;
import org.example.demo.deepseek.Configuration;
import org.example.demo.deepseek.LLM;
import org.example.demo.deepseek.OllamaAPIClient;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.example.demo.deepseek.Configuration.API_URL;

public class ProjectInitImpl implements ProjectInit {


    public final static String TAG = "ProjectInitImpl";
    private static final Logger log = LoggerFactory.getLogger(ProjectInitImpl.class);

    public ProjectInitImpl() {
    }

    @Override
    public void initComponent() {
        log.info("initComponent");
    }

    @Override
    public void disposeComponent() {
        log.info("disposeComponent");
    }

    @Override
    public @NotNull String getComponentName() {
        return "org.example.demo";
    }
}
