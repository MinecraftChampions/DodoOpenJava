package io.github.minecraftchampions.dodoopenjava.card.component;

import io.github.minecraftchampions.dodoopenjava.card.enums.Align;

/**
 * 文字和模块
 */
public class SectionAndButtonComponent extends Component {
     /**
      * 初始化
      * @param align 对齐方式，left：左对齐，right：右对齐
      * @param sectionComponent 文字
      * @param buttonGroupComponent 按钮
      */
     public SectionAndButtonComponent(Align align,ButtonGroupComponent buttonGroupComponent,SectionComponent sectionComponent) {
          if (sectionComponent.isParagraph) {
               return;
          }
          jsonCard.put("type", "section");
          jsonCard.put("text", sectionComponent.getJsonCard());
          jsonCard.put("align", align.getType());
          jsonCard.put("accessory", buttonGroupComponent.getJsonCard());
     }

     /**
      * 初始化
      * @param align 对齐方式，left：左对齐，right：右对齐
      * @param sectionComponent 文字
      * @param imageComponent 图片
      */
     public SectionAndButtonComponent(Align align,ImageComponent imageComponent,SectionComponent sectionComponent) {
          if (sectionComponent.isParagraph) {
               return;
          }
          jsonCard.put("type", "section");
          jsonCard.put("text", sectionComponent.getJsonCard());
          jsonCard.put("align", align.getType());
          jsonCard.put("accessory", imageComponent.getJsonCard());
     }

     /**
      * 编辑对齐方式
      * @param align 对齐方式
      */
     public void editAlign(Align align) {
          jsonCard.put("align",align.getType());
     }
}
