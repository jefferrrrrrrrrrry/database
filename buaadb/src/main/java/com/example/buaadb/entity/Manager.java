package com.example.buaadb.entity;

import java.io.Serializable;

public class Manager implements Serializable {
    private Integer mno;

    private String mname;

    private String mpassword;

    private static final long serialVersionUID = 1L;

    public Integer getMno() {
        return mno;
    }

    public void setMno(Integer mno) {
        this.mno = mno;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname == null ? null : mname.trim();
    }

    public String getMpassword() {
        return mpassword;
    }

    public void setMpassword(String mpassword) {
        this.mpassword = mpassword == null ? null : mpassword.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", mno=").append(mno);
        sb.append(", mname=").append(mname);
        sb.append(", mpassword=").append(mpassword);
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
        Manager other = (Manager) that;
        return (this.getMno() == null ? other.getMno() == null : this.getMno().equals(other.getMno()))
            && (this.getMname() == null ? other.getMname() == null : this.getMname().equals(other.getMname()))
            && (this.getMpassword() == null ? other.getMpassword() == null : this.getMpassword().equals(other.getMpassword()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMno() == null) ? 0 : getMno().hashCode());
        result = prime * result + ((getMname() == null) ? 0 : getMname().hashCode());
        result = prime * result + ((getMpassword() == null) ? 0 : getMpassword().hashCode());
        return result;
    }
}