package org.example.demo;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformCoreDataKeys;
import org.example.demo.deepseek.LLM;
import org.example.demo.deepseek.OKHttpInstance;
import org.example.demo.deepseek.OllamaAPIClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class rightMenuPopText extends AnAction {

    private static final Logger log = LoggerFactory.getLogger(rightMenuPopText.class);

    @Override
    public void actionPerformed(AnActionEvent e) {
        String selectedText = e.getData(PlatformCoreDataKeys.EDITOR).getSelectionModel().getSelectedText();
        log.info("selectedText:" + selectedText);
        OllamaAPIClient ollamaAPIClient = new OllamaAPIClient(LLM.DEEPSEEK_R1_1_5B, selectedText);
        ollamaAPIClient.runTask(new OKHttpInstance.RequestCallback() {
            @Override
            public void onSuccess(String result) {
                log.info("result:" + result);
            }

            @Override
            public void onError(String error) {
                log.info("error:" + error);
            }
        });
        // TODO: insert action logic here
//        if (e.getProject() != null) {
//            String name = e.getProject().getName();
//            String basePath = e.getProject().getBasePath();
//            log.info("name: " + name + ", basePath: " + basePath);
//
//            Messages.showInfoMessage("A:" + selectedText, "B:" + name );
//        } else {
//            log.info("getProject is null");
//
//
//        }


    }
}