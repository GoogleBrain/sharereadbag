import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShareRedBag {
    /**
     * 在此我们假设
     * 红包剩余金额为 M
     * 红包剩余数量为 N
     * 这种算法就是每次都在区间[0,M/N×2] 随机取一个数
     * 假设100元红包发10个人，那么合理的做法应该是每个人领到10元的概率相同。
     * 第一个人随机金额的范围为[0,100/10×2] ,也就是[0,20],这样平均可以领到10元，此时剩余金额为100-10=90。
     * 第二个人随机金额的范围为[0,90/9×2] ,也就是[0,20],这样平均也可以领到10元，此时剩余金额为90-10=80。
     * 第三个人随机金额的范围为[0,80/8×2] ,也就是[0,20],这样平均也可以领到10元。
     * 这样推导下去，每个人领到相同金额的概率应该就是相同的了。
     */

    public static void main(String[] args) {
        System.out.println(doubleMeanMethod(10,2));

    }

    public static List<Double> doubleMeanMethod(double money, int number){
        List<Double> result = new ArrayList<Double>();
        if(money<0&&number<1)
            return null;
        double amount,sum=0;
        int remainingNumber=number;
        int i=1;
        while(remainingNumber>1){
            amount= nextDouble(0.01,2*(money/remainingNumber));
            sum+=amount;
            System.out.println("第"+i+"个人领取的红包金额为："+format(amount));
            money -= amount;
            remainingNumber--;
            result.add(amount);
            i++;
        }
        result.add(money);
        System.out.println("第"+i+"个人领取的红包金额为："+format(money));
        sum+=money;
        System.out.println("验证发出的红包总金额为："+format(sum));

        return result;
    }

    public static double nextDouble(final double min, final double max) {
        return min + ((max - min) * new Random().nextDouble());
    }

    public static String format(double value) {

        return new java.text.DecimalFormat("0.00").format(value); // 保留两位小数
    }
}
