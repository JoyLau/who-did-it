package cn.joylau.code.utils;

import com.intellij.ide.plugins.IdeaPluginDescriptor;
import com.intellij.ide.plugins.PluginManager;
import com.intellij.ide.util.treeView.PresentableNodeDescriptor;
import com.intellij.openapi.extensions.PluginId;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vcs.AbstractVcs;
import com.intellij.openapi.vcs.ProjectLevelVcsManager;
import com.intellij.openapi.vcs.diff.DiffMixin;
import com.intellij.openapi.vcs.history.VcsRevisionDescription;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.SimpleTextAttributes;
import com.intellij.util.ui.UIUtil;
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

    private static final int SMALL_ITALIC_STYLE = SimpleTextAttributes.STYLE_SMALLER + SimpleTextAttributes.STYLE_ITALIC;

    private static final SimpleTextAttributes GRAYED_SMALL_ITALIC_ATTR = new SimpleTextAttributes(SMALL_ITALIC_STYLE, UIUtil.getInactiveTextColor());

    public static PresentableNodeDescriptor.ColoredFragment getNodeDesc(Project project, VirtualFile virtualFile) {
        AbstractVcs vcs = ProjectLevelVcsManager.getInstance(project).getVcsFor(virtualFile);
        if (vcs == null) return null;

        if (vcs.fileIsUnderVcs(VcsUtil.getFilePath(virtualFile))) {
            if (vcs.getDiffProvider() instanceof DiffMixin) {
                VcsRevisionDescription description = ((DiffMixin) vcs.getDiffProvider()).getCurrentRevisionDescription(virtualFile);
                if (description == null) return null;

                String author = description.getAuthor();
                String date = getDate(description.getRevisionDate());
                return new PresentableNodeDescriptor.ColoredFragment("   " + date + "  " + author,
                        description.getCommitMessage(),
                        GRAYED_SMALL_ITALIC_ATTR);
            }
        }
        return null;
    }

    private static String getDate(Date d) {
        Date newDate = new Date();
        if (DateUtils.isSameDay(d, newDate)) {
            long delta = (newDate.getTime() - d.getTime()) / 1000;
            if (delta / 60 < 1) return "Moments ago";
            if (delta / 60 <= 60) return delta / 60 + " minutes ago";
            return "Today " + new SimpleDateFormat("HH:mm:ss").format(d);
        }
        return new SimpleDateFormat("yyyy/MM/dd").format(d);
    }

    public static String getVersion() {
        return getPlugin().getVersion();
    }

    private static IdeaPluginDescriptor getPlugin() {
        return PluginManager.getPlugin(PluginId.getId(getPluginId()));
    }

    public static String getPluginId() {
        return BundleUtils.PLUGIN_ID;
    }
}
