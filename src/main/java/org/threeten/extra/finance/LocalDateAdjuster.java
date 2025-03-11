package org.threeten.extra.finance;

import org.threeten.extra.DayOfMonth;
import org.threeten.extra.DayOfYear;
import org.threeten.extra.Quarter;

import java.time.Month;
import java.time.Year;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

public enum LocalDateAdjuster implements TemporalAdjuster {
    FIRST_DAY_OF_MONTH(DayOfMonth.of(1)),
    LAST_DAY_OF_MONTH(new TemporalAdjuster() {
        private final DayOfMonth dayOfMonth = DayOfMonth.of(31);

        @Override
        public Temporal adjustInto(Temporal temporal) {
            return dayOfMonth.atMonth(Month.from(temporal)).adjustInto(temporal);
        }
    }),
    FIRST_DAY_OF_QUARTER(new TemporalAdjuster() {
        private final DayOfMonth dayOfMonth = DayOfMonth.of(1);

        @Override
        public Temporal adjustInto(Temporal temporal) {
            return dayOfMonth.atMonth(Quarter.from(temporal).firstMonth()).adjustInto(temporal);
        }
    }),
    LAST_DAY_OF_QUARTER(new TemporalAdjuster() {
        private final DayOfMonth dayOfMonth = DayOfMonth.of(31);

        @Override
        public Temporal adjustInto(Temporal temporal) {
            return dayOfMonth.atMonth(Quarter.from(temporal).firstMonth().plus(2)).adjustInto(temporal);
        }
    }),
    FIRST_DAY_OF_YEAR(DayOfYear.of(1)),
    LAST_DAY_OF_YEAR(new TemporalAdjuster() {
        private final DayOfYear dayOfYear = DayOfYear.of(365);
        private final DayOfYear dayOfLeapYear = DayOfYear.of(366);

        @Override
        public Temporal adjustInto(Temporal temporal) {
            Year year = Year.from(temporal);
            return dayOfYear.isValidYear(year.getValue())
                    ? dayOfYear.atYear(year)
                    : dayOfLeapYear.atYear(year);
        }
    });

    private final TemporalAdjuster adjuster;

    LocalDateAdjuster(TemporalAdjuster adjuster) {
        this.adjuster = adjuster;
    }

    @Override
    public Temporal adjustInto(Temporal temporal) {
        return adjuster.adjustInto(temporal);
    }
}
