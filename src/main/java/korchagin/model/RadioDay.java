package korchagin.model;

import java.sql.Date;

public class RadioDay {
    private Integer countProgram;
    private Date date;

    public RadioDay(Integer countProgram, Date date) {
        this.date = date;
        setCountProgram(countProgram);
    }

    public Integer getCountProgram() {
        return countProgram;
    }

    public void setCountProgram(Integer countProgram) {
        if (countProgram >= 7 && countProgram <= 12) {
            this.countProgram = countProgram;
        } else {
            throw new IllegalArgumentException("value out of range");
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
