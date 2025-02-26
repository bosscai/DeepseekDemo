package org.example.demo;

import com.alibaba.fastjson2.JSONObject;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformCoreDataKeys;
import com.intellij.openapi.ui.Messages;
import org.example.demo.deepseek.LLM;
import org.example.demo.deepseek.OKHttpInstance;
import org.example.demo.deepseek.OllamaAPIClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

public class rightMenuPopText extends AnAction {

    private static final Logger log = LoggerFactory.getLogger(rightMenuPopText.class);

    @Override
    public void actionPerformed(AnActionEvent e) {
        String selectedText = e.getData(PlatformCoreDataKeys.EDITOR).getSelectionModel().getSelectedText();
        log.info("selectedText:" + selectedText);
        OllamaAPIClient ollamaAPIClient = new OllamaAPIClient(LLM.DEEPSEEK_R1_1_5B, "Hello");
        StringBuilder stringBuilder = new StringBuilder();
        ollamaAPIClient.runTask(new OKHttpInstance.RequestCallback() {
            @Override
            public void onSuccess(String result) {
                log.info("result:" + result);
                try {
                    JSONObject jsonObject = JSONObject.parseObject(result);
                    String string = jsonObject.getString("response");
                    Boolean done = jsonObject.getBoolean("done");
                    if (done) {
                        log.info("sb:" + stringBuilder);
                        SwingUtilities.invokeLater(() -> {
                            Messages.showInfoMessage(stringBuilder.toString(), "Deepseek");
                        });
                    } else {
                        stringBuilder.append(string);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(String error) {
                log.info("error:" + error);
            }
        });
    }
}