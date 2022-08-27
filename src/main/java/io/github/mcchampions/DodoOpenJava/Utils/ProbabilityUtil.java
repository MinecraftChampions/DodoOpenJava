package io.github.mcchampions.DodoOpenJava.Utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 关于 概率 的一些实用方法
 */
public class ProbabilityUtil {
    private final Random random = new Random();
    private final List<Integer> probabilityNumList = new ArrayList<>();

    private ProbabilityUtil() {
    }

    private static class SingletonHolder {
        private static final ProbabilityUtil INSTANCE = new ProbabilityUtil();
    }

    public static ProbabilityUtil getInstance() {
        return ProbabilityUtil.SingletonHolder.INSTANCE;
    }

    /**
     * 不加锁进行概率抽取
     *
     * @param num 概率数字
     * @return true 抽到
     */
    public boolean pickIndex(double num) {
        if (num == 0) {
            return false;
        }
        BigDecimal rate = BigDecimal.ONE.divide(new BigDecimal(num + ""), RoundingMode.UP);
        return pickIndex(1, rate.intValue());
    }

    /**
     * 不加锁进行概率抽取
     *
     * @param num    概率数字
     * @param maxNum 总概率
     * @return true 抽到
     */
    public boolean pickIndex(int num, int maxNum) {
        if (num >= maxNum) {
            num = maxNum;
        }
        int[] nums = {num, maxNum - num};
        return randomIndex(nums) == 0;
    }

    /**
     * 加锁进行概率抽取
     *
     * @param num 概率数字
     * @return true 抽到
     */
    public synchronized boolean pickSyncIndex(double num) {
        return pickIndex(num);
    }

    /**
     * 加锁进行概率抽取
     *
     * @param num    概率数字
     * @param maxNum 总概率
     * @return true 抽到
     */
    public synchronized boolean pickSyncIndex(int num, int maxNum) {
        return pickIndex(num, maxNum);
    }

    /**
     * 进行二分查询
     *
     * @param nums 数组
     * @return 返回0为匹配到了数组第一个数
     */
    private int randomIndex(int[] nums) {
        probabilityNumList.clear();
        int tot = 0;
        for (int num : nums) {
            tot += num;
            probabilityNumList.add(tot);
        }
        int randomNum = random.nextInt(tot);
        int hi = probabilityNumList.size() - 1;
        int lo = 0;
        while (lo != hi) {
            int mid = (lo + hi) / 2;
            if (randomNum >= probabilityNumList.get(mid)) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }
}
