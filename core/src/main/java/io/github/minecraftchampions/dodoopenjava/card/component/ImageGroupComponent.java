package io.github.minecraftchampions.dodoopenjava.card.component;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 多图组件
 */
public class ImageGroupComponent extends Component {
    /**
     * 初始化
     */
    public ImageGroupComponent() {
        jsonCard.put("type", "image-group");
        jsonCard.put("elements", new JSONArray());
    }

    /**
     * 初始化
     *
     * @param list 图片组件列表
     */
    public ImageGroupComponent(List<ImageComponent> list) {
        jsonCard.put("type", "image-group");
        jsonCard.put("elements", new JSONArray());
        list.forEach(this::addImage);
    }

    /**
     * 增加图片组件
     *
     * @param image 组件
     */
    public void addImage(ImageComponent image) {
        jsonCard.getJSONArray("elements").put(image.getJsonCard());
    }

    /**
     * 移除图片，如果有多个相同的则全部移除
     *
     * @param component 图片
     */
    public void removeImage(ImageComponent component) {
        List<Object> list = jsonCard.getJSONArray("element").toList();
        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Object object = list.get(i);
            if (object instanceof JSONObject jsonObject) {
                if (component.getJsonCard() == jsonObject) {
                    integerList.add(i);
                }
            }
        }
        for (int i = 0; i < integerList.size(); i++) {
            removeImage(integerList.get(i) - i);
        }
    }

    /**
     * 删除一个图片
     *
     * @param index index
     */
    public void removeImage(int index) {
        jsonCard.getJSONArray("elements").remove(index);
    }
}
