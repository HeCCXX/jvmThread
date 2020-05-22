package algorithm.application;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @ClassName RedPackageTest
 * @Description 抢红包算法
 * @Author 贺楚翔
 * @Date 2020-05-21 17:23
 * @Version 1.0
 **/
public class RedPackageTest {
    public static List<Integer> divideRedPackage(Integer totalAmount,Integer peopleSum){
        ArrayList<Integer> list = new ArrayList<>();
        Random random = new Random();
        int restAmount = totalAmount;
        int restPeopleSum = peopleSum;
        for (int i = 0; i < peopleSum-1; i++) {
            int amount = random.nextInt(restAmount / restPeopleSum * 2 - 1)+1;
            restAmount -= amount;
            restPeopleSum--;
            list.add(amount);
        }
        list.add(restAmount);
        return list;
    }
    public static void main(String[] args) {
        List<Integer> list = divideRedPackage(1000, 10);
        for (Integer amount : list) {
            System.out.println("抢到金额："+new BigDecimal(amount).divide(new BigDecimal("100")));
        }
    }
}
