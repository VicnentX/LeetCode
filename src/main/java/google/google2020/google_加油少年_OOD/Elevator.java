package google.google2020.google_加油少年_OOD;

/*
首先确认电梯运行的状态：
    运行状态 跑还是待命
    运行方向
    楼层
    去的楼层
 */

/*
所以我们差不多要4个东西
Controller 把user那边的信号转成数据给scheduler

Scheduler   把input变成command再决定分配给哪一台电梯

Command Queue 就是每个电梯任务的queue（电梯本身不断监听这个queue）

Elevator 这就是电梯的状态（上面提到的）
 */

public class Elevator {
}
