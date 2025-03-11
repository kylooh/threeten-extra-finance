package org.threeten.extra.finance;

import org.threeten.extra.LocalDateRange;

import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalUnit;
import java.util.Objects;

public abstract class SinceToDate implements TemporalAdjuster {

    public static SinceToDate of(TemporalAdjuster adjuster) {
        return new Default(adjuster);
    }

    public abstract TemporalAdjuster getAdjuster();

    public abstract long get(TemporalUnit unit, LocalDate endIncluded);

    public abstract LocalDateRange range(LocalDate endIncluded);

    public abstract LocalDate since(LocalDate endIncluded);

    @Override
    public Temporal adjustInto(Temporal temporal) {
        return getAdjuster().adjustInto(temporal);
    }

    private static final class Default extends SinceToDate {
        private final TemporalAdjuster adjuster;

        public Default(TemporalAdjuster adjuster) {
            this.adjuster = adjuster;
        }

        @Override
        public TemporalAdjuster getAdjuster() {
            return adjuster;
        }

        @Override
        public long get(TemporalUnit unit, LocalDate endIncluded) {
            return unit.between(since(endIncluded), endIncluded);
        }

        @Override
        public LocalDateRange range(LocalDate endIncluded) {
            return LocalDateRange.of(since(endIncluded), endIncluded);
        }

        @Override
        public LocalDate since(LocalDate endIncluded) {
            return endIncluded.with(adjuster);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Default aDefault = (Default) o;
            return Objects.equals(adjuster, aDefault.adjuster);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(adjuster);
        }
    }
}
