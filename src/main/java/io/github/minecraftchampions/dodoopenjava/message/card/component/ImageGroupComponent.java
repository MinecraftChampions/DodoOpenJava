package io.github.minecraftchampions.dodoopenjava.message.card.component;

import io.github.minecraftchampions.dodoopenjava.message.card.element.ImageElement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@NoArgsConstructor(staticName = "of")
public class ImageGroupComponent implements CardComponent {
    private final List<ImageElement> imageElements = new ArrayList<>();
    @Getter
    private final String type = "image-group";

    @Override
    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject(Map.of("type",getType(),"elements",new JSONArray()));
        imageElements.forEach(image ->jsonObject.getJSONArray("elements").put(image.toJSONObject()));
        return jsonObject;
    }

    public ImageGroupComponent of(@NonNull ImageElement... elements) {
        ImageGroupComponent imageGroupComponent = of();
        for (ImageElement e : elements) {
            imageGroupComponent.append(e);
        }
        return this;
    }

    public ImageGroupComponent append(@NonNull ImageElement image) {
        synchronized (imageElements) {
            imageElements.add(image);
            return this;
        }
    }

    public ImageElement get(int size) {
        synchronized (imageElements) {
            return imageElements.get(size);
        }
    }


    public ImageGroupComponent prepend(@NonNull ImageElement image) {
        synchronized (imageElements) {
            imageElements.add(0, image);
            return this;
        }
    }

    public ImageGroupComponent insert(int index, @NonNull ImageElement image) {
        synchronized (imageElements) {
            imageElements.add(index, image);
            return this;
        }
    }

    public void remove(int index) {
        synchronized (imageElements) {
            imageElements.remove(index);
        }
    }

    public int size() {
        return imageElements.size();
    }

    public ImageGroupComponent image(@NonNull ImageElement imageElement) {
        return append(imageElement);
    }

    public ImageGroupComponent image(@NonNull String link) {
        return append(ImageElement.of(link));
    }
}
