package Amazon.full_time2020.final_round_加油少年_OOD;

/*
让我写一段instant message app 的 server side code，



满足三个要求：1. user can connect to server  2. user can disconnect from server  3. user can send message to another user (connected)
其实感觉很模糊，就一直问问题试图搞懂，比如
用户之间发消息是不是要经过server？  是的
两个用户都必须在线才能发消息吗？  是的
。。。
后来他帮忙写了个框架出来
class ？{
  onUserConnect
  onUserDisconnect
  onUserSendMessage
}
让我自己填充细节，我就写了个user类，然后加了几个helper function之类的
follow up：现在加个group，你要怎么handle新的要求：4. user can send message to a group
我几乎要写个group class了，然后大哥又给‍‍‍‌‌‌‍‌‍‌‌‍‍‍‍‌‌‌‍我加了几个function框架，告诉我groupName是个string
后来他看完一脸懵逼，告诉我可以在server里加个field，Map<String, List<User>>
最后例行问问题，没什么力气

 */

public class InstantMessageApp {
}
