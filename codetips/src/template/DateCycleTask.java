package template;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateCycleTask {

  public static void main(String[] args) {
    // mock 2024-10-01 to 2024-10-11，左闭右开，所以入参是 2024-10-01 和 2024-10-12
    process(
        LocalDate.parse("2024-10-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
        LocalDate.parse("2024-10-12", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
  }

  /**
   * 左闭右开区间（方法实现的约定俗成）
   *
   * @param start
   * @param end
   */
  public static void process(LocalDate start, LocalDate end) {
    while (start.isBefore(end)) {
      System.out.println(
          "Already done: " + start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

      start = start.plusDays(1);
    }
  }
}
