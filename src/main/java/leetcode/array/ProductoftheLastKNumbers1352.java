package leetcode.array;

import java.util.ArrayList;
import java.util.List;

public class ProductoftheLastKNumbers1352 {

    private List<Integer> preProduct;

    public ProductoftheLastKNumbers1352() {
        preProduct = new ArrayList<>();
        preProduct.add(1);
    }

    public void add(int x) {
        if (x != 0) {
            preProduct.add(preProduct.get(preProduct.size() - 1) * x);
        } else {
            preProduct.clear();
            preProduct.add(1);
        }
    }

    public int getProduct(int k) {
        int n = preProduct.size();
        return k >= n ? 0 : preProduct.get(n - 1) / preProduct.get(n - k - 1);
    }

}
