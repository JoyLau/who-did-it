package cn.joylau.code.panel;

import cn.joylau.code.panel.form.SettingsForm;
import cn.joylau.code.utils.VCSUtils;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
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
        SettingsForm jPanel = new SettingsForm();
        return jPanel.mainPanel;
    }

    @Override
    public boolean isModified() {
        return false;
    }

    @Override
    public void apply() throws ConfigurationException {

    }
}
