package AlgorithmForJava;

public class TomonhikoSakamoto_Algorithm {
    public static  int dayOfWeek(int year, int month, int day) {
        // 月份调整表
        int[] t = {0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4};

        // 如果月份是1月或2月，则视为前一年的13月或14月
        if (month < 3) {
            year--;
        }

        // 核心计算公式
        return (year + year/4 - year/100 + year/400 + t[month-1] + day) % 7;
    }

    public static void main(String[] args) {
        // 测试示例
        int year = 2025;
        int month = 4;
        int day = 14;

        int dow = dayOfWeek(year, month, day);

        // 输出星期几
        String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        System.out.printf("%d-%02d-%02d is a %s\n", year, month, day, days[dow]);
    }
}
