package io.github.minecraftchampions.dodoopenjava.card.component;

/**
 * ��ͼ���
 */
public class ImageComponent extends Component{
    /**
     * ��ʼ��
     * @param url ͼƬurl
     */
    public ImageComponent(String url) {
        jsonCard.put("type","image");
        jsonCard.put("src",url);
    }

    /**
     * �޸�ͼƬ����
     * @param url ͼƬurl
     */
    public void editUrl(String url) {
        jsonCard.put("src",url);
    }
}
