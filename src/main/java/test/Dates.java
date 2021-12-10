package test;

import java.time.YearMonth;

public class Dates {
    public static void main(String[] args) {
        System.out.println(YearMonth.now().atDay(1));
        System.out.println(YearMonth.now().atEndOfMonth());
    }
}
