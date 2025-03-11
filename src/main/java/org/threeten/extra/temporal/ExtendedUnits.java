package org.threeten.extra.temporal;

import org.threeten.extra.TemporalFields;

import java.time.temporal.ChronoUnit;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalUnit;

public final class ExtendedUnits {
    public static TemporalUnit NANOS = ChronoUnit.NANOS;
    public static TemporalUnit MICROS = ChronoUnit.MICROS;
    public static TemporalUnit MILLIS = ChronoUnit.MILLIS;
    public static TemporalUnit SECONDS = ChronoUnit.SECONDS;
    public static TemporalUnit MINUTES = ChronoUnit.MINUTES;
    public static TemporalUnit HOURS = ChronoUnit.HOURS;
    public static TemporalUnit HALF_DAYS = ChronoUnit.HALF_DAYS;
    public static TemporalUnit DAYS = ChronoUnit.DAYS;
    public static TemporalUnit WEEKS = ChronoUnit.WEEKS;
    public static TemporalUnit MONTHS = ChronoUnit.MONTHS;
    public static TemporalUnit QUARTERS = IsoFields.QUARTER_YEARS;
    public static TemporalUnit HALF_YEARS = TemporalFields.HALF_YEARS;
    public static TemporalUnit YEARS = ChronoUnit.YEARS;
    public static TemporalUnit DECADES = ChronoUnit.DECADES;
    public static TemporalUnit CENTURIES = ChronoUnit.CENTURIES;
    public static TemporalUnit MILLENNIA = ChronoUnit.MILLENNIA;
    public static TemporalUnit ERAS = ChronoUnit.ERAS;
    public static TemporalUnit FOREVER = ChronoUnit.FOREVER;

    private ExtendedUnits() {
        throw new UnsupportedOperationException();
    }
}
