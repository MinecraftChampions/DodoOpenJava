package io.github.minecraftchampions.dodoopenjava.card.component;

/**
 * 视频组件
 */
public class VideoComponent extends Component {
    /**
     * 初始化
     *
     * @param url   视频地址
     * @param cover 封面地址
     */
    public VideoComponent(String url, String cover) {
        jsonCard.put("type", "video");
        jsonCard.put("src", url);
        jsonCard.put("cover", cover);
    }

    /**
     * 初始化
     *
     * @param url   视频地址
     * @param cover 封面地址
     * @param title 视频标题
     */
    public VideoComponent(String url, String cover, String title) {
        jsonCard.put("type", "video");
        jsonCard.put("src", url);
        jsonCard.put("cover", cover);
        jsonCard.put("title", title);
    }

    /**
     * 编辑视频url
     *
     * @param url 地址
     */
    public void editUrl(String url) {
        jsonCard.put("src", url);
    }

    /**
     * 编辑封面url
     *
     * @param url 地址
     */
    public void editCover(String url) {
        jsonCard.put("cover", url);
    }

    /**
     * 编辑标题
     *
     * @param title 标题
     */
    public void editTitle(String title) {
        jsonCard.put("title", title);
    }
}
