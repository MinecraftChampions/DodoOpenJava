package io.github.minecraftchampions.dodoopenjava.event.events.v2.shop;

import io.github.minecraftchampions.dodoopenjava.utils.BaseUtils;
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
     * 获取群号
     */
    protected String islandSourceId;

    /**
     * -- GETTER --
     * 获取DodoId
     */
    protected String dodoSourceId;

    /**
     * -- GETTER --
     * 获取变动时间
     */
    protected String modifyTime;

    /**
     * -- GETTER --
     * 获取JSONObject
     */
    protected JSONObject jsonObject;

    /**
     * -- GETTER --
     * 获取JsonString
     */
    protected String jsonString;

    /**
     * -- GETTER --
     * 获取订单号
     */
    protected String orderNo;

    /**
     * -- GETTER --
     * 获取商品类型
     * 1：身份组，2：自定义商品
     */
    protected int goodsType;

    /**
     * -- GETTER --
     * 获取商品ID
     */
    protected String goodsId;

    /**
     * -- GETTER --
     * 获取商品图片列表，链接后接参数可以调整图片样式
     */
    protected String goodsName;

    /**
     * -- GETTER --
     * 获取订单号
     */
    protected List<String> goodsImageList;

    public GoodsPurchaseEvent(JSONObject json) {
        super(true);
        this.jsonObject = json;
        this.jsonString = json.toString();
        this.timestamp = json.getJSONObject("data").getLong("timestamp");
        this.eventId = json.getJSONObject("data").getString("eventId");
        this.islandSourceId = json.getJSONObject("data").getJSONObject("eventBody").getString("islandSourceId");
        this.dodoSourceId = json.getJSONObject("data").getJSONObject("eventBody").getString("dodoSourceId");
        this.modifyTime = json.getJSONObject("data").getJSONObject("eventBody").getString("modifyTime");
        this.orderNo = json.getJSONObject("data").getJSONObject("eventBody").getString("orderNo");
        this.goodsType = json.getJSONObject("data").getJSONObject("eventBody").getInt("goodsType");
        this.goodsId = json.getJSONObject("data").getJSONObject("eventBody").getString("goodsId");
        this.goodsName = json.getJSONObject("data").getJSONObject("eventBody").getString("goodsName");
        this.goodsImageList = BaseUtils.toStringList(json.getJSONObject("data").getJSONObject("eventBody").getJSONArray("goodsImageList").toList());
        eventType = GoodsPurchaseEvent.class;
    }
}