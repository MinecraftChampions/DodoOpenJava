package io.github.minecraftchampions.dodoopenjava.card.component;

import io.github.minecraftchampions.dodoopenjava.card.enums.TextType;
import org.json.JSONObject;

/**
 * �������
 */
public class HeaderComponent extends Component{
    /**
     * ��ʼ��
     * @param type �ı�����
     * @param title ����
     */
    public HeaderComponent(TextType type, String title) {
        jsonCard.put("type","header");
        JSONObject object = new JSONObject();
        object.put("type",type.getType());
        object.put("content",title);
        jsonCard.put("text",object);
    }

    /**
     * �༭����
     * @param type ����
     */
    public void editTextType(TextType type) {
        jsonCard.getJSONObject("text").put("type",type.getType());
    }

    /**
     * �༭�ı�
     * @param context �ı�
     */
    public void editContent(String context) {
        jsonCard.getJSONObject("text").put("content",context);
    }
}