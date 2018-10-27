package cn.joylau.code.menu;

import cn.joylau.code.settings.WhoDidSettings;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;

/**
 * Created by joylau on 2018/10/27.
 * cn.joylau.code.menu
 * 2587038142@qq.com
 */
public class HideVCSInfoMenu extends AnAction {

    private static final WhoDidSettings whoDidSettings = WhoDidSettings.getInstance();

    public HideVCSInfoMenu() {
        super(getText());
    }

    private static String getText(){
        return whoDidSettings.isEnableNodeDecorator() ? "Hide VCS Info" : "Show VCS Info";
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        whoDidSettings.setEnableNodeDecorator(!whoDidSettings.isEnableNodeDecorator());
        final Presentation presentation = this.getTemplatePresentation();
        presentation.setText(getText());
    }

}
