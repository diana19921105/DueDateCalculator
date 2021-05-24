package hu.dianaszanto.solution;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class DueDateCalculatorTest {

  private DueDateCalculator dueDateCalculator;

  @Before
  public void setUp() {
    dueDateCalculator = new DueDateCalculator();
  }


  @Test
  public void given_calculateDueDate_whenCallTheMethod_thenDueDateIsOnSameDay() {

    LocalDateTime localDateTime = dueDateCalculator.calculateDueDate(LocalDateTime.of(2021, 5, 24, 9, 57),
        7);

    Assert.assertNotNull(localDateTime);
    assertEquals("2021-05-24T16:57", localDateTime.toString());
  }

  @Test
  public void given_calculateDueDate_whenCallTheMethod_thenDueDateIsNextDay() {

    LocalDateTime localDateTime = dueDateCalculator.calculateDueDate(LocalDateTime.of(2021, 5, 24, 9, 57),
        12);

    Assert.assertNotNull(localDateTime);
    assertEquals("2021-05-25T13:57", localDateTime.toString());
  }

  @Test
  public void given_calculateDueDate_whenCallTheMethod_thenDueDateIsOnNextWeek() {

    LocalDateTime localDateTime = dueDateCalculator.calculateDueDate(LocalDateTime.of(2021, 5, 21, 9, 57),
        45);

    Assert.assertNotNull(localDateTime);
    assertEquals("2021-05-27T14:57", localDateTime.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void given_calculateDueDate_whenSubmittedOutOfBusinessHours_thenReturnsIllegalArgumentException() {

    dueDateCalculator.calculateDueDate(LocalDateTime.of(2021, 5, 24, 8, 57),
        7);
  }

  @Test(expected = IllegalArgumentException.class)
  public void given_calculateDueDate_whenSubmittedOnWeekend_thenReturnsIllegalArgumentException() {

    dueDateCalculator.calculateDueDate(LocalDateTime.of(2021, 5, 23, 9, 57),
        7);
  }

}