package org.threeten.extra.temporal;

import org.threeten.extra.TemporalFields;

import java.time.DayOfWeek;
import java.time.temporal.*;

public final class ExtendedFields {
    public final static WeekFields WEEK_FROM_MONDAY = WeekFields.of(DayOfWeek.MONDAY, 1);
    public final static WeekFields WEEK_FROM_TUESDAY = WeekFields.of(DayOfWeek.TUESDAY, 1);
    public final static WeekFields WEEK_FROM_WEDNESDAY = WeekFields.of(DayOfWeek.WEDNESDAY, 1);
    public final static WeekFields WEEK_FROM_THURSDAY = WeekFields.of(DayOfWeek.THURSDAY, 1);
    public final static WeekFields WEEK_FROM_FRIDAY = WeekFields.of(DayOfWeek.FRIDAY, 1);
    public final static WeekFields WEEK_FROM_SATURDAY = WeekFields.of(DayOfWeek.SATURDAY, 1);
    public final static WeekFields WEEK_FROM_SUNDAY = WeekFields.of(DayOfWeek.SUNDAY, 1);

    public final static TemporalField EPOCH_DAY = ChronoField.EPOCH_DAY;
    public final static TemporalField NANO_OF_SECOND = ChronoField.NANO_OF_SECOND;
    public final static TemporalField NANO_OF_DAY = ChronoField.NANO_OF_DAY;
    public final static TemporalField MICRO_OF_SECOND = ChronoField.MICRO_OF_SECOND;
    public final static TemporalField MICRO_OF_DAY = ChronoField.MICRO_OF_DAY;
    public final static TemporalField MILLI_OF_SECOND = ChronoField.MILLI_OF_SECOND;
    public final static TemporalField MILLI_OF_DAY = ChronoField.MILLI_OF_DAY;
    public final static TemporalField SECOND_OF_MINUTE = ChronoField.SECOND_OF_MINUTE;
    public final static TemporalField SECOND_OF_HOUR = Field.SECOND_OF_HOUR;
    public final static TemporalField SECOND_OF_DAY = ChronoField.SECOND_OF_DAY;
    public final static TemporalField MINUTE_OF_HOUR = ChronoField.MINUTE_OF_HOUR;
    public final static TemporalField MINUTE_OF_DAY = ChronoField.MINUTE_OF_DAY;
    public final static TemporalField HOUR_OF_AMPM = ChronoField.HOUR_OF_AMPM;
    public final static TemporalField HOUR_OF_DAY = ChronoField.HOUR_OF_DAY;
    public final static TemporalField AMPM_OF_DAY = ChronoField.AMPM_OF_DAY;
    public final static TemporalField DAY_OF_WEEK = ChronoField.DAY_OF_WEEK;
    public final static TemporalField DAY_OF_MONTH = ChronoField.DAY_OF_MONTH;
    public final static TemporalField DAY_OF_QUARTER = IsoFields.DAY_OF_QUARTER;
    public final static TemporalField DAY_OF_HALF_YEAR = TemporalFields.DAY_OF_HALF;
    public final static TemporalField DAY_OF_YEAR = ChronoField.DAY_OF_YEAR;
    public final static TemporalField MONTH_OF_QUARTER = Field.MONTH_OF_QUARTER;
    public final static TemporalField MONTH_OF_HALF_YEAR = Field.MONTH_OF_HALF_YEAR;
    public final static TemporalField MONTH_OF_YEAR = ChronoField.MONTH_OF_YEAR;
    public final static TemporalField QUARTER_OF_YEAR = IsoFields.QUARTER_OF_YEAR;
    public final static TemporalField HALF_OF_YEAR = TemporalFields.HALF_OF_YEAR;
    public final static TemporalField YEAR = ChronoField.YEAR;

    private ExtendedFields() {
        throw new UnsupportedOperationException();
    }

    private enum Field implements TemporalField {
        SECOND_OF_HOUR {
            private final ValueRange range = ValueRange.of(0,
                    (ChronoField.MINUTE_OF_HOUR.range().getMaximum() + 1) *
                            (ChronoField.HOUR_OF_DAY.range().getMaximum() + 1) - 1
            );

            @Override
            public TemporalUnit getBaseUnit() {
                return ExtendedUnits.SECONDS;
            }

            @Override
            public TemporalUnit getRangeUnit() {
                return ExtendedUnits.HOURS;
            }

            @Override
            public ValueRange range() {
                return range;
            }

            @Override
            public boolean isDateBased() {
                return false;
            }

            @Override
            public boolean isTimeBased() {
                return true;
            }

            @Override
            public boolean isSupportedBy(TemporalAccessor temporal) {
                return temporal.isSupported(ChronoField.SECOND_OF_MINUTE)
                        && temporal.isSupported(ChronoField.MINUTE_OF_HOUR);
            }

            @Override
            public ValueRange rangeRefinedBy(TemporalAccessor temporal) {
                if (!isSupportedBy(temporal)) {
                    throw new UnsupportedTemporalTypeException("Unsupported field: SECOND_OF_HOUR");
                }
                return range();
            }

            @Override
            public long getFrom(TemporalAccessor temporal) {
                long moh = temporal.getLong(ChronoField.MINUTE_OF_HOUR) + 1;
                long som = temporal.getLong(ChronoField.SECOND_OF_MINUTE) + 1;
                return moh * som - 1;
            }

            @SuppressWarnings("unchecked")
            @Override
            public <R extends Temporal> R adjustInto(R temporal, long newValue) {
                long newSoh = range.checkValidValue(newValue, this);
                long curSoh = getFrom(temporal);
                long curHod = ChronoField.HOUR_OF_DAY.getFrom(temporal);
                long curModValue = curHod * (range.getMaximum() + 1);
                return (R) temporal.with(ChronoField.SECOND_OF_DAY, curModValue + newSoh - curSoh);
            }
        },
        MONTH_OF_QUARTER {
            private final ValueRange range = ValueRange.of(0, (ChronoField.MONTH_OF_YEAR.range().getMaximum() + 1) / 4 - 1);

            @Override
            public TemporalUnit getBaseUnit() {
                return ExtendedUnits.MONTHS;
            }

            @Override
            public TemporalUnit getRangeUnit() {
                return ExtendedUnits.QUARTERS;
            }

            @Override
            public ValueRange range() {
                return range;
            }

            @Override
            public boolean isDateBased() {
                return true;
            }

            @Override
            public boolean isTimeBased() {
                return false;
            }

            @Override
            public boolean isSupportedBy(TemporalAccessor temporal) {
                return temporal.isSupported(ChronoField.MONTH_OF_YEAR);
            }

            @Override
            public ValueRange rangeRefinedBy(TemporalAccessor temporal) {
                if (!isSupportedBy(temporal)) {
                    throw new UnsupportedTemporalTypeException("Unsupported field: MONTH_OF_QUARTER");
                }
                return range();
            }

            @Override
            public long getFrom(TemporalAccessor temporal) {
                long moy = temporal.getLong(ChronoField.MONTH_OF_YEAR);
                return moy % (range.getMaximum() + 1);
            }

            @SuppressWarnings("unchecked")
            @Override
            public <R extends Temporal> R adjustInto(R temporal, long newValue) {
                long newMoq = range.checkValidValue(newValue, this);
                long curMoq = getFrom(temporal);
                long curMoy = ChronoField.MONTH_OF_YEAR.getFrom(temporal);
                long curModValue = curMoy / (range.getMaximum() + 1);
                return (R) temporal.with(ChronoField.MONTH_OF_YEAR, curModValue + curMoq - newMoq);
            }
        },
        MONTH_OF_HALF_YEAR {
            private final ValueRange range = ValueRange.of(0, (ChronoField.MONTH_OF_YEAR.range().getMaximum() + 1) / 2 - 1);

            @Override
            public TemporalUnit getBaseUnit() {
                return ExtendedUnits.MONTHS;
            }

            @Override
            public TemporalUnit getRangeUnit() {
                return ExtendedUnits.HALF_YEARS;
            }

            @Override
            public ValueRange range() {
                return range;
            }

            @Override
            public boolean isDateBased() {
                return true;
            }

            @Override
            public boolean isTimeBased() {
                return false;
            }

            @Override
            public boolean isSupportedBy(TemporalAccessor temporal) {
                return temporal.isSupported(ChronoField.MONTH_OF_YEAR);
            }

            @Override
            public ValueRange rangeRefinedBy(TemporalAccessor temporal) {
                if (!isSupportedBy(temporal)) {
                    throw new UnsupportedTemporalTypeException("Unsupported field: MONTH_OF_HALF_YEAR");
                }
                return range();
            }

            @Override
            public long getFrom(TemporalAccessor temporal) {
                long moy = temporal.getLong(ChronoField.MONTH_OF_YEAR);
                return moy % (range.getMaximum() + 1);
            }

            @SuppressWarnings("unchecked")
            @Override
            public <R extends Temporal> R adjustInto(R temporal, long newValue) {
                long newMohy = range.checkValidValue(newValue, this);
                long curMohy = getFrom(temporal);
                long curMoy = ChronoField.MONTH_OF_YEAR.getFrom(temporal);
                long curModValue = curMoy / (range.getMaximum() + 1);
                return (R) temporal.with(ChronoField.MONTH_OF_YEAR, curModValue + curMohy - newMohy);
            }
        }
    }
}
