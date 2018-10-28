package cn.joylau.code.action;

import cn.joylau.code.settings.WhoDidSettings;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.ToggleAction;

/**
 * Created by joylau on 2018/10/19.
 * cn.joylau.code.action
 * 2587038142@qq.com
 */
public class EnableVCSInfoAction extends ToggleAction {

    private WhoDidSettings whoDidSettings;

    public EnableVCSInfoAction() {
        super();
        this.whoDidSettings = WhoDidSettings.getInstance();
    }

    @Override
    public boolean isSelected(AnActionEvent e) {
        return e.getProject() != null && whoDidSettings.isEnableNodeDecorator();
    }

    @Override
    public void setSelected(AnActionEvent e, boolean state) {
        whoDidSettings.setEnableNodeDecorator(state);
    }

}
