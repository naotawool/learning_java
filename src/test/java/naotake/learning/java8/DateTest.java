package naotake.learning.java8;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.Temporal;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;

/**
 * Java8で新たに追加された日付処理の API に対するテストクラス。
 * 
 * @author naotake
 */
public class DateTest {

    @Test
    public void 現在日を取得できること() {
        // 現在日
        Temporal now = LocalDate.now();
        System.out.println(now); // 表示するだけ

        // 現在時間
        now = LocalTime.now();
        System.out.println(now); // 表示するだけ

        // 現在日時
        now = LocalDateTime.now();
        System.out.println(now); // 表示するだけ
    }

    @Test
    public void 指定した日時を取得できること() {
        Temporal now = LocalDate.of(2014, 4, 20);
        assertThat(now.toString(), is("2014-04-20"));

        now = LocalTime.of(21, 46, 9);
        assertThat(now.toString(), is("21:46:09"));

        now = LocalDateTime.of(2014, 4, 20, 21, 46, 9);
        assertThat(now.toString(), is("2014-04-20T21:46:09"));
    }

    @Test
    public void 日付の加減算ができること() {
        LocalDateTime now = LocalDateTime.of(2014, 4, 20, 21, 46, 9);
        LocalDateTime bithday = now.plusYears(1).plusMonths(3).minusDays(2);
        assertThat(bithday.toString(), is("2015-07-18T21:46:09"));
    }

    @Test
    public void 日付の比較が行われること() {
        LocalDate date1 = LocalDate.of(2014, 4, 19);
        LocalDate date2 = LocalDate.of(2014, 4, 20);
        LocalDate date3 = LocalDate.of(2014, 4, 19);

        // isEqual
        assertThat(date1.isEqual(date2), is(false));
        assertThat(date1.isEqual(date3), is(true));

        // isBefore
        assertThat(date1.isBefore(date2), is(true));
        assertThat(date1.isBefore(date3), is(false));

        // isAfter
        assertThat(date1.isAfter(date2), is(false));
        assertThat(date2.isAfter(date3), is(true));
    }

    @Test
    public void うるう年の判定が行われること() {
        assertThat(LocalDate.of(2012, 4, 19).isLeapYear(), is(true));
        assertThat(LocalDate.of(2013, 4, 19).isLeapYear(), is(false));
    }

    @Test
    public void 時間の比較が行われること() {
        LocalTime time1 = LocalTime.of(19, 3, 51);
        LocalTime time2 = LocalTime.of(19, 4, 51);
        LocalTime time3 = LocalTime.of(19, 3, 51);

        // equals
        assertThat(time1.equals(time2), is(false));
        assertThat(time1.equals(time3), is(true));

        // isBefore
        assertThat(time1.isBefore(time2), is(true));
        assertThat(time2.isBefore(time3), is(false));

        // isAfter
        assertThat(time1.isAfter(time2), is(false));
        assertThat(time2.isAfter(time3), is(true));
    }

    @Test
    public void 旧DateクラスからLocalDateTimeへ変換が行われること() {
        Date oldDate = createDate(2014, 4, 20, 21, 46, 9);
        Instant instant = oldDate.toInstant();
        LocalDateTime date = LocalDateTime.ofInstant(instant, ZoneId.of("Asia/Tokyo"));

        // 検証用に変換(--> Calender)
        Calendar oldCal = DateUtils.toCalendar(oldDate);
        TimeZone asiaZone = TimeZone.getTimeZone("Asia/Tokyo");
        oldCal.setTimeZone(asiaZone);
        assertThat(date.getYear(), is(oldCal.get(Calendar.YEAR)));
        assertThat(date.getMonthValue(), is(oldCal.get(Calendar.MONTH) + 1));
        assertThat(date.getDayOfMonth(), is(oldCal.get(Calendar.DAY_OF_MONTH)));
        assertThat(date.getHour(), is(oldCal.get(Calendar.HOUR_OF_DAY)));
        assertThat(date.getMinute(), is(oldCal.get(Calendar.MINUTE)));
        assertThat(date.getSecond(), is(oldCal.get(Calendar.SECOND)));
    }

    @Test
    public void LocalDateTimeから旧Dateクラスへ変換が行われること() {
        LocalDateTime dateTime = LocalDateTime.of(2014, 4, 20, 21, 46, 9);
        ZonedDateTime date = ZonedDateTime.of(dateTime, ZoneId.of("Asia/Tokyo"));
        Instant instant = Instant.from(date);
        Date oldDate = Date.from(instant);

        // 検証用に変換(--> Calender)
        Calendar oldCal = DateUtils.toCalendar(oldDate);
        TimeZone asiaZone = TimeZone.getTimeZone("Asia/Tokyo");
        oldCal.setTimeZone(asiaZone);
        assertThat(oldCal.get(Calendar.YEAR), is(dateTime.getYear()));
        assertThat(oldCal.get(Calendar.MONTH) + 1, is(dateTime.getMonthValue()));
        assertThat(oldCal.get(Calendar.DAY_OF_MONTH), is(dateTime.getDayOfMonth()));
        assertThat(oldCal.get(Calendar.HOUR_OF_DAY), is(dateTime.getHour()));
        assertThat(oldCal.get(Calendar.MINUTE), is(dateTime.getMinute()));
        assertThat(oldCal.get(Calendar.SECOND), is(dateTime.getSecond()));
    }

    private static Date createDate(int year, int month, int day, int hour, int minute, int second) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, second);
        return cal.getTime();
    }
}
