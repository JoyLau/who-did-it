package cn.joylau.code.utils;

import com.intellij.notification.*;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by joylau on 2018/10/18.
 * cn.joylau.code.utils
 * 2587038142@qq.com
 */
public class NotifyUtils {
    public static void showUpdate(@NotNull Project project) {
        show(
                project,
                BundleUtils.message("notification.update.title", "2018.2.4"),
                BundleUtils.message("notification.update.content"),
                new NotificationGroup(
                        BundleUtils.PLUGIN_ID,
                        NotificationDisplayType.STICKY_BALLOON,
                        true
                ),
                NotificationType.INFORMATION,
                NotificationListener.URL_OPENING_LISTENER
        );
    }

    public static void showUpdated(@NotNull Project project, String version) {
        show(
                project,
                BundleUtils.message("notification.updated.title", version),
                BundleUtils.message("notification.updated.content"),
                new NotificationGroup(
                        BundleUtils.PLUGIN_ID,
                        NotificationDisplayType.STICKY_BALLOON,
                        true
                ),
                NotificationType.INFORMATION,
                NotificationListener.URL_OPENING_LISTENER
        );
    }


    /**
     * Shows {@link Notification}.
     *
     * @param project  current project
     * @param title    notification title
     * @param group    notification group
     * @param content  notification text
     * @param type     notification type
     * @param listener optional listener
     */
    public static void show(@NotNull Project project, @NotNull String title, @NotNull String content,
                            @NotNull NotificationGroup group, @NotNull NotificationType type,
                            @Nullable NotificationListener listener) {
        Notification notification = group.createNotification(title, content, type, listener);
        Notifications.Bus.notify(notification, project);
    }
}
