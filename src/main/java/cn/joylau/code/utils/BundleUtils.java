package cn.joylau.code.utils;

import com.intellij.CommonBundle;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.PropertyKey;

import java.util.ResourceBundle;

/**
 * Created by joylau on 2018/10/18.
 * cn.joylau.code.utils
 * 2587038142@qq.com
 */
public class BundleUtils {
    @NonNls
    public static final String PLUGIN_ID = "cn.joylau.code.who-did-it";

    @NonNls
    private static final String BUNDLE_NAME = "messages.Who-Did-It-Bundle";

    @NotNull
    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

    private BundleUtils() {
    }

    static String message(@PropertyKey(resourceBundle = BUNDLE_NAME) String key, Object... params) {
        return CommonBundle.message(BUNDLE, key, params);
    }
}
