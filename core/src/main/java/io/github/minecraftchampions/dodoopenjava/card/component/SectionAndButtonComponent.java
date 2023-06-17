package io.github.minecraftchampions.dodoopenjava.card.component;

import io.github.minecraftchampions.dodoopenjava.card.enums.Align;

/**
 * ���ֺ�ģ��
 */
public class SectionAndButtonComponent extends Component {
     /**
      * ��ʼ��
      * @param align ���뷽ʽ��left������룬right���Ҷ���
      * @param sectionComponent ����
      * @param buttonGroupComponent ��ť
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
      * ��ʼ��
      * @param align ���뷽ʽ��left������룬right���Ҷ���
      * @param sectionComponent ����
      * @param imageComponent ͼƬ
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
      * �༭���뷽ʽ
      * @param align ���뷽ʽ
      */
     public void editAlign(Align align) {
          jsonCard.put("align",align.getType());
     }
}
