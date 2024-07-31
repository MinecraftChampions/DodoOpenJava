package io.github.minecraftchampions.dodoopenjava.api.v2;

import io.github.minecraftchampions.dodoopenjava.DodoOpenJava;
import io.github.minecraftchampions.dodoopenjava.api.Bot;
import io.github.minecraftchampions.dodoopenjava.debug.Result;
import io.github.minecraftchampions.dodoopenjava.utils.NetUtils;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * 资源API
 *
 * @author qscbm187531
 */
@Data
@RequiredArgsConstructor
public class ResourceApi {
   @NonNull
   private Bot bot;

    /**
     * 上传资源
     *
     * @param path          路径
     * @return result
     */
    public Result uploadResource(String path) {
        String url = DodoOpenJava.BASEURL + "resource/picture/upload";
        return NetUtils.uploadFileToDodo(new HashMap<>(Map.of("Authorization", bot.getAuthorization())), path, url);
    }
}
