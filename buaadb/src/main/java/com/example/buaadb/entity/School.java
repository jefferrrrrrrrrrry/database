package com.example.buaadb.entity;

import java.io.Serializable;

public class School implements Serializable {
    private Integer scno;

    private String scname;

    private static final long serialVersionUID = 1L;

    public Integer getScno() {
        return scno;
    }

    public void setScno(Integer scno) {
        this.scno = scno;
    }

    public String getScname() {
        return scname;
    }

    public void setScname(String scname) {
        this.scname = scname == null ? null : scname.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", scno=").append(scno);
        sb.append(", scname=").append(scname);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        School other = (School) that;
        return (this.getScno() == null ? other.getScno() == null : this.getScno().equals(other.getScno()))
            && (this.getScname() == null ? other.getScname() == null : this.getScname().equals(other.getScname()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getScno() == null) ? 0 : getScno().hashCode());
        result = prime * result + ((getScname() == null) ? 0 : getScname().hashCode());
        return result;
    }
}