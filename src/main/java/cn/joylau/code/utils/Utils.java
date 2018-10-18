package cn.joylau.code.utils;

import com.intellij.ide.plugins.IdeaPluginDescriptor;
import com.intellij.ide.plugins.PluginManager;
import com.intellij.openapi.extensions.PluginId;

/**
 * Created by joylau on 2018/10/18.
 * cn.joylau.code.utils
 * 2587038142@qq.com
 */
public class Utils {
    public Utils() {
    }

    public static String getVersion() {
        return getPlugin().getVersion();
    }
    public static IdeaPluginDescriptor getPlugin() {
        return PluginManager.getPlugin(PluginId.getId(BundleUtils.PLUGIN_ID));
    }
}
