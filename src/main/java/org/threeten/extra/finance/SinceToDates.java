package org.threeten.extra.finance;

import java.time.DayOfWeek;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

public enum SinceToDates implements TemporalAdjuster {
    YTD(LocalDateAdjuster.FIRST_DAY_OF_YEAR),
    QTD(LocalDateAdjuster.FIRST_DAY_OF_QUARTER),
    MTD(LocalDateAdjuster.FIRST_DAY_OF_MONTH),
    WTD(DayOfWeek.MONDAY);

    private final SinceToDate sinceToDate;

    SinceToDates(TemporalAdjuster adjuster) {
        this.sinceToDate = SinceToDate.of(adjuster);
    }

    public SinceToDate getSinceToDate() {
        return sinceToDate;
    }

    @Override
    public Temporal adjustInto(Temporal temporal) {
        return sinceToDate.getAdjuster().adjustInto(temporal);
    }
}