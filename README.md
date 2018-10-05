#WeiXin#

微信公众号接口。

参考:
[[接口文档]](http://mp.weixin.qq.com/wiki/home/index.html "接口文档") 
[[第三方公众平台文档]](https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1419318292&token=&lang=zh_CN)
[[测试地址]](http://mp.weixin.qq.com/debug/cgi-bin/sandbox?t=sandbox/login "测试地址") 
[[本机公网发布]](https://ngrok.com "ngrok")

## 消息返回 ##

`Service` 的实现方法中返回的字符串会被写回到请求流中，需要返回消息时，调用 `com.chn.wx.template.PassiveMessage` 中的对应方法生成报文返回即可。
> **注意：**仅同步执行时该返回有效

## 主动调用 ##

所有实例获取可以通过 `@Autowired`，也可以通过 `FactoryUtils.getInstance()`

- `com.chn.wx.api.GroupManager` 分组管理
- `com.chn.wx.api.MaterialManager` 素材管理
- `com.chn.wx.api.MenuManager` 菜单管理
- `com.chn.wx.api.PlatFormManager` 作为第三方平台运行时的相关接口
- `com.chn.wx.api.PlatFormTokenAccessor` 第三方平台相关接口的 token 获取入口
- `com.chn.wx.api.ServiceMessageSender` 客服消息发送
- `com.chn.wx.api.TokenAccessor` 唯一的 token 获取入口, token 只能能过该类获取, 不能另做缓存
- `com.chn.wx.api.UrlTransformer` 短地址服务
- `com.chn.wx.api.UserManager` 用户管理

