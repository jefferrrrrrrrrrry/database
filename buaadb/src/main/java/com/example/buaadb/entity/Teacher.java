package com.example.buaadb.entity;

import java.io.Serializable;

public class Teacher implements Serializable {
    private Integer tno;

    private String tname;

    private String tsex;

    private String ttitle;

    private Integer tpassword;

    private Integer scno;

    private static final long serialVersionUID = 1L;

    public Integer getTno() {
        return tno;
    }

    public void setTno(Integer tno) {
        this.tno = tno;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname == null ? null : tname.trim();
    }

    public String getTsex() {
        return tsex;
    }

    public void setTsex(String tsex) {
        this.tsex = tsex == null ? null : tsex.trim();
    }

    public String getTtitle() {
        return ttitle;
    }

    public void setTtitle(String ttitle) {
        this.ttitle = ttitle == null ? null : ttitle.trim();
    }

    public Integer getTpassword() {
        return tpassword;
    }

    public void setTpassword(Integer tpassword) {
        this.tpassword = tpassword;
    }

    public Integer getScno() {
        return scno;
    }

    public void setScno(Integer scno) {
        this.scno = scno;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tno=").append(tno);
        sb.append(", tname=").append(tname);
        sb.append(", tsex=").append(tsex);
        sb.append(", ttitle=").append(ttitle);
        sb.append(", tpassword=").append(tpassword);
        sb.append(", scno=").append(scno);
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
        Teacher other = (Teacher) that;
        return (this.getTno() == null ? other.getTno() == null : this.getTno().equals(other.getTno()))
            && (this.getTname() == null ? other.getTname() == null : this.getTname().equals(other.getTname()))
            && (this.getTsex() == null ? other.getTsex() == null : this.getTsex().equals(other.getTsex()))
            && (this.getTtitle() == null ? other.getTtitle() == null : this.getTtitle().equals(other.getTtitle()))
            && (this.getTpassword() == null ? other.getTpassword() == null : this.getTpassword().equals(other.getTpassword()))
            && (this.getScno() == null ? other.getScno() == null : this.getScno().equals(other.getScno()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTno() == null) ? 0 : getTno().hashCode());
        result = prime * result + ((getTname() == null) ? 0 : getTname().hashCode());
        result = prime * result + ((getTsex() == null) ? 0 : getTsex().hashCode());
        result = prime * result + ((getTtitle() == null) ? 0 : getTtitle().hashCode());
        result = prime * result + ((getTpassword() == null) ? 0 : getTpassword().hashCode());
        result = prime * result + ((getScno() == null) ? 0 : getScno().hashCode());
        return result;
    }
}