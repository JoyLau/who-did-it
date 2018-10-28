package cn.joylau.code.listener;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationListener;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import javax.swing.event.HyperlinkEvent;

/**
 * Created by joylau on 2018/10/27.
 * cn.joylau.code.listener
 * 2587038142@qq.com
 */
public class SettingsOpeningListener extends NotificationListener.Adapter {

    private final Project project;
    private final String nameToSelect;

    public SettingsOpeningListener(Project project, String nameToSelect) {
        this.project = project;
        this.nameToSelect = nameToSelect;
    }

    @Override
    protected void hyperlinkActivated(@NotNull final Notification notification, @NotNull final HyperlinkEvent e) {

        if (!project.isDisposed()) {
            ShowSettingsUtil.getInstance().showSettingsDialog(project, nameToSelect);
        }
    }
}
