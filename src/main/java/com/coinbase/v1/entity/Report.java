package com.coinbase.v1.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.io.Serializable;
import org.joda.time.DateTime;

public class Report implements Serializable {
    public static final Integer INFINITE = Integer.valueOf(-1);
    private static final long serialVersionUID = -8462599652861673119L;
    private String callbackUrl;
    private DateTime createdAt;
    private String email;
    private String id;
    private DateTime lastRun;
    private DateTime nextRun;
    private String nextRunDate;
    private String nextRunTime;
    private Repeat repeat;
    private StartType startType;
    private Status status;
    private TimeRange timeRange;
    private String timeRangeEnd;
    private DateTime timeRangeEndDateTime;
    private String timeRangeStart;
    private DateTime timeRangeStartDateTime;
    private Integer times;
    private Integer timesRun;
    private Type type;

    public enum Repeat {
        NEVER("never"),
        DAILY("daily"),
        WEEKLY("weekly"),
        BIWEEKLY("every_two_weeks"),
        MONTHLY("monthly"),
        QUARTERLY("quarterly"),
        YEARLY("yearly");
        
        private String _value;

        private Repeat(String value) {
            this._value = value;
        }

        @JsonValue
        public String toString() {
            return this._value;
        }

        @JsonCreator
        public static Repeat create(String val) {
            for (Repeat repeat : values()) {
                if (repeat.toString().equalsIgnoreCase(val)) {
                    return repeat;
                }
            }
            return null;
        }
    }

    public enum StartType {
        ON("on"),
        NOW("now");
        
        private String _value;

        private StartType(String value) {
            this._value = value;
        }

        @JsonValue
        public String toString() {
            return this._value;
        }

        @JsonCreator
        public static StartType create(String val) {
            for (StartType type : values()) {
                if (type.toString().equalsIgnoreCase(val)) {
                    return type;
                }
            }
            return null;
        }
    }

    public enum Status {
        ACTIVE("active"),
        COMPLETED("completed");
        
        private String _value;

        private Status(String value) {
            this._value = value;
        }

        @JsonValue
        public String toString() {
            return this._value;
        }

        @JsonCreator
        public static Status create(String val) {
            for (Status status : values()) {
                if (status.toString().equalsIgnoreCase(val)) {
                    return status;
                }
            }
            return null;
        }
    }

    public enum TimeRange {
        TODAY("today"),
        YESTERDAY("yesterday"),
        PAST_SEVEN("past_7"),
        PAST_THIRTY("past_30"),
        MONTH_TO_DATE("month_to_date"),
        LAST_FULL_MONTH("last_full_month"),
        YEAR_TO_DATE("year_to_date"),
        LAST_FULL_YEAR("last_full_year"),
        ALL("all"),
        CUSTOM("custom");
        
        private String _value;

        private TimeRange(String value) {
            this._value = value;
        }

        @JsonValue
        public String toString() {
            return this._value;
        }

        @JsonCreator
        public static TimeRange create(String val) {
            for (TimeRange timerange : values()) {
                if (timerange.toString().equalsIgnoreCase(val)) {
                    return timerange;
                }
            }
            return null;
        }
    }

    public enum Type {
        TRANSACTIONS("transactions"),
        ORDERS("orders"),
        TRANSFERS("transfers");
        
        private String _value;

        private Type(String value) {
            this._value = value;
        }

        @JsonValue
        public String toString() {
            return this._value;
        }

        @JsonCreator
        public static Type create(String val) {
            for (Type type : values()) {
                if (type.toString().equalsIgnoreCase(val)) {
                    return type;
                }
            }
            return null;
        }
    }

    public DateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(DateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTimeRangeStart() {
        return this.timeRangeStart;
    }

    public void setTimeRangeStart(DateTime timeRangeStart) {
        this.timeRangeStartDateTime = timeRangeStart;
        this.timeRangeStart = timeRangeStart.toString("MM/dd/YYYY");
    }

    public String getTimeRangeEnd() {
        return this.timeRangeEnd;
    }

    public void setTimeRangeEnd(DateTime timeRangeEnd) {
        this.timeRangeEndDateTime = timeRangeEnd;
        this.timeRangeEnd = timeRangeEnd.toString("MM/dd/YYYY");
    }

    public DateTime getLastRun() {
        return this.lastRun;
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCallbackUrl() {
        return this.callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public TimeRange getTimeRange() {
        return this.timeRange;
    }

    public void setTimeRange(TimeRange timeRange) {
        this.timeRange = timeRange;
    }

    public StartType getStartType() {
        return this.startType;
    }

    public void setStartType(StartType startType) {
        this.startType = startType;
    }

    public Repeat getRepeat() {
        return this.repeat;
    }

    public void setRepeat(Repeat repeat) {
        this.repeat = repeat;
    }

    public Integer getTimes() {
        return this.times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public static Integer getInfinite() {
        return INFINITE;
    }

    public String getNextRunDate() {
        return this.nextRunDate;
    }

    public String getNextRunTime() {
        return this.nextRunTime;
    }

    public void setNextRun(DateTime time) {
        this.nextRun = time;
        this.nextRunDate = time.toString("MM/dd/YYYY");
        this.nextRunTime = time.toString("HH:mm");
    }

    public void setLastRun(DateTime time) {
        this.lastRun = time;
    }

    public DateTime getNextRun() {
        return this.nextRun;
    }

    public Integer getTimesRun() {
        return this.timesRun;
    }

    public void setTimesRun(Integer timesRun) {
        this.timesRun = timesRun;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
