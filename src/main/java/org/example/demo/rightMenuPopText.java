package org.example.demo;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformCoreDataKeys;
import com.intellij.openapi.ui.Messages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class rightMenuPopText extends AnAction {


    public final static String TAG = "rightMenuPopText";

    private static final Logger log = LoggerFactory.getLogger(rightMenuPopText.class);

    @Override
    public void actionPerformed(AnActionEvent e) {
        String selectedText = e.getData(PlatformCoreDataKeys.EDITOR).getSelectionModel().getSelectedText();
        // TODO: insert action logic here
        if (e.getProject() != null) {
            String name = e.getProject().getName();
            log.info(TAG, "name: " + name);
            Messages.showInfoMessage("A:" + selectedText, "B:" + name );
        } else {
            log.debug(TAG, "getProject is null");
        }


    }
}