# DodoOpenJava <img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white" alt="Java"> [![](https://jitpack.io/v/top.qscraft/dodoopenjava.svg)](https://jitpack.io/#top.qscraft/dodoopenjava)

Dodo开放平台的JavaSDK

JavaDoc：https://qscraft.top/javadoc

Dodo开放平台：https://open.imdodo.com/

### 代码范例
```java
package io.github.minecraftchampions.dodoopenjava.test;

import io.github.minecraftchampions.dodoopenjava.Bot;
import io.github.minecraftchampions.dodoopenjava.DodoOpenJava;
import io.github.minecraftchampions.dodoopenjava.command.CommandExecutor;
import io.github.minecraftchampions.dodoopenjava.command.CommandSender;
import io.github.minecraftchampions.dodoopenjava.event.EventHandler;
import io.github.minecraftchampions.dodoopenjava.event.Listener;
import io.github.minecraftchampions.dodoopenjava.event.events.v2.channelmessage.MessageEvent;

import java.io.IOException;

public class Main implements CommandExecutor,Listener{
    public static Bot bot;
    public static void main(String... args) {
        //创建机器人实例
        bot = DodoOpenJava.createBot("111111","token");
        //注册事件监听器
        bot.registerListener(new Main());
        //注册命令处理器
        bot.registerCommand(new Main());
        System.out.println(bot.getApi().V2.eventApi.getWebSocketConnection().getJSONObjectData().getString("endpoint"));
        bot.getApi().V2.botApi.getBotInfo().ifSuccess(result -> {
            System.out.println("成功");
            System.out.println(result.getJSONObjectData());
        });

        bot.getApi().V2.channelMessageApi.sendTextMessage("1252355","测试");
    }

    @EventHandler
    public void onEvent(MessageEvent e) {
        System.out.println(e.getEventName());
        System.out.println(e.getMessageId());
        bot.getApi().V2.channelMessageApi.sendTextMessage(e.getChannelId(),"你发送了" + e.getMessageBody());
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
        System.out.println(commandSender.getSenderName());
        try {
            commandSender.editSenderNickName("测试名字");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        bot.getApi().V2.channelMessageApi.sendTextMessage(commandSender.getChannelId(),"测试成功");

    }
}

```
### 计划
~~目前，计划大改SDK，可感觉似乎kotlin可好用，正在学习中~~

~~想了想，还是java好，准备去除多模块开始大改~~

~~合成了一个模块，但是现在编译后有46MB，疑似有些太臃肿了，为了一个小功能引进了许多前置库，接下来的计划就是慢慢删减掉这些无用的前置~~

减到0.8MB了

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
    <groupId>top.qscraft</groupId>
    <artifactId>dodoopenjava</artifactId>
    <version>3.1.9</version>
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
	        implementation 'top.qscraft:dodoopenjava:3.1.9'
    }
```
### 教程(过于古老，无参考价值，改日重写)
[直达链接](https://www.showdoc.com.cn/DodoOpenJava/)
