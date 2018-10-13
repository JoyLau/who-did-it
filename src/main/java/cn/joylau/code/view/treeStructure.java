package cn.joylau.code.view;

import com.intellij.ide.projectView.TreeStructureProvider;
import com.intellij.ide.projectView.ViewSettings;
import com.intellij.ide.util.treeView.AbstractTreeNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

/**
 * Created by joylau on 2018/10/12.
 * cn.joylau.code.view
 * 2587038142@qq.com
 */
public class treeStructure implements TreeStructureProvider {
    @NotNull
    @Override
    public Collection<AbstractTreeNode> modify(@NotNull AbstractTreeNode parent, @NotNull Collection<AbstractTreeNode> children, ViewSettings settings) {
        return children;
    }

    @Nullable
    @Override
    public Object getData(@NotNull Collection<AbstractTreeNode> selected, String dataName) {
        return null;
    }
}
