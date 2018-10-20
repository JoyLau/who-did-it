package cn.joylau.code.compont;

import cn.joylau.code.cache.VCSDecoratorCache;
import cn.joylau.code.utils.VCSUtils;
import com.intellij.ide.projectView.ProjectView;
import com.intellij.ide.util.treeView.PresentableNodeDescriptor;
import com.intellij.openapi.components.AbstractProjectComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vcs.changes.ChangeList;
import com.intellij.openapi.vcs.changes.ChangeListListener;
import com.intellij.openapi.vcs.changes.ChangeListManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileEvent;
import com.intellij.openapi.vfs.VirtualFileListener;
import com.intellij.openapi.vfs.VirtualFilePropertyEvent;
import com.intellij.openapi.vfs.ex.VirtualFileManagerEx;
import com.intellij.util.messages.MessageBusConnection;
import org.jetbrains.annotations.NotNull;

/**
 * Created by joylau on 2018/10/17.
 * cn.joylau.code.compont
 * 2587038142@qq.com
 */
public class VCSDecoratorProject extends AbstractProjectComponent {

    private final Project project;

    private final MessageBusConnection messageBus;

    private final VirtualFileListener vfListener;

    private final ChangeListListener changeListListener;

    protected VCSDecoratorProject(Project project) {
        super(project);

        this.project = project;

        this.messageBus = this.project.getMessageBus().connect();

        this.vfListener = new VirtualFileListener() {
            @Override
            public void propertyChanged(@NotNull VirtualFilePropertyEvent event) {
                refresh(event.getFile());
            }

            @Override
            public void contentsChanged(@NotNull VirtualFileEvent event) {
                refresh(event.getFile());
            }

            @Override
            public void fileDeleted(@NotNull VirtualFileEvent event) {
                delete(event.getFile());
            }
        };

        this.changeListListener = new ChangeListListener() {
            @Override
            public void changeListChanged(ChangeList fromList) {
            }
        };
    }

    private void refresh(VirtualFile virtualFile) {
        if (VCSDecoratorCache.getInstance().getMap().containsKey(virtualFile.getPath())) {
            PresentableNodeDescriptor.ColoredFragment coloredFragment = VCSUtils.getNodeDesc(project, virtualFile);
            if (coloredFragment != null) {
                VCSDecoratorCache.getInstance().getMap().put(virtualFile.getPath(), coloredFragment);
                ProjectView.getInstance(this.project).refresh();
            }
        }
    }

    private void delete(VirtualFile virtualFile) {
        VCSDecoratorCache.getInstance().getMap().remove(virtualFile.getPath());
    }

    @Override
    public void initComponent() {
        VirtualFileManagerEx.getInstance().addVirtualFileListener(this.vfListener);
        ChangeListManager.getInstance(project).addChangeListListener(this.changeListListener);
    }

    @Override
    public void disposeComponent() {
        if (this.messageBus != null) {
            this.messageBus.disconnect();
        }
        if (this.vfListener != null) {
            VirtualFileManagerEx.getInstance().removeVirtualFileListener(this.vfListener);
        }
        if (this.changeListListener != null) {
            ChangeListManager.getInstance(project).removeChangeListListener(this.changeListListener);
        }
    }

}
