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

@NoArgsConstructor(staticName = "of")
@Getter
public class ImageGroupComponent implements CardComponent {
    @Getter(AccessLevel.NONE)
    private final List<ImageElement> elementList = new ArrayList<>();

    private final String type = "image-group";

    @Override
    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject(Map.of("type",getType(),"elements",new JSONArray()));
        elementList.forEach(image ->jsonObject.getJSONArray("elements").put(image.toJSONObject()));
        return jsonObject;
    }

    public static ImageGroupComponent of(@NonNull ImageElement... elements) {
        ImageGroupComponent imageGroupComponent = of();
        imageGroupComponent.elementList.addAll(List.of(elements));
        return imageGroupComponent;
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
