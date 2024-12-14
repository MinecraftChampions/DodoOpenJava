package io.github.minecraftchampions.dodoopenjava.message.card.component;

import io.github.minecraftchampions.dodoopenjava.message.card.element.ImageElement;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 多图组件
 *
 * @author qscbm187531
 */
@NoArgsConstructor(staticName = "of")
@Getter
public class ImageGroupComponent implements CardComponent {
    @Getter(AccessLevel.NONE)
    private final List<ImageElement> elementList = new ArrayList<>();

    private static final String type = "image-group";

    public String getType() {
        return type;
    }

    @Override
    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject(Map.of("type", type, "elements", new JSONArray()));
        elementList.forEach(image -> jsonObject.getJSONArray("elements").put(image.toJsonObject()));
        return jsonObject;
    }

    public static ImageGroupComponent of(@NonNull ImageElement... elements) {
        ImageGroupComponent imageGroupComponent = of();
        imageGroupComponent.elementList.addAll(List.of(elements));
        return imageGroupComponent;
    }

    public static ImageGroupComponent of(@NonNull String... links) {
        ImageElement[] imageElements = new ImageElement[links.length];
        for (int i = 0; i < links.length; i++) {
            imageElements[i] = ImageElement.of(links[i]);
        }
        return of(imageElements);
    }

    public ImageGroupComponent append(@NonNull ImageElement image) {
        synchronized (elementList) {
            elementList.add(image);
            return this;
        }
    }

    public ImageElement get(int size) {
        synchronized (elementList) {
            return elementList.get(size);
        }
    }


    public ImageGroupComponent prepend(@NonNull ImageElement image) {
        synchronized (elementList) {
            elementList.add(0, image);
            return this;
        }
    }

    public ImageGroupComponent insert(int index, @NonNull ImageElement image) {
        synchronized (elementList) {
            elementList.add(index, image);
            return this;
        }
    }

    public void remove(int index) {
        synchronized (elementList) {
            elementList.remove(index);
        }
    }

    public int size() {
        return elementList.size();
    }

    public ImageGroupComponent image(@NonNull ImageElement imageElement) {
        return append(imageElement);
    }

    public ImageGroupComponent image(@NonNull String link) {
        return append(ImageElement.of(link));
    }
}