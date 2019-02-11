package Amazon;

import java.util.*;

public class MaxComponentSpeed {
    public class ComponentNode {
        int value;
        ArrayList<ComponentNode> components;
        ComponentNode() {
            components = new ArrayList<>();
        }
        ComponentNode(int x) {
            value = x;
            this.components = new ArrayList<>();
        }
    }

    private double max = 0;
    private ComponentNode finalNode = null;
    public ComponentNode findMaxAve(ComponentNode root) {
        dfs(root);
        return finalNode;
    }
    private double[] dfs (ComponentNode root) {
        if (root == null) return new double[] {0.0 , 0.0};

        double sum = 0.0;
        double cnt = 1.0;
        for (ComponentNode cn : root.components) {
            double[] tem = dfs(cn);
            sum += tem[0];
            cnt += tem[1];
        }

        if (sum / cnt > max) {
            max = sum / cnt;
            finalNode = root;
        }

        return new double[] {sum , cnt};
    }
}
