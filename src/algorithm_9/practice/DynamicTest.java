package algorithm_9.practice;

/**
 * @ClassName DynamicTest
 * @Description 动态规划算法
 * 有五座金矿
 * @Author 贺楚翔
 * @Date 2020-05-20 14:48
 * @Version 1.0
 **/
public class DynamicTest {

    /**
    * 利用二维数组记录数据
    * @param w 工人人数
    * @param p 金矿所需人数
    * @param g 金矿价值
    * @return int
    * @exception       
    **/
    public static int getMaxWorth(int w,int[] p,int[] g){
        int[][] tables = new int[g.length+1][w+1];
        for (int i = 1; i <= g.length; i++) {
            for (int j = 1; j <= w; j++) {
                if (j<p[i-1]){
                    tables[i][j] = tables[i-1][j];
                }else {
                    tables[i][j] = Math.max(tables[i-1][j],tables[i-1][j-p[i-1]] + g[i-1]);
                }
            }
        }
        return tables[g.length][w];
    }
    /**
     * 空间优化，利用一维数组记录
     * @param w 工人人数
     * @param p 金矿所需人数
     * @param g 金矿价值
     * @return int
     * @exception
     **/
    public static int getMaxWorthBetter(int w,int[] p,int[] g){
        int[] table = new int[w+1];
        for (int i = 1; i <= g.length; i++) {
            for (int j = w; j > 0; j--) {
                if (j >= p[i-1]){
                    table[j] = Math.max(table[j],table[j-p[i-1]]+g[i-1]);
                }
            }
        }
        return table[w];
    }

    public static void main(String[] args) {
        int w = 10;
        int[] p = {5,5,3,4,3};
        int[] g = {400,500,200,300,350};
        System.out.println("最优解："+getMaxWorth(w,p,g));
        System.out.println("优化后的最优解："+getMaxWorthBetter(w,p,g));
    }
}
