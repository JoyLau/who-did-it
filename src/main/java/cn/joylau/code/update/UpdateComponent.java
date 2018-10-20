package cn.joylau.code.update;

import cn.joylau.code.cache.VCSDecoratorCache;
import cn.joylau.code.settings.WhoDidSettings;
import cn.joylau.code.utils.NotifyUtils;
import cn.joylau.code.utils.VCSUtils;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.intellij.ide.projectView.ProjectView;
import com.intellij.ide.util.treeView.PresentableNodeDescriptor;
import com.intellij.openapi.components.AbstractProjectComponent;
import com.intellij.openapi.project.Project;
import com.intellij.vcsUtil.VcsUtil;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by joylau on 2018/10/18.
 * cn.joylau.code.update
 * 2587038142@qq.com
 */
public class UpdateComponent extends AbstractProjectComponent {

    private Project project;
    private ScheduledExecutorService scheduledTask;

    protected UpdateComponent(@NotNull Project project) {
        super(project);

        this.project = project;

        this.startScheduledTask();
    }

    private synchronized void startScheduledTask() {

        if (this.scheduledTask != null) stopScheduledTask();

        this.scheduledTask = Executors.newSingleThreadScheduledExecutor(new ThreadFactoryBuilder()
                .setDaemon(true)
                .setNameFormat(getComponentName() + "-scheduledTask")
                .setPriority(Thread.NORM_PRIORITY)
                .build()
        );

        this.scheduledTask.schedule(this::showUpdated, 25L, TimeUnit.SECONDS);
        this.scheduledTask.scheduleAtFixedRate(this::refresh, 30L, 60L, TimeUnit.SECONDS);
    }

    private synchronized void stopScheduledTask() {
        if (this.scheduledTask == null) return;
        this.scheduledTask.shutdown();
        try {
            this.scheduledTask.awaitTermination(7L, TimeUnit.SECONDS);
        } catch (InterruptedException ignored) {
        } finally {
            this.scheduledTask = null;
        }
    }

    @Override
    public void initComponent() {
    }

    @Override
    public void disposeComponent() {
        if (this.scheduledTask != null) {
            this.startScheduledTask();
        }
    }

    @NotNull
    @Override
    public String getComponentName() {
        return "WhoDidItUpdateComponent";
    }

    /**
     * Method called when project is opened.
     */
    @Override
    public void projectOpened() {
    }

    private void showUpdated() {
        final WhoDidSettings settings = WhoDidSettings.getInstance();
        String pluginVersion = VCSUtils.getVersion();
        boolean updated = !pluginVersion.equals(settings.getVersion());
        if (updated) {
            settings.setVersion(pluginVersion);
            NotifyUtils.showUpdated(myProject, pluginVersion);
        }
    }

    private void refresh() {
        VCSDecoratorCache.getInstance().getMap().forEach((path, coloredFragment) -> {
            PresentableNodeDescriptor.ColoredFragment desc = VCSUtils.getNodeDesc(project, VcsUtil.getVirtualFile(path));
            if (desc != null) {
                VCSDecoratorCache.getInstance().getMap().put(path, desc);
            }
        });
        ProjectView.getInstance(this.project).refresh();
    }
}