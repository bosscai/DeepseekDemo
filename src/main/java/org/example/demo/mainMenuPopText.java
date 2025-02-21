package org.example.demo;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;

public class mainMenuPopText extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // 弹出一个确认的对话框
        int result = Messages.showYesNoCancelDialog("确认执行操作吗？", "是否进行代码检测", Messages.getInformationIcon());
        if (result == Messages.YES) {
            Messages.showInfoMessage("操作已经确认", "确认结果");
        }

    }
}
