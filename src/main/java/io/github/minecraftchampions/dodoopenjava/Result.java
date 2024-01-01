package io.github.minecraftchampions.dodoopenjava;

import io.github.minecraftchampions.dodoopenjava.utils.Equal;
import lombok.Getter;
import lombok.NonNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.function.Consumer;

/**
 * api结果
 */
@Getter
public class Result {
    public static final JSONObject STATUS_CODE = new JSONObject("""
            {
                "0": "成功",
                "10000": "请求参数非法",
                "10001": "鉴权clientId或者token 两个字段都不能为空",
                "10002": "请求参数不能为空",
                "10003": "不是成员",
                "10004": "没有mqtt服务连接点",
                "10005": "鉴权失败",
                "10006": "修改昵称用户不属于你的群",
                "10007": "机器人被封禁",
                "10008": "clientId不存在",
                "10009": "群不存在",
                "10010": "频道不存在",
                "10011": "只允许在文字频道发送消息",
                "10012": "验证签名timestamp字段不能为空",
                "10013": "验证签名sign字段不能为空",
                "10014": "签名url过期",
                "10015": "签名失败",
                "10016": "只允许发送域名包含imdodo.com的资源",
                "10017": "用户不存在",
                "10018": "消息不存在",
                "10019": "文字内容校验异常",
                "10020": "昵称长度不能超过32字符",
                "10021": "介绍长度不能超过200字符",
                "10022": "记录已存在,请勿重复操作",
                "10023": "机器人创建数量上限为20，你已达到创建数量上限",
                "10024": "机器人调用太频繁，触发限流",
                "10025": "无权限操作",
                "10026": "系统负载过高，请稍后重试",
                "10027": "账号不存在",
                "10028": "开发者未被认证，不能创建机器人",
                "10029": "开发者被封禁中",
                "10030": "手机号非法",
                "10031": "验证码发送频繁，请稍后再试",
                "10032": "机器人不能被邀请",
                "10033": "机器人不存在",
                "10034": "验证码已过期",
                "10035": "验证码错误",
                "10036": "登录token不能为空",
                "10037": "登录token已过期",
                "10038": "登录token非法",
                "10039": "您今日邀请用户数已达上限（20个），请明日再试",
                "10042": "暂未开放机器人@所有人功能",
                "10043": "业务签名失败",
                "10044": "暂未开放机器人@身份组功能",
                "10045": "您的账号已被注销",
                "10082": "该群已被限制发言频率处罚，1分钟内仅可发送一条消息",
                "10083": "该群已被限制发言频率处罚，1分钟内仅可编辑一条消息",
                "10084": "当前群未开通邀请系统",
                "10085": "当前用户未绑定高能链钱包或未开启数字藏品展示",
                "10086": "模版机器人不允许操作",
                "10087": "未开通群等级",
                "10088": "用户未开启数字藏品展示",
                "10089": "权限值非法",
                "10090": "组不存在",
                "10091": "权限值有误，请检查操作身份组时允许的权限",
                "10092": "系统维护中，暂不支持修改",
                "10093": "系统维护中，暂不支持修改",
                "10094": "系统维护中，暂时无法发布内容",
                "10095": "系统维护中，暂不支持修改",
                "10096": "帖子不存在",
                "10097": "帖子已被删除",
                "10099": "本群未开启赠礼系统",
                "10100": "该类型消息不允许置顶",
                "10101": "消息已处于置顶状态",
                "10102": "该群开通了隐私保护模式，且您没有管理成员或超级管理员权限",
                "10103": "对方不是该群成员",
                "10104": "本群未开启积分系统",
                "-9999": "系统异常"
            }
            """);

    private final int statusCode;

    private final String message;

    private final long timestamp;

    private Object data;

    private Result(@NonNull JSONObject jsonObject) {
        this.statusCode = jsonObject.getInt("status");
        this.message = jsonObject.getString("message");
        this.timestamp = System.currentTimeMillis();
        Equal.of(statusCode).equal(0).ifEquals(code -> this.data = jsonObject.get("data"));
    }

    /**
     * 初始化
     * @param jsonObject jsonObject
     * @return Result
     */
    public static Result of(@NonNull JSONObject jsonObject) {
        return new Result(jsonObject);
    }

    /**
     * 如果成功
     * @param consumer consumer
     * @return this
     */
    public Result ifSuccess(@NonNull Consumer<Result> consumer) {
        Equal.of(statusCode).equal(0).ifEquals(code -> consumer.accept(this));
        return this;
    }

    /**
     * 如果失败
     * @param consumer consumer
     * @return this
     */
    public Result ifFailure(@NonNull Consumer<Result> consumer) {
        Equal.of(statusCode).equal(0).ifNoEquals(code -> consumer.accept(this));
        return this;
    }

    /**
     * 获取
     * @return jsonObject
     */
    public JSONObject getJSONObjectData() {
        if (data instanceof JSONObject jsonObject) {
            return jsonObject;
        } else {
            throw new RuntimeException();
        }
    }

    /**
     * 获取
     * @return jsonArray
     */
    public JSONArray getJSONArrayData() {
        if (data instanceof JSONArray jsonArray) {
            return jsonArray;
        } else {
            throw new RuntimeException();
        }
    }
}
