package io.github.minecraftchampions.dodoopenjava.permission;

import lombok.Getter;
import lombok.NonNull;

/**
 * 常用权限值
 *
 * @author qscbm187531
 * @author KisssStar
 */
@Getter
public enum DodoPermission {
    /**
     * 管理频道与分组权限
     */
    ManageChannels(new Permission(1)),

    /**
     * 编辑频道
     */
    EditChannels(new Permission(1 << 1)),

    /**
     * 管理成员
     */
    ManageMembers(new Permission(1 << 2)),

    /**
     * 超级管理员
     */
    Administrator(new Permission(1 << 3)),

    /**
     * 修改群昵称
     */
    ModifyNickname(new Permission(1 << 4)),

    /**
     * 管理群昵称
     */
    ManageNickname(new Permission(1 << 5)),

    /**
     * 查看频道
     */
    ViewChannel(new Permission(1 << 6)),

    /**
     * 管理权限与身份组
     */
    ManageRoles(new Permission(1 << 7)),

    /**
     * 管理群表情包
     */
    ManageEmojis(new Permission(1 << 8)),

    /**
     * at所有人和身份组
     */
    Mention(new Permission(1 << 9)),

    /**
     * 发送消息
     */
    SendMessages(new Permission(1 << 10)),

    /**
     * 管理消息
     */
    ManageMessages(new Permission(1 << 11)),

    /**
     * 添加新反应
     */
    CreateReaction(new Permission(1 << 12)),

    /**
     * 发布帖子
     */
    PublishArticles(new Permission(1 << 13)),

    /**
     * 管理帖子
     */
    ManageArticles(new Permission(1 << 14)),

    /**
     * 删除帖子
     */
    DeleteArticles(new Permission(1 << 15)),

    /**
     * 连接
     */
    Connect(new Permission(1 << 16)),

    /**
     * 说话
     */
    Speak(new Permission(1 << 17)),

    /**
     * 管理语音
     */
    ManageVoices(new Permission(1 << 18)),

    /**
     * 移动成员加入语音频道
     */
    MoveVoiceMember(new Permission(1 << 19)),

    /**
     * 搜索内容
     */
    SearchArticle(new Permission(1 << 20)),

    /**
     * 发布评论
     */
    CommentArticle(new Permission(1 << 21)),

    /**
     * 资料管理权限
     */
    ManageRes(new Permission(1 << 22));

    @NonNull
    private final Permission permission;

    DodoPermission(@NonNull Permission permission) {
        this.permission = permission;
    }

    /**
     * 通过权限获取DodoPermission
     *
     * @param targetPermission 权限
     * @return DodoPermission dodo对象
     */
    public static DodoPermission getDodoPermissionByPermission(Permission targetPermission) {
        for (DodoPermission dodoPermission : DodoPermission.values()) {
            if (dodoPermission.getPermission().equals(targetPermission)) {
                return dodoPermission;
            }
        }
        return null;
    }
}
