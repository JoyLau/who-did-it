package cn.joylau.code.utils;

import com.intellij.ide.util.treeView.PresentableNodeDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vcs.AbstractVcs;
import com.intellij.openapi.vcs.ProjectLevelVcsManager;
import com.intellij.openapi.vcs.diff.DiffMixin;
import com.intellij.openapi.vcs.history.VcsRevisionDescription;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.SimpleTextAttributes;
import com.intellij.vcsUtil.VcsUtil;
import org.apache.commons.lang3.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by joylau on 2018/10/17.
 * cn.joylau.code.utils
 * 2587038142@qq.com
 */
public class VCSUtils {

    public static PresentableNodeDescriptor.ColoredFragment getNodeDesc(Project project, VirtualFile virtualFile){
        AbstractVcs vcs = ProjectLevelVcsManager.getInstance(project).getVcsFor(virtualFile);
        if (vcs == null) return null;

        if (vcs.fileIsUnderVcs(VcsUtil.getFilePath(virtualFile))) {
            if (vcs.getDiffProvider() instanceof DiffMixin) {
                VcsRevisionDescription description = ((DiffMixin) vcs.getDiffProvider()).getCurrentRevisionDescription(virtualFile);
                if (description == null) return null;

                String author = description.getAuthor();
                String date = getDate(description.getRevisionDate());
                return new PresentableNodeDescriptor.ColoredFragment("  " + date + "  " + author,
                        description.getCommitMessage(),
                        SimpleTextAttributes.GRAYED_SMALL_ATTRIBUTES);
            }
        }
        return null;
    }

    private static String getDate(Date date){
        return DateUtils.isSameDay(new Date(),date) ? "Today" : new SimpleDateFormat("yyyy/MM/dd").format(date);
    }
}
