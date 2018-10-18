package cn.joylau.code.view;

import cn.joylau.code.cache.VCSDecoratorCache;
import cn.joylau.code.utils.VCSUtils;
import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.projectView.ProjectViewNode;
import com.intellij.ide.projectView.ProjectViewNodeDecorator;
import com.intellij.ide.projectView.impl.nodes.ClassTreeNode;
import com.intellij.ide.projectView.impl.nodes.PsiDirectoryNode;
import com.intellij.ide.projectView.impl.nodes.PsiFileNode;
import com.intellij.ide.util.treeView.PresentableNodeDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.packageDependencies.ui.PackageDependenciesNode;
import com.intellij.ui.ColoredTreeCellRenderer;
import com.intellij.ui.SimpleTextAttributes;

/**
 * Created by joylau on 2018/10/11.
 * cn.joylau.code.view
 * 2587038142@qq.com
 */
public class VCSDecoratorNode implements ProjectViewNodeDecorator {
    private final Project project;

    public VCSDecoratorNode(Project project) {
        this.project = project;
    }

    @Override
    public void decorate(ProjectViewNode node, PresentationData data) {
        VirtualFile vFile;
        if (node instanceof PsiDirectoryNode) return;

        if (node instanceof PsiFileNode) {
            vFile = node.getVirtualFile();
        } else if (node instanceof ClassTreeNode) {
            vFile = ((ClassTreeNode) node).getPsiClass().getContainingFile().getVirtualFile();
        } else {
            return;
        }

        if (vFile == null) return;

        if (VCSDecoratorCache.getInstance().getMap().containsKey(vFile.getPath())) {
            data.addText(data.getPresentableText(), SimpleTextAttributes.REGULAR_ATTRIBUTES);
            data.addText(VCSDecoratorCache.getInstance().getMap().get(vFile.getPath()));
        } else {
            PresentableNodeDescriptor.ColoredFragment coloredFragment = VCSUtils.getNodeDesc(project, vFile);
            if (coloredFragment != null) {
                data.addText(data.getPresentableText(), SimpleTextAttributes.REGULAR_ATTRIBUTES);
                data.addText(coloredFragment);
                VCSDecoratorCache.getInstance().getMap().put(vFile.getPath(), coloredFragment);
            }
        }
    }


    @Override
    public void decorate(PackageDependenciesNode node, ColoredTreeCellRenderer cellRenderer) {
    }

}
