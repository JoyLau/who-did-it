package cn.joylau.code.settings;

/**
 * Created by joylau on 2018/10/18.
 * cn.joylau.code.settings
 * 2587038142@qq.com
 */
class WhoDidState {
    public String pluginVersion = "";

    public boolean enableNodeDecorator = true;

    String getPluginVersion() {
        return pluginVersion;
    }

    void setPluginVersion(String pluginVersion) {
        this.pluginVersion = pluginVersion;
    }

    public boolean isEnableNodeDecorator() {
        return enableNodeDecorator;
    }

    public void setEnableNodeDecorator(boolean enableNodeDecorator) {
        this.enableNodeDecorator = enableNodeDecorator;
    }
}
