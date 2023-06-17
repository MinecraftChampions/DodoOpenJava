package io.github.minecraftchampions.dodoopenjava.card.component;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * ��ע���
 */
public class RemarkComponent extends Component{
    /**
     * ��ʼ��
     */
    public RemarkComponent() {
        jsonCard.put("type","remark");
        jsonCard.put("elements",new JSONArray());
    }

    /**
     * ��ʼ��
     * @param object ���
     */
    public RemarkComponent(ImageComponent object) {
        jsonCard.put("type","remark");
        jsonCard.put("elements",new JSONArray());
        addElement(object);
    }

    /**
     * ���ӱ�ע
     * @param object ����
     */
    public void addElement(ImageComponent object) {
        jsonCard.getJSONArray("elements").put(object.getJsonCard());
    }

    /**
     * ��ʼ��
     * @param object ���
     */
    public RemarkComponent(SectionComponent object) {
        jsonCard.put("type","remark");
        jsonCard.put("elements",new JSONArray());
        addElement(object);
    }


    /**
     * ���ӱ�ע
     * @param object ����
     */
    public void addElement(SectionComponent object) {
        if (object.isParagraph) {
            System.out.println("����Ĵ���(�����ı�)");
        }
        jsonCard.getJSONArray("elements").put(object.getJsonCard());
    }

    /**
     * �Ƴ���ע
     * @param component ��ע
     */
    public void removeImage(Component component) {
        List<Object> list = jsonCard.getJSONArray("element").toList();
        List<Integer> integerList = new ArrayList<>();
        for (int i = 0;i < list.size();i++) {
            Object object = list.get(i);
            if (object instanceof JSONObject jsonObject) {
                if (component.getJsonCard() == jsonObject) {
                    integerList.add(i);
                }
            }
        }
        for (int i = 0;i < integerList.size();i++) {
            removeRemark(integerList.get(i) - i);
        }
    }

    /**
     * ɾ��һ����ע
     * @param index index
     */
    public void removeRemark(int index) {
        jsonCard.getJSONArray("elements").remove(index);
    }
}
