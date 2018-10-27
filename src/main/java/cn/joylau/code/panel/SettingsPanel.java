package cn.joylau.code.panel;

import cn.joylau.code.panel.form.SettingsForm;
import cn.joylau.code.settings.WhoDidSettings;
import cn.joylau.code.utils.VCSUtils;
import com.intellij.ide.projectView.ProjectView;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.project.ProjectCoreUtil;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Created by joylau on 2018/10/20.
 * cn.joylau.code.panel
 * 2587038142@qq.com
 */
public class SettingsPanel implements SearchableConfigurable {

    private SettingsForm settingsForm;

    private WhoDidSettings whoDidSettings;

    public SettingsPanel() {
        this.whoDidSettings = WhoDidSettings.getInstance();
    }

    @NotNull
    @Override
    public String getId() {
        return VCSUtils.getPluginId();
    }

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "Who Did It";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        if (null == settingsForm) {
            this.settingsForm = new SettingsForm();
            settingsForm.enableVCSInfo.setSelected(whoDidSettings.isEnableNodeDecorator());
        }
        return settingsForm.mainPanel;
    }

    @Override
    public boolean isModified() {
        return whoDidSettings.isEnableNodeDecorator() != settingsForm.enableVCSInfo.isSelected();
    }

    @Override
    public void apply() throws ConfigurationException {
        whoDidSettings.setEnableNodeDecorator(settingsForm.enableVCSInfo.isSelected());
        ProjectView.getInstance(ProjectCoreUtil.theProject).refresh();
    }
}
