package io.github.minecraftchampions.dodoopenjava.card.component;

import lombok.NonNull;

/**
 * 单图组件
 */
public class ImageComponent extends CardComponent {
    /**
     * 初始化
     *
     * @param url 图片url
     */
    public ImageComponent(@NonNull String url) {
        jsonCard.put("type", "image");
        jsonCard.put("src", url);
    }

    /**
     * 修改图片连接
     *
     * @param url 图片url
     */
    public void editUrl(@NonNull String url) {
        jsonCard.put("src", url);
    }
}
