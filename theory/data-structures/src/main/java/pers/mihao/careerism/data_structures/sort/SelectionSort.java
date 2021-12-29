package pers.mihao.careerism.data_structures.sort;

public class SelectionSort extends Sort {

    /**
     * 选择排序一个一个的选择最小值/最大值 放到头部
     *
     * @param nums
     */
    @Override
    public void sort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            // 假定初始值最小
            int minIndex = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[minIndex]) minIndex = j;
            }
            swap(nums, minIndex, i);
        }
    }

    private void mySort(int[] nums) {
        int minIndex;
        for (int i = 0; i < nums.length; i++) {
            minIndex = i;
            for (int j = i; j < nums.length; j++) {
                if (nums[i] < nums[minIndex]) minIndex = i;
            }
            swap(nums, i, minIndex);
        }
    }

    /**
     * 初始交换值错误
     * @param nums
     */
    public void sort20211214(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < nums.length; j++) {
                min = nums[min] > nums[j] ? j : min;
            }
            swap(nums, min, i);
        }
    }
}
