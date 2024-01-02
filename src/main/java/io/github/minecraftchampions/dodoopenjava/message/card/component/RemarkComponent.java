package io.github.minecraftchampions.dodoopenjava.message.card.component;

import lombok.NonNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 备注组件
 */
public class RemarkComponent extends CardComponent {
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
    public RemarkComponent(@NonNull ImageComponent object) {
        jsonCard.put("type", "remark");
        jsonCard.put("elements", new JSONArray());
        addElement(object);
    }

    /**
     * 增加备注
     *
     * @param object 数据
     */
    public void addElement(@NonNull ImageComponent object) {
        jsonCard.getJSONArray("elements").put(object.getJsonCard());
    }

    /**
     * 初始化
     *
     * @param object 组件
     */
    public RemarkComponent(@NonNull SectionComponent object) {
        jsonCard.put("type", "remark");
        jsonCard.put("elements", new JSONArray());
        addElement(object);
    }


    /**
     * 增加备注
     *
     * @param object 数据
     */
    public void addElement(@NonNull SectionComponent object) {
        if (object.isParagraph) {
            System.out.println("错误的传参(多栏文本)");
        }
        jsonCard.getJSONArray("elements").put(object.getJsonCard());
    }

    /**
     * 移除备注
     *
     * @param cardComponent 备注
     */
    public void removeImage(@NonNull CardComponent cardComponent) {
        List<Object> list = jsonCard.getJSONArray("element").toList();
        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Object object = list.get(i);
            if (object instanceof JSONObject jsonObject) {
                if (cardComponent.getJsonCard() == jsonObject) {
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
