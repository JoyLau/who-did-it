package cn.joylau.code.cache;

import com.intellij.ide.util.treeView.PresentableNodeDescriptor;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by joylau on 2018/10/17.
 * cn.joylau.code.cache
 * 2587038142@qq.com
 */
public class VCSDecoratorCache {

    private static VCSDecoratorCache ourInstance = new VCSDecoratorCache();

    private ConcurrentHashMap<String, PresentableNodeDescriptor.ColoredFragment> map = new ConcurrentHashMap<>();

    public static VCSDecoratorCache getInstance() {
        return ourInstance;
    }

    private VCSDecoratorCache() {
    }

    public ConcurrentHashMap<String, PresentableNodeDescriptor.ColoredFragment> getMap() {
        return map;
    }

}
