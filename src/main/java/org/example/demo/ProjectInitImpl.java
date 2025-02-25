package org.example.demo;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProjectInitImpl implements ProjectInit {


    public final static String TAG = "ProjectInitImpl";
    private static final Logger log = LoggerFactory.getLogger(ProjectInitImpl.class);

    public ProjectInitImpl() {
    }

    @Override
    public void initComponent() {
        System.out.println("initComponent");
        log.info(TAG, "initComponent");
    }

    @Override
    public void disposeComponent() {
        System.out.println("disposeComponent");
        log.info(TAG, "disposeComponent");
    }

    @Override
    public @NotNull String getComponentName() {
        return "org.example.demo";
    }
}
