package com.example.arada.db;

public class RecordVO {
    Integer _no;
    String _date;
    String record;

    public Integer get_no() {
        return _no;
    }

    public void set_no(Integer _no) {
        this._no = _no;
    }

    public String get_date() {
        return _date;
    }

    public void set_date(String _date) {
        this._date = _date;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }
}
