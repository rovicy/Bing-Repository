package bing.leetcode.solutions;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: Given a non-negative integer numRows,
 * generate the first numRows of Pascal's triangle.
 */

public class Solution118 {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> sourceList = new ArrayList<>();
        if (numRows == 0) return sourceList;

        List<Integer> listOne = new ArrayList<>();
        listOne.add(1);
        sourceList.add(listOne);
        if (numRows == 1) return sourceList;

        List<Integer> listTwo = new ArrayList<>();
        listTwo.add(1);
        listTwo.add(1);
        sourceList.add(listTwo);
        if (numRows == 2) return sourceList;

        for (int i = 3; i <= numRows; i++) {
            List<Integer> currentList = new ArrayList<>();
            currentList.add(1);
            List<Integer> lastList = sourceList.get(i - 2);
            int size = lastList.size();
            for (int j = 0; j < size - 1; j++) {
                currentList.add(lastList.get(j) + lastList.get(j + 1));
            }
            currentList.add(1);
            sourceList.add(currentList);
        }
        return sourceList;
    }
}
