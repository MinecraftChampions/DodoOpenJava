package io.github.minecraftchampions.dodoopenjava.card.component;

import io.github.minecraftchampions.dodoopenjava.utils.MapUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * �б�ѡ����
 */
public class ListSelectorComponent extends Component {
    /**
     * ��ʼ��
     * <p>
     * ����Ҫ����������дnull
     * @param interactCustomId �Զ��彻��id
     * @param placeholder �������ʾ
     * @param min ����ѡ�и���
     * @param max ���ѡ�и���
     * @param element ���ݣ�Ϊ�˷�����HashMap�洢��ǰ��Ϊѡ����������Ϊѡ������
     */
    public ListSelectorComponent(String interactCustomId, String placeholder, int min, int max, HashMap<String,String> element) {
        jsonCard.put("type","list-selector");
        jsonCard.put("interactCustomId", interactCustomId);
        jsonCard.put("placeholder", placeholder);
        jsonCard.put("min", min);
        jsonCard.put("max", max);
        jsonCard.put("elements", new JSONArray());

        for(int i = 0; i < MapUtil.ergodicHashMaps(element).size(); i++) {
            JSONObject json2 = new JSONObject();

            json2.put("name", MapUtil.ergodicHashMaps(element).get(i).get(0));
            json2.put("desc", MapUtil.ergodicHashMaps(element).get(i).get(1));

            jsonCard.getJSONArray("elements").put(json2);
        }
    }

    /**
     * ��ʼ��
     * <p>
     * ����Ҫ����������дnull
     * @param interactCustomId �Զ��彻��id
     * @param placeholder �������ʾ
     * @param min ����ѡ�и���
     * @param max ���ѡ�и���
     */
    public ListSelectorComponent(String interactCustomId, String placeholder, int min, int max) {
        jsonCard.put("type","list-selector");
        jsonCard.put("interactCustomId", interactCustomId);
        jsonCard.put("placeholder", placeholder);
        jsonCard.put("min", min);
        jsonCard.put("max", max);
        jsonCard.put("elements", new JSONArray());
    }

    /**
     * ����ѡ��
     * @param data1 ѡ����
     * @param data2 ����
     */
    public void addElement(String data1,String data2) {
        JSONObject json = new JSONObject();
        json.put("name", data1);
        json.put("desc", data2);
        jsonCard.getJSONArray("elements").put(json);
    }
}
