package pers.mihao.careerism.data_structures.tanxin;

import java.util.Arrays;

/**
 * 452. 用最少数量的箭引爆气球
 * 在二维空间中有许多球形的气球。对于每个气球，提供的输入是水平方向上，气球直径的开始和结束坐标。由于它是水平的，所以纵坐标并不重要，因此只要知道开始和结束的横坐标就足够了。开始坐标总是小于结束坐标。
 *
 * 一支弓箭可以沿着 x 轴从不同点完全垂直地射出。在坐标 x 处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足  xstart ≤ x ≤ xend，则该气球会被引爆。可以射出的弓箭的数量没有限制。 弓箭一旦被射出之后，可以无限地前进。我们想找到使得所有气球全部被引爆，所需的弓箭的最小数量。
 *
 * 给你一个数组 points ，其中 points [i] = [xstart,xend] ，返回引爆所有气球所必须射出的最小弓箭数。
 *
 *
 * 示例 1：
 *
 * 输入：points = [[10,16],[2,8],[1,6],[7,12]]
 * 输出：2
 * 解释：对于该样例，x = 6 可以射爆 [2,8],[1,6] 两个气球，以及 x = 11 射爆另外两个气球
 * 示例 2：
 *
 * 输入：points = [[1,2],[3,4],[5,6],[7,8]]
 * 输出：4
 * 示例 3：
 *
 * 输入：points = [[1,2],[2,3],[3,4],[4,5]]
 * 输出：2
 * 示例 4：
 *
 * 输入：points = [[1,2]]
 * 输出：1
 * 示例 5：
 *
 * 输入：points = [[2,3],[2,3]]
 * 输出：1
 * @Author mihao
 * @Date 2020/11/23 9:39
 */
public class Code452用最少数量的箭引爆气球 {

    public static void main(String[] args) {
        System.out.println(new Code452用最少数量的箭引爆气球().findMinArrowShots(
            new int[][]{{3,9},{7,12},{3,8},{6,8},{9,10},{2,9},{0,9},{3,9},{0,6},{2,8}}
        ));

    }


    /**
     * 贪心
     * @param points
     * @return
     */
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) {
            return 0;
        }
        // 1.根据起点排序
        Arrays.sort(points, (p1,p2)->{
            if (p1[0] != p2[0]) {
                return Integer.compare(p1[0], p2[0]);
            }else {
                return Integer.compare(p1[1], p2[1]);
            }
        });
        // 2.每一个都尽量靠后射
        int lastShoot = points[0][1], shootNum = 1, startPoint;
        for (int i = 1; i < points.length; i++) {
            startPoint = points[i][0];
            if (startPoint > lastShoot) {
                shootNum ++;
                lastShoot = points[i][1];
            }else if (startPoint < lastShoot){
                // 如果这个气球太小 就射里面的小的
                if (points[i][1] < lastShoot) {
                    lastShoot = points[i][1];
                }
            }
        }
        return shootNum;
    }



}
