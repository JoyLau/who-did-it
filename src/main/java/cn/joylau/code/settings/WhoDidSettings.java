package cn.joylau.code.settings;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by joylau on 2018/10/18.
 * cn.joylau.code.settings
 * 2587038142@qq.com
 */
@State(
        name = "WhoDidSettings",
        storages = @Storage("who-did-it.xml")
)
public class WhoDidSettings implements PersistentStateComponent<WhoDidState> {

    private WhoDidState whoDidState = new WhoDidState();

    public static WhoDidSettings getInstance() {
        return ServiceManager.getService(WhoDidSettings.class);
    }

    @Nullable
    @Override
    public WhoDidState getState() {
        return whoDidState;
    }

    @Override
    public void loadState(@NotNull WhoDidState state) {
        whoDidState = state;
    }

    public String getVersion() {
        return whoDidState.getPluginVersion();
    }

    public void setVersion(String version) {
        whoDidState.setPluginVersion(version);
    }

    public boolean isEnableNodeDecorator() {
        return whoDidState.isEnableNodeDecorator();
    }

    public void setEnableNodeDecorator(boolean enableNodeDecorator) {
        whoDidState.setEnableNodeDecorator(enableNodeDecorator);
    }

}
