package io.github.minecraftchampions.dodoopenjava.card.component;

/**
 * ��Ƶ���
 */
public class VideoComponent extends Component {
    /**
     * ��ʼ��
     * @param url ��Ƶ��ַ
     * @param cover �����ַ
     */
    public VideoComponent(String url,String cover) {
        jsonCard.put("type","video");
        jsonCard.put("src",url);
        jsonCard.put("cover",cover);
    }

    /**
     * ��ʼ��
     * @param url ��Ƶ��ַ
     * @param cover �����ַ
     * @param title ��Ƶ����
     */
    public VideoComponent(String url,String cover,String title) {
        jsonCard.put("type","video");
        jsonCard.put("src",url);
        jsonCard.put("cover",cover);
        jsonCard.put("title",title);
    }

    /**
     * �༭��Ƶurl
     * @param url ��ַ
     */
    public void editUrl(String url) {
        jsonCard.put("src",url);
    }

    /**
     * �༭����url
     * @param url ��ַ
     */
    public void editCover(String url) {
        jsonCard.put("cover",url);
    }

    /**
     * �༭����
     * @param title ����
     */
    public void editTitle(String title) {
        jsonCard.put("title",title);
    }
}
