package cn.joylau.code.action;

import cn.joylau.code.settings.WhoDidSettings;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.ToggleAction;
import org.jetbrains.annotations.NotNull;

/**
 * Created by joylau on 2018/10/19.
 * cn.joylau.code.action
 * 2587038142@qq.com
 */
public class EnableVCSInfoAction extends ToggleAction {
    public EnableVCSInfoAction() {
        super();
    }

    @Override
    public boolean isSelected(AnActionEvent e) {
        //ShowSettingsUtil.getInstance().showSettingsDialog(null,);
        return e.getProject() != null && WhoDidSettings.getInstance().isEnableNodeDecorator();
    }

    @Override
    public void setSelected(AnActionEvent e, boolean state) {
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        e.getPresentation().setEnabled(e.getProject()!=null);
        super.update(e);
    }
}
