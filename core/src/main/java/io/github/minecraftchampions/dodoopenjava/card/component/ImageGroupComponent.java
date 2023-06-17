package io.github.minecraftchampions.dodoopenjava.card.component;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * ��ͼ���
 */
public class ImageGroupComponent extends Component {
    /**
     * ��ʼ��
     */
    public ImageGroupComponent() {
        jsonCard.put("type","image-group");
        jsonCard.put("elements",new JSONArray());
    }

    /**
     * ��ʼ��
     * @param list ͼƬ����б�
     */
    public ImageGroupComponent(List<ImageComponent> list) {
        jsonCard.put("type","image-group");
        jsonCard.put("elements",new JSONArray());
        list.forEach(this::addImage);
    }

    /**
     * ����ͼƬ���
     * @param image ���
     */
    public void addImage(ImageComponent image) {
        jsonCard.getJSONArray("elements").put(image.getJsonCard());
    }

    /**
     * �Ƴ�ͼƬ������ж����ͬ����ȫ���Ƴ�
     * @param component ͼƬ
     */
    public void removeImage(ImageComponent component) {
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
            removeImage(integerList.get(i) - i);
        }
    }

    /**
     * ɾ��һ��ͼƬ
     * @param index index
     */
    public void removeImage(int index) {
        jsonCard.getJSONArray("elements").remove(index);
    }
}
