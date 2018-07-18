package com.coinbase.v1.entity;

import com.coinbase.android.splittesting.SplitTestConstants;
import com.coinbase.v1.entity.Button.Repeat;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.io.Serializable;
import org.joda.money.Money;
import org.joda.time.DateTime;

public class RecurringPayment implements Serializable {
    public static final Integer INDEFINITE = Integer.valueOf(-1);
    private static final long serialVersionUID = 7769736829519464743L;
    private Money _amount;
    private Button _button;
    private DateTime _createdAt;
    private String _custom;
    private String _description;
    private String _from;
    private String _id;
    private DateTime _lastRun;
    private DateTime _nextRun;
    private String _notes;
    private Repeat _repeat;
    private StartType _startType;
    private Status _status;
    private Integer _times;
    private Integer _timesRun;
    private String _to;

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
        NEW(SplitTestConstants.LINK_BANK_ACCOUNT_CHANGES_ENABLED),
        ACTIVE("active"),
        PAUSED("paused"),
        CANCELED("canceled"),
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

    public String getTo() {
        return this._to;
    }

    public void setTo(String to) {
        this._to = to;
    }

    public String getFrom() {
        return this._from;
    }

    public void setFrom(String from) {
        this._from = from;
    }

    public Integer getTimesRun() {
        return this._timesRun;
    }

    public void setTimesRun(Integer timesRun) {
        this._timesRun = timesRun;
    }

    public Integer getTimes() {
        return this._times;
    }

    public void setTimes(Integer times) {
        this._times = times;
    }

    public Repeat getRepeat() {
        return this._repeat;
    }

    public void setRepeat(Repeat repeat) {
        this._repeat = repeat;
    }

    public DateTime getLastRun() {
        return this._lastRun;
    }

    public void setLastRun(DateTime lastRun) {
        this._lastRun = lastRun;
    }

    public DateTime getNextRun() {
        return this._nextRun;
    }

    public void setNextRun(DateTime nextRun) {
        this._nextRun = nextRun;
    }

    public String getNotes() {
        return this._notes;
    }

    public void setNotes(String notes) {
        this._notes = notes;
    }

    public String getDescription() {
        return this._description;
    }

    public void setDescription(String description) {
        this._description = description;
    }

    public Money getAmount() {
        return this._amount;
    }

    public void setAmount(Money amount) {
        this._amount = amount;
    }

    public String getId() {
        return this._id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public DateTime getCreatedAt() {
        return this._createdAt;
    }

    public void setCreatedAt(DateTime createdAt) {
        this._createdAt = createdAt;
    }

    public Status getStatus() {
        return this._status;
    }

    public void setStatus(Status status) {
        this._status = status;
    }

    public String getCustom() {
        return this._custom;
    }

    public void setCustom(String custom) {
        this._custom = custom;
    }

    public Button getButton() {
        return this._button;
    }

    public void setButton(Button button) {
        this._button = button;
    }

    public StartType getStartType() {
        return this._startType;
    }

    public void setStartType(StartType startType) {
        this._startType = startType;
    }
}
