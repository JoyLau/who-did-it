package cn.joylau.code.view;

import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.projectView.ProjectViewNode;
import com.intellij.ide.projectView.ProjectViewNodeDecorator;
import com.intellij.ide.projectView.impl.nodes.ClassTreeNode;
import com.intellij.ide.projectView.impl.nodes.PsiFileNode;
import com.intellij.ide.util.treeView.AbstractTreeNode;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vcs.AbstractVcs;
import com.intellij.openapi.vcs.FilePath;
import com.intellij.openapi.vcs.ProjectLevelVcsManager;
import com.intellij.openapi.vcs.diff.DiffMixin;
import com.intellij.openapi.vcs.history.VcsRevisionDescription;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.packageDependencies.ui.PackageDependenciesNode;
import com.intellij.ui.ColoredTreeCellRenderer;
import com.intellij.ui.SimpleTextAttributes;
import com.intellij.vcsUtil.VcsUtil;
import org.jetbrains.annotations.Nullable;

import java.text.SimpleDateFormat;

/**
 * Created by joylau on 2018/10/11.
 * cn.joylau.code.view
 * 2587038142@qq.com
 */
public class VCSInfoTreeNodeDecorator implements ProjectViewNodeDecorator {
    private final Project project;

    public VCSInfoTreeNodeDecorator(Project project) {
        this.project = project;
    }

    @Override
    public void decorate(ProjectViewNode node, PresentationData data) {
        String stickyTag = getStickyTagFor(node);

        if (stickyTag != null) {
            String parentStickyTag = getStickyTagFor(node.getParent());
            if (!stickyTag.equals(parentStickyTag)) {
                if (data.getColoredText().isEmpty() && data.getPresentableText() != null) {
                    data.addText(data.getPresentableText(), SimpleTextAttributes.REGULAR_ATTRIBUTES);
                }
                data.addText(" <" + stickyTag + ">", SimpleTextAttributes.GRAY_ATTRIBUTES);
            }
        }
    }

    @Nullable
    private String getStickyTagFor(@Nullable AbstractTreeNode node) {
        String stickyTag = null;
        VirtualFile file = null;

        if (node instanceof ClassTreeNode) {
            file = ((ClassTreeNode) node).getValue().getContainingFile().getVirtualFile();
        } else if (node instanceof PsiFileNode) {
            file = ((PsiFileNode) node).getValue().getVirtualFile();
        }

        if (file == null) return null;

        FilePath filePath = VcsUtil.getFilePath(file);

        AbstractVcs vcs = ProjectLevelVcsManager.getInstance(project).getVcsFor(file);
        if (vcs == null) return null;
        if (vcs.fileIsUnderVcs(filePath)) {
            if (vcs.getDiffProvider() instanceof DiffMixin) {
                VcsRevisionDescription description = ((DiffMixin) vcs.getDiffProvider()).getCurrentRevisionDescription(file);
                if (description == null) return null;
                String author = description.getAuthor();
                String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(description.getRevisionDate());
                stickyTag = "by " + author + " " + date;
            }
        }

        return stickyTag;
    }

    @Override
    public void decorate(PackageDependenciesNode node, ColoredTreeCellRenderer cellRenderer) {
    }

}
