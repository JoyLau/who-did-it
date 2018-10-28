package cn.joylau.code.update;

import cn.joylau.code.cache.VCSDecoratorCache;
import cn.joylau.code.settings.WhoDidSettings;
import cn.joylau.code.utils.BundleUtils;
import cn.joylau.code.utils.NotifyUtils;
import cn.joylau.code.utils.VCSUtils;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.intellij.ide.projectView.ProjectView;
import com.intellij.ide.util.treeView.PresentableNodeDescriptor;
import com.intellij.openapi.components.AbstractProjectComponent;
import com.intellij.openapi.project.Project;
import com.intellij.vcsUtil.VcsUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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

        this.scheduledTask.schedule(this::showUpdate, 60L, TimeUnit.SECONDS);
        this.scheduledTask.schedule(this::showUpdated, 25L, TimeUnit.SECONDS);
        this.scheduledTask.scheduleAtFixedRate(this::refreshNodeDecorator, 30L, 60L, TimeUnit.SECONDS);
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

    private void showUpdate() {
        try {
            HttpResponse httpResponse = HttpClients.createDefault()
                    .execute(new HttpGet(BundleUtils.message("plugin.releases.latest.api")));
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                HttpEntity httpEntity = httpResponse.getEntity();
                String result = EntityUtils.toString(httpEntity);
                JsonObject jsonObject = new Gson().fromJson(result, JsonObject.class);
                LocalDateTime publishDateTime = LocalDateTime
                        .parse(jsonObject.get("published_at").getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"));
                LocalDateTime versionDateTime = LocalDateTime.ofInstant(VCSUtils.getPluginReleaseDate().toInstant(), ZoneId.systemDefault());
                if (publishDateTime.isAfter(versionDateTime)) {
                    NotifyUtils.showUpdate(myProject,jsonObject.get("tag_name").getAsString());
                }
            }
        } catch (Exception e) {
           // do nothing
        }
    }

    private void refreshNodeDecorator() {
        VCSDecoratorCache.getInstance().getMap().forEach((path, coloredFragment) -> {
            PresentableNodeDescriptor.ColoredFragment desc = VCSUtils.getNodeDesc(project, VcsUtil.getVirtualFile(path));
            if (desc != null) {
                VCSDecoratorCache.getInstance().getMap().put(path, desc);
            }
        });
        ProjectView.getInstance(this.project).refresh();
    }
}