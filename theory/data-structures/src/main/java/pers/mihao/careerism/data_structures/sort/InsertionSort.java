package pers.mihao.careerism.data_structures.sort;

/**
 * 插入排序
 */
public class InsertionSort extends Sort {


    @Override
    public void sort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            // 记住当前在插入的值 和前一个比较
            int j = i - 1, insert = nums[i];
            while (j >= 0 && insert < nums[j]) {
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = insert;
        }

    }


    public void sort20211215(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int insert = nums[i], j = i - 1;
            while (j >= 0 && insert  < nums[j]) {
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = insert;
        }
    }
}
