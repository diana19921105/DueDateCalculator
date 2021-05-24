package hu.dianaszanto.solution;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class DueDateCalculator {

  public LocalDateTime calculateDueDate(LocalDateTime submit, Integer turnaroundTime) {
    final List<DayOfWeek> workingDays = Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
        DayOfWeek.THURSDAY, DayOfWeek.FRIDAY);
    final int workingHoursFrom = 9;
    final int workingHoursTo = 17;

    if (submit.getHour() < workingHoursFrom || submit.getHour() > workingHoursTo ||
        !workingDays.contains(submit.getDayOfWeek())) {
      throw new IllegalArgumentException();
    }

    LocalDateTime result = submit;
    Duration betweenStartAndEnd = Duration.between(submit.toLocalTime(), submit.withHour(workingHoursTo).withMinute(0));
    int leftTurnaroundHoursInMinutes = turnaroundTime * 60;

    while (leftTurnaroundHoursInMinutes > 0) {
      boolean notContains = !workingDays.contains(result.getDayOfWeek());
      if (notContains) {
        result = result.plusDays(1).withHour(workingHoursFrom).withMinute(0);
      }

      if (betweenStartAndEnd.getSeconds() / 60 > leftTurnaroundHoursInMinutes) {
        return result.plusMinutes(leftTurnaroundHoursInMinutes);
      }

      leftTurnaroundHoursInMinutes =
          Math.toIntExact(leftTurnaroundHoursInMinutes - (betweenStartAndEnd.getSeconds() / 60));
      betweenStartAndEnd = Duration
          .between(submit.withHour(workingHoursFrom).withMinute(0), submit.withHour(workingHoursTo).withMinute(0));
      result = result.plusDays(1).withHour(workingHoursFrom).withMinute(0);
    }
    return null;
  }
}
