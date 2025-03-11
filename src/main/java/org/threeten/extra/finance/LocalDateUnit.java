package org.threeten.extra.finance;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;

public enum LocalDateUnit implements TemporalUnit {
    /**
     * Unit that represents the concept of a day.
     * <p>An alias for {@link ChronoUnit#DAYS}
     */
    DAYS(ChronoUnit.DAYS.getDuration()),
    /**
     * Unit that represents the concept of two days.
     * <p>The estimated duration of two days is 2 {@link LocalDateUnit#DAYS}.
     */
    TW0_DAYS(ChronoUnit.DAYS.getDuration().multipliedBy(2)),
    /**
     * Unit that represents the concept of three days.
     * <p>The estimated duration of three days is 3 {@link LocalDateUnit#DAYS}.
     */
    THREE_DAYS(ChronoUnit.DAYS.getDuration().multipliedBy(3)),
    /**
     * Unit that represents the concept of a week.
     * <p>An alias for {@link ChronoUnit#WEEKS}
     */
    WEEKS(ChronoUnit.WEEKS.getDuration()),
    /**
     * Unit that represents the concept of two weeks.
     * <p>The estimated duration of two weeks is 2 {@link LocalDateUnit#WEEKS}.
     */
    TWO_WEEKS(ChronoUnit.WEEKS.getDuration().multipliedBy(2)),
    /**
     * Unit that represents the concept of three weeks.
     * <p>The estimated duration of three weeks is 3 {@link LocalDateUnit#WEEKS}.
     */
    THREE_WEEKS(ChronoUnit.WEEKS.getDuration().multipliedBy(3)),
    /**
     * Unit that represents the concept of a month.
     * <p>An alias for {@link ChronoUnit#MONTHS}
     */
    MONTHS(ChronoUnit.MONTHS.getDuration()),
    /**
     * Unit that represents the concept of two months.
     * <p>The estimated duration of two months is 2 {@link LocalDateUnit#MONTHS}.
     */
    TWO_MONTHS(ChronoUnit.MONTHS.getDuration().multipliedBy(2)),
    /**
     * Unit that represents the concept of three months.
     * <p>The estimated duration of three months is 3 {@link LocalDateUnit#MONTHS}.
     */
    THREE_MONTHS(ChronoUnit.MONTHS.getDuration().multipliedBy(3)),
    /**
     * Unit that represents the concept of four months.
     * <p>The estimated duration of four months is 4 {@link LocalDateUnit#MONTHS}.
     */
    FOUR_MONTHS(ChronoUnit.MONTHS.getDuration().multipliedBy(4)),
    /**
     * Unit that represents the concept of five months.
     * <p>The estimated duration of five months is 5 {@link LocalDateUnit#MONTHS}.
     */
    FIVE_MONTHS(ChronoUnit.MONTHS.getDuration().multipliedBy(5)),
    /**
     * Unit that represents the concept of six months.
     * <p>The estimated duration of six months is 6 {@link LocalDateUnit#MONTHS}.
     */
    SIX_MONTHS(ChronoUnit.MONTHS.getDuration().multipliedBy(6)),
    /**
     * Unit that represents the concept of a quarter.
     * <p>The estimated duration of a quarter is {@link LocalDateUnit#THREE_MONTHS}.
     * <p>An alias for {@link LocalDateUnit#THREE_MONTHS}
     */
    QUARTERS(ChronoUnit.MONTHS.getDuration().multipliedBy(3)),
    /**
     * Unit that represents the concept of two quarters.
     * <p>The estimated duration of two quarter is 2 {@link LocalDateUnit#QUARTERS}.
     * <p>An alias for {@link LocalDateUnit#SIX_MONTHS}
     */
    TWO_QUARTERS(ChronoUnit.MONTHS.getDuration().multipliedBy(6)),
    /**
     * Unit that represents the concept of three quarters.
     * <p>The estimated duration of three quarters is 3 {@link LocalDateUnit#QUARTERS}.
     */
    THREE_QUARTERS(ChronoUnit.MONTHS.getDuration().multipliedBy(9)),
    /**
     * Unit that represents the concept of a half year.
     * <p>The estimated duration of three quarters is 3 {@link LocalDateUnit#QUARTERS}.
     */
    HALF_YEARS(ChronoUnit.MONTHS.getDuration().multipliedBy(6)),
    /**
     * Unit that represents the concept of a year.
     * <p>An alias for {@link ChronoUnit#YEARS}
     */
    YEARS(ChronoUnit.YEARS.getDuration()),
    /**
     * Unit that represents the concept of one and half years.
     * <p>The estimated duration of one and half years is 1.5 {@link LocalDateUnit#YEARS}.
     */
    ONE_AND_HALF_YEARS(ChronoUnit.MONTHS.getDuration().multipliedBy(18)),
    /**
     * Unit that represents the concept of two years.
     * <p>The estimated duration of two years is 2 {@link LocalDateUnit#YEARS}.
     */
    TWO_YEARS(ChronoUnit.YEARS.getDuration().multipliedBy(2)),
    /**
     * Unit that represents the concept of two and half years.
     * <p>The estimated duration of two and half years is 2.5 {@link LocalDateUnit#YEARS}.
     */
    TWO_AND_HALF_YEARS(ChronoUnit.MONTHS.getDuration().multipliedBy(30)),
    /**
     * Unit that represents the concept of three years.
     * <p>The estimated duration of three years is 3 {@link LocalDateUnit#YEARS}.
     */
    THREE_YEARS(ChronoUnit.YEARS.getDuration().multipliedBy(3)),
    /**
     * Unit that represents the concept of four years.
     * <p>The estimated duration of four years is 4 {@link LocalDateUnit#YEARS}.
     */
    FOUR_YEARS(ChronoUnit.YEARS.getDuration().multipliedBy(4)),
    /**
     * Unit that represents the concept of five years.
     * <p>The estimated duration of five years is 5 {@link LocalDateUnit#YEARS}.
     */
    FIVE_YEARS(ChronoUnit.YEARS.getDuration().multipliedBy(5));

    private final Duration duration;

    LocalDateUnit(Duration duration) {
        this.duration = duration;
    }

    @Override
    public Duration getDuration() {
        return duration;
    }

    @Override
    public boolean isDurationEstimated() {
        return true;
    }

    @Override
    public boolean isDateBased() {
        return true;
    }

    @Override
    public boolean isTimeBased() {
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <R extends Temporal> R addTo(R temporal, long amount) {
        return (R) temporal.plus(amount, this);
    }

    @Override
    public long between(Temporal temporal1Inclusive, Temporal temporal2Exclusive) {
        return temporal1Inclusive.until(temporal2Exclusive, this);
    }
}
