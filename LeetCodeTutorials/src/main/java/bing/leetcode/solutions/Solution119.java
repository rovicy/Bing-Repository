package bing.leetcode.solutions;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 */

public class Solution119 {

    public List<Integer> getRow(int rowIndex) {
        List<Integer> source = new ArrayList<>();
        source.add(1);
        if (rowIndex < 0) {
            return null;
        }
        if (rowIndex == 0) {
            return source;
        }
        source.add(1);
        if (rowIndex == 1) {
            return source;
        }
        for (int i = 2; i <= rowIndex; i++) {
            refactorList(source);
        }
        return source;
    }

    private void refactorList(List<Integer> source) {
        for (int i = source.size() - 1; i > 0; i--) {
            source.set(i, source.get(i - 1) + source.get(i));
        }
        source.add(1);
    }

}
