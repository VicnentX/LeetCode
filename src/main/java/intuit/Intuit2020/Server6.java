package intuit.Intuit2020;


import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * clarify the questions:
 *      could be multiple inheritance?
 *      input format?
 */
/*
题目如下：给一个text format的file包含了server的ID和其他信息，ID是放在方括号里。比如[foo]和[bar:foo]。第二个例子里bar会继承foo的一些属性，bar自己带新的属性或者可以override foo的属性。题目要求是写一个function parse这个file然后用要求的format输出。给的代码里input是个List<String>。input是严格遵循给的例子的format没有提行。除了等号两边有空格外其他不会有空格出现。可能会出现多重继承的关系。
The output should be in sorted order - both sections and the keys within them should be sorted.
输入：
[base_server]
ram = 16G
disk = 15G
[review_console:admin_console]
[admin_console:base_server]
ram = 32g
owner = root
输出：
[admin_console]
disk = 15G
owner = root
ram = 32G
[base_server]
disk = 16G
ram = 16G
[review_console]
disk = 15G
owner = root
ram = 32G
从其他几个testcase里看到还有envname这个属性。第一反应是用regular expression去处理字符串然后用map去存，还自定义了一个新的类。然而后面发现我想的太简单。我自己的代码太菜了，希望有大神能给点hint这道题怎么写比较好。

 */


public class Server6 {

    private class Properties {

    }

    public Map<String, Properties> sortServer(List<String> input) {
        //store id and its out-degree
        Map<String, Integer> outDegree = new TreeMap<String, Integer>();


        return new TreeMap<>();
    }
}
