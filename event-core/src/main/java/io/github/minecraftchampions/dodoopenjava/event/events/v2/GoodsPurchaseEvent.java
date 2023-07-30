package io.github.minecraftchampions.dodoopenjava.event.events.v2;

import io.github.minecraftchampions.dodoopenjava.event.Event;
import io.github.minecraftchampions.dodoopenjava.event.HandlerList;
import io.github.minecraftchampions.dodoopenjava.utils.BaseUtil;
import org.json.JSONObject;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * 商品购买成功事件
 */
public class GoodsPurchaseEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    @Override
    @Nonnull
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Integer timestamp;

    public String eventId;

    public String islandSourceId;

    public String dodoSourceId;

    public String modifyTime;

    public JSONObject jsonObject;

    public String jsonString;

    public String orderNo;

    public int goodsType;

    public String goodsId;

    public String goodsName;

    public List<String> goodsImageList;

    public GoodsPurchaseEvent(JSONObject json) {
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
    }

    /**
     * 获取订单号
     *
     * @return 订单号
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 获取商品类型
     * 1：身份组，2：自定义商品
     *
     * @return 商品类型
     */
    public int getGoodsType() {
        return goodsType;
    }

    /**
     * 获取商品ID
     *
     * @return 商品ID
     */
    public String getGoodsId() {
        return goodsId;
    }

    /**
     * 获取商品图片列表，链接后接参数可以调整图片样式
     *
     * @return 图片列表
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * 获取订单号
     *
     * @return 订单号
     */
    public List<String> getGoodsImageList() {
        return goodsImageList;
    }

    /**
     * 获取时间戳
     *
     * @return 返回时间戳
     */
    public Integer getTimestamp() {
        return this.timestamp;
    }

    /**
     * 获取事件ID
     *
     * @return 事件ID
     */
    public String getEventId() {
        return this.eventId;
    }

    /**
     * 获取群号
     *
     * @return 群号
     */
    public String getIslandSourceId() {
        return this.islandSourceId;
    }

    /**
     * 获取DodoId
     *
     * @return DodoSourceId
     */
    public String getDodoSourceId() {
        return this.dodoSourceId;
    }

    /**
     * 获取变动时间
     *
     * @return 变动时间
     */
    public String getModifyTime() {
        return this.modifyTime;
    }

    /**
     * 获取JSONObject
     *
     * @return JSONObject
     */
    public JSONObject getJsonObject() {
        return this.jsonObject;
    }

    /**
     * 获取JsonString
     *
     * @return String
     */
    public String getJsonString() {
        return this.jsonString;
    }
}
