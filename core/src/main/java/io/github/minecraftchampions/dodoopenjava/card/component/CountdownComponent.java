package io.github.minecraftchampions.dodoopenjava.card.component;

import io.github.minecraftchampions.dodoopenjava.card.enums.Style;

/**
 * ����ʱ���
 */
public class CountdownComponent extends Component {
    /**
     * ��ʼ��
     * @param style ��ʾ��ʽ
     * @param endTime ʱ���
     */
    public CountdownComponent(Style style, long endTime) {
        jsonCard.put("type","countdown");
        jsonCard.put("style",style.toString());
        jsonCard.put("endTime",endTime);
    }

    /**
     * ��ʼ��
     * @param style ��ʾ��ʽ
     * @param endTime ʱ���
     * @param title ����
     */
    public CountdownComponent(Style style, long endTime,String title) {
        jsonCard.put("type","countdown");
        jsonCard.put("style",style.toString());
        jsonCard.put("endTime",endTime);
        jsonCard.put("title",title);
    }

    /**
     * �༭��ʾ��ʽ
     * @param style ��ʽ
     */
    public void editStyle(Style style) {
        jsonCard.put("style",style);
    }

    /**
     * �༭����ʱ���
     * @param endTime ʱ���
     */
    public void editCover(long endTime) {
        jsonCard.put("endTime",endTime);
    }

    /**
     * �༭����
     * @param title ����
     */
    public void editTitle(String title) {
        jsonCard.put("title",title);
    }
}
