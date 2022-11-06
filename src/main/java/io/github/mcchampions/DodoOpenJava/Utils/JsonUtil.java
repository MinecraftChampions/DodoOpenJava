package io.github.mcchampions.DodoOpenJava.Utils;

import org.json.JSONObject;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Map;

/**
 * �й��� JSON �����ʵ���Է���
 * @author qscbm187531
 */
public class JsonUtil {
    /**
     * ת���ַ���Ϊjson
     *
     * @param string �ַ���
     * @return JSONObject
     */
    public static JSONObject parseJson(String string) {
        return new JSONObject(string);
    }



    /**
     * ת��BsonΪjson
     *
     * @param bson Bson
     * @return JSONObject
     */
    public static JSONObject bsonToJson(Bson bson) {
        return new JSONObject(bson.toBsonDocument().toJson());
    }

    /**
     * ת��DocumentΪjson
     *
     * @param document Document
     * @return JSONObject
     */
    public static JSONObject documentToJson(Document document) {
        return new JSONObject(document.toJson());
    }

    /**
     * jsonת��Ϊ�ַ���
     *
     * @param obj obj
     * @return json
     */
    public static String toString(JSONObject obj) {
        return obj.toString();
    }

    /**
     * ��jsonװ��Ϊmap
     * @param jsonObject json
     * @return map
     */
    public static Map<String,Object> toMap(JSONObject jsonObject) {
        return jsonObject.toMap();
    }
}
