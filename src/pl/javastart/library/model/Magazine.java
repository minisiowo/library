package pl.javastart.library.model;

import java.time.MonthDay;
import java.util.Objects;

public class Magazine extends Publication {
    public static final String TYPE = "Magazine";

    private MonthDay monthDay;
    private String language;

    public Magazine(String title, String publisher, String language, int year, int month, int day) {
        super(year, publisher, title);
        this.monthDay = MonthDay.of(month, day);
        this.language = language;
    }

    public MonthDay getMonthDay() {
        return monthDay;
    }

    public void setMonthDay(MonthDay monthDay) {
        this.monthDay = monthDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Magazine magazine = (Magazine) o;
        return Objects.equals(monthDay, magazine.monthDay) && Objects.equals(language, magazine.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), monthDay, language);
    }

    @Override
    public String toString() {
        return super.toString() + "/" + monthDay.getMonthValue() + "/" + monthDay.getDayOfMonth() + "; " + language;
    }

    @Override
    public String toCsv() {
        return String.format("%s;%s;%s;%d;%d;%d;%s",
                TYPE, getTitle(), getPublisher(), getYear().getValue(), monthDay.getMonthValue(), monthDay.getDayOfMonth(), language);
    }
}
