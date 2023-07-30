package io.github.minecraftchampions.dodoopenjava.card.component;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 备注组件
 */
public class RemarkComponent extends Component {
    /**
     * 初始化
     */
    public RemarkComponent() {
        jsonCard.put("type", "remark");
        jsonCard.put("elements", new JSONArray());
    }

    /**
     * 初始化
     *
     * @param object 组件
     */
    public RemarkComponent(ImageComponent object) {
        jsonCard.put("type", "remark");
        jsonCard.put("elements", new JSONArray());
        addElement(object);
    }

    /**
     * 增加备注
     *
     * @param object 数据
     */
    public void addElement(ImageComponent object) {
        jsonCard.getJSONArray("elements").put(object.getJsonCard());
    }

    /**
     * 初始化
     *
     * @param object 组件
     */
    public RemarkComponent(SectionComponent object) {
        jsonCard.put("type", "remark");
        jsonCard.put("elements", new JSONArray());
        addElement(object);
    }


    /**
     * 增加备注
     *
     * @param object 数据
     */
    public void addElement(SectionComponent object) {
        if (object.isParagraph) {
            System.out.println("错误的传参(多栏文本)");
        }
        jsonCard.getJSONArray("elements").put(object.getJsonCard());
    }

    /**
     * 移除备注
     *
     * @param component 备注
     */
    public void removeImage(Component component) {
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
            removeRemark(integerList.get(i) - i);
        }
    }

    /**
     * 删除一个备注
     *
     * @param index index
     */
    public void removeRemark(int index) {
        jsonCard.getJSONArray("elements").remove(index);
    }
}
