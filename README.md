# DodoOpenJava <img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white" alt="Java"> [![](https://jitpack.io/v/top.qscraft/dodoopenjava.svg)](https://jitpack.io/#top.qscraft/dodoopenjava)

Dodo开放平台的JavaSDK

JavaDoc：https://qscraft.top/javadoc

Dodo开放平台：https://open.imdodo.com/

### 代码范例

```java
package io.github.minecraftchampions.dodoopenjava.test;

import io.github.minecraftchampions.dodoopenjava.DodoOpenJava;
import io.github.minecraftchampions.dodoopenjava.command.CommandExecutor;
import io.github.minecraftchampions.dodoopenjava.api.CommandSender;
import io.github.minecraftchampions.dodoopenjava.event.EventHandler;
import io.github.minecraftchampions.dodoopenjava.event.Listener;
import io.github.minecraftchampions.dodoopenjava.event.events.v2.channelmessage.MessageEvent;
import io.github.minecraftchampions.dodoopenjava.api.Bot;

public class Main implements CommandExecutor, Listener {
    public static Bot bot;

    public static void main(String... args) {
        // 创建机器人实例
        bot = DodoOpenJava.createBot("111111", "token");
        // 采用WebHook监听事件
        // WebHookEventTrigger webHookEventTrigger = new WebHookEventTrigger(bot,"密钥","ssl证书密码",new File("C:\\abc.com.jks"));
        // bot.initEventListenSystem(webHookEventTrigger);
        //启用日志记录功能
        bot.enableApiResultsLogger();
        // 注册事件监听器
        bot.registerListener(new Main());
        // 注册命令处理器
        bot.registerCommand(new Main());
        // bot.getCommandManager().getCommandTrigger().setCommandHeader("/");;
        System.out.println(bot.getApi().V2.eventApi.getWebSocketConnection().getJSONObjectData().getJSONObject("data").getString("endpoint"));
        bot.getApi().V2.botApi.getBotInfo().ifSuccess(result -> {
            System.out.println("成功");
        });

        bot.getApi().V2.channelMessageApi.sendTextMessage("1252355", "测试");
        testCard();
        System.out.println(bot.getApiResultsLogger());
    }

    static void testCard() {
        SectionComponent normalSection = SectionComponent.of("卡片消息文本测试1");
        SectionComponent paragraphText = SectionComponent.of("卡片消息多栏文本测试1",
                "卡片消息多栏文本测试2");
        ImageElement imageElement1 = ImageElement.of("https://img.imdodo.com/upload/cdn/09151DF5C726C6E2F5915E5B117EF98E_1660189645615.png");
        ImageComponent imageComponent1 = ImageComponent.of(imageElement1);
        ImageElement imageElement2 = ImageElement.of("https://img.imdodo.com/upload/cdn/09151DF5C726C6E2F5915E5B117EF98E_1660189645615.png");
        ImageGroupComponent imageGroupComponent = ImageGroupComponent.of(imageElement1, imageElement2);
        SectionWithModuleComponent moduleComponent1 = SectionWithModuleComponent.of("文字+模块测试1", imageElement1);
        HeaderComponent headerComponent = HeaderComponent.of("***标题测试***", TextType.Markdown);
        RemarkComponent remarkComponent = RemarkComponent.of(imageElement1, imageElement2, TextElement.newNormalText("备注文本测试"));
        VideoComponent videoComponent = VideoComponent.of("屏幕内覆盖视频地址", "https://img.imdodo.com/dodo/2493bf9b000b8dc18e77d079ac517bb9.png", "https://video.imdodo.com/dodo/7f0a1979c818fa05cf7bdeae20aad24b.mp4");
        CountdownComponent countdownComponent = CountdownComponent.of(CountdownStyle.Day, 1760644927968L);
        OptionElement optionElement1 = OptionElement.of("选项1", "一个选项");
        OptionElement optionElement2 = OptionElement.of("选项2");
        ListSelectorComponent listSelectorComponent = ListSelectorComponent.of(1, 2, "id", "请选择", optionElement1, optionElement2);
        InputElement inputElement1 = InputElement.of("title1", "input1", 2, 1, 2500, "请输入");
        InputElement inputElement2 = InputElement.of("title2", "input2", 2, 1, 2500);
        Form form = Form.of("title");
        form.append(inputElement1).append(inputElement2);
        ButtonElement.Action action1 = ButtonElement.Action.form(form);
        ButtonElement.Action action2 = ButtonElement.Action.of(ActionType.link_url);
        action2.setValue("https://github.com");
        ButtonElement.Action action3 = ButtonElement.Action.of(ActionType.copy_content);
        action3.setValue("复制");
        ButtonElement.Action action4 = ButtonElement.Action.of(ActionType.call_back);
        action4.setValue("message");
        ButtonElement buttonElement1 = ButtonElement.of(ButtonColor.Blue, "跳转", action2);
        ButtonElement buttonElement2 = ButtonElement.of(ButtonColor.Grey, "表单", action1);
        ButtonElement buttonElement3 = ButtonElement.of(ButtonColor.Green, "复制", action3);
        ButtonElement buttonElement4 = ButtonElement.of(ButtonColor.Orange, "回传", action4);
        SectionWithModuleComponent moduleComponent2 = SectionWithModuleComponent.of("文字+模块测试2", buttonElement3);
        ButtonGroupComponent buttonGroupComponent = ButtonGroupComponent.of(buttonElement1, buttonElement2, buttonElement3, buttonElement4);
        CardMessage.Builder builder = CardMessage.builder();
        CardMessage m = builder.content("Dodo卡片消息附加文本测试").title("Dodo卡片消息标题测试").theme(Theme.Black)
                .button(buttonGroupComponent).header(headerComponent).section(normalSection)
                .section(paragraphText).image(imageComponent1).images(imageGroupComponent).divider()
                .remark(remarkComponent).countdown(countdownComponent).video(videoComponent)
                .button(buttonGroupComponent).section(moduleComponent1).listSelector(listSelectorComponent)
                .section(moduleComponent2).build();
        bot.getApi().V2.channelMessageApi.sendMessage("1252355", m);
        //你也可以通过调用io.github.minecraftchampions.dodoopenjava.api.v2下的各个类的方法来调用Dodo接口
    }

    @EventHandler
    public void onEvent(MessageEvent e) {
        System.out.println(e.getEventName());
        System.out.println(e.getMessageId());
        bot.getApi().V2.channelMessageApi.sendTextMessage(e.getChannelId(), "你发送了" + e.getMessageBody());
    }

    @Override
    public String getMainCommand() {
        return "test";
    }

    @Override
    public String getPermission() {
        return null;
    }

    @Override
    public void onCommand(CommandSender commandSender, String[] strings) {
        System.out.println(commandSender.getName());
        commandSender.editNickName("测试名字");
        bot.getApi().V2.channelMessageApi.sendTextMessage(commandSender.getChannelId(), "测试成功");
    }
}

```
### 计划
- [X] 减小大小
- [X] 重构卡片消息
- [ ] 将卡片回传参数处理包含在卡片消息部分

### 添加依赖
#### Maven：
```xml
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>
```
```xml
<dependencies>
  <dependency>
    <groupId>icu.qscraft</groupId>
    <artifactId>dodoopenjava</artifactId>
    <version>3.3.0-SNAPSHOT</version>
  </dependency>
</dependencies>
```
#### Gradle
```groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    implementation 'icu.qscraft:dodoopenjava:3.3.0-SNAPSHOT'
}
```
### 教程(过于古老，无参考价值，改日重写)
[直达链接](https://www.showdoc.com.cn/DodoOpenJava/)

