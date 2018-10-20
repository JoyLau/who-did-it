package cn.joylau.code.settings;

/**
 * Created by joylau on 2018/10/18.
 * cn.joylau.code.settings
 * 2587038142@qq.com
 */
class WhoDidState {
    public String pluginVersion = "";

    public boolean infoEnable = true;

    String getPluginVersion() {
        return pluginVersion;
    }

    void setPluginVersion(String pluginVersion) {
        this.pluginVersion = pluginVersion;
    }

    public boolean isInfoEnable() {
        return infoEnable;
    }

    public void setInfoEnable(boolean infoEnable) {
        this.infoEnable = infoEnable;
    }
}
