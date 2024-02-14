package io.github.minecraftchampions.dodoopenjava.event.events.v2.shop;

import io.github.minecraftchampions.dodoopenjava.utils.BaseUtil;
import lombok.Getter;
import org.json.JSONObject;

import java.util.List;

/**
 * 商品购买成功事件
 *
 * @author qscbm187531
 */
@Getter
public class GoodsPurchaseEvent extends AbstractShopEvent {
    /**
     * -- GETTER --
     * 获取时间戳
     */
    public Integer timestamp;

    /**
     * -- GETTER --
     * 获取事件ID
     */
    public String eventId;

    /**
     * -- GETTER --
     * 获取群号
     */
    public String islandSourceId;

    /**
     * -- GETTER --
     * 获取DodoId
     */
    public String dodoSourceId;

    /**
     * -- GETTER --
     * 获取变动时间
     */
    public String modifyTime;

    /**
     * -- GETTER --
     * 获取JSONObject
     */
    public JSONObject jsonObject;

    /**
     * -- GETTER --
     * 获取JsonString
     */
    public String jsonString;

    /**
     * -- GETTER --
     * 获取订单号
     */
    public String orderNo;

    /**
     * -- GETTER --
     * 获取商品类型
     * 1：身份组，2：自定义商品
     */
    public int goodsType;

    /**
     * -- GETTER --
     * 获取商品ID
     */
    public String goodsId;

    /**
     * -- GETTER --
     * 获取商品图片列表，链接后接参数可以调整图片样式
     */
    public String goodsName;

    /**
     * -- GETTER --
     * 获取订单号
     */
    public List<String> goodsImageList;

    public GoodsPurchaseEvent(JSONObject json) {
        super(true);
        this.jsonObject = json;
        this.jsonString = json.toString();
        this.timestamp = json.getJSONObject("data").getInt("timestamp");
        this.eventId = json.getJSONObject("data").getString("eventId");
        this.islandSourceId = json.getJSONObject("data").getJSONObject("eventBody").getString("islandSourceId");
        this.dodoSourceId = json.getJSONObject("data").getJSONObject("eventBody").getString("dodoSourceId");
        this.modifyTime = json.getJSONObject("data").getJSONObject("eventBody").getString("modifyTime");
        this.orderNo = json.getJSONObject("data").getJSONObject("eventBody").getString("orderNo");
        this.goodsType = json.getJSONObject("data").getJSONObject("eventBody").getInt("goodsType");
        this.goodsId = json.getJSONObject("data").getJSONObject("eventBody").getString("goodsId");
        this.goodsName = json.getJSONObject("data").getJSONObject("eventBody").getString("goodsName");
        this.goodsImageList = BaseUtil.toStringList(json.getJSONObject("data").getJSONObject("eventBody").getJSONArray("goodsImageList").toList());
        eventType = GoodsPurchaseEvent.class;
    }
}
