package io.github.minecraftchampions.dodoopenjava.event.events.v2;

import io.github.minecraftchampions.dodoopenjava.event.Event;
import io.github.minecraftchampions.dodoopenjava.event.HandlerList;
import io.github.minecraftchampions.dodoopenjava.utils.BaseUtil;
import org.json.JSONObject;

import javax.annotation.Nonnull;
import java.util.List;

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
     * ��ȡ������
     * @return ������
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * ��ȡ��Ʒ����
     * 1������飬2���Զ�����Ʒ
     * @return ��Ʒ����
     */
    public int getGoodsType() {
        return goodsType;
    }

    /**
     * ��ȡ��ƷID
     * @return ��ƷID
     */
    public String getGoodsId() {
        return goodsId;
    }

    /**
     * ��ȡ��ƷͼƬ�б����Ӻ�Ӳ������Ե���ͼƬ��ʽ
     * @return ͼƬ�б�
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * ��ȡ������
     * @return ������
     */
    public List<String> getGoodsImageList() {
        return goodsImageList;
    }

    /**
     * ��ȡʱ���
     * @return ����ʱ���
     */
    public Integer getTimestamp() {
        return this.timestamp;
    }

    /**
     * ��ȡ�¼�ID
     * @return �¼�ID
     */
    public String getEventId() {
        return this.eventId;
    }

    /**
     * ��ȡȺ��
     * @return Ⱥ��
     */
    public String getIslandSourceId() {
        return this.islandSourceId;
    }

    /**
     * ��ȡDodoId
     * @return DodoSourceId
     */
    public String getDodoSourceId() {
        return this.dodoSourceId;
    }

    /**
     * ��ȡ�䶯ʱ��
     * @return �䶯ʱ��
     */
    public String getModifyTime() {
        return this.modifyTime;
    }

    /**
     * ��ȡJSONObject
     * @return JSONObject
     */
    public JSONObject getJsonObject() {
        return this.jsonObject;
    }

    /**
     * ��ȡJsonString
     * @return String
     */
    public String getJsonString() {
        return this.jsonString;
    }
}
