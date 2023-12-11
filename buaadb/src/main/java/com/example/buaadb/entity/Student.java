package com.example.buaadb.entity;

import java.io.Serializable;

public class Student implements Serializable {
    private Integer sno;

    private String sname;

    private Integer sage;

    private String ssex;

    private Integer sgrade;

    private Integer scredit;

    private Integer spassword;

    private Integer clno;

    private static final long serialVersionUID = 1L;

    public Integer getSno() {
        return sno;
    }

    public void setSno(Integer sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname == null ? null : sname.trim();
    }

    public Integer getSage() {
        return sage;
    }

    public void setSage(Integer sage) {
        this.sage = sage;
    }

    public String getSsex() {
        return ssex;
    }

    public void setSsex(String ssex) {
        this.ssex = ssex == null ? null : ssex.trim();
    }

    public Integer getSgrade() {
        return sgrade;
    }

    public void setSgrade(Integer sgrade) {
        this.sgrade = sgrade;
    }

    public Integer getScredit() {
        return scredit;
    }

    public void setScredit(Integer scredit) {
        this.scredit = scredit;
    }

    public Integer getSpassword() {
        return spassword;
    }

    public void setSpassword(Integer spassword) {
        this.spassword = spassword;
    }

    public Integer getClno() {
        return clno;
    }

    public void setClno(Integer clno) {
        this.clno = clno;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", sno=").append(sno);
        sb.append(", sname=").append(sname);
        sb.append(", sage=").append(sage);
        sb.append(", ssex=").append(ssex);
        sb.append(", sgrade=").append(sgrade);
        sb.append(", scredit=").append(scredit);
        sb.append(", spassword=").append(spassword);
        sb.append(", clno=").append(clno);
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
        Student other = (Student) that;
        return (this.getSno() == null ? other.getSno() == null : this.getSno().equals(other.getSno()))
            && (this.getSname() == null ? other.getSname() == null : this.getSname().equals(other.getSname()))
            && (this.getSage() == null ? other.getSage() == null : this.getSage().equals(other.getSage()))
            && (this.getSsex() == null ? other.getSsex() == null : this.getSsex().equals(other.getSsex()))
            && (this.getSgrade() == null ? other.getSgrade() == null : this.getSgrade().equals(other.getSgrade()))
            && (this.getScredit() == null ? other.getScredit() == null : this.getScredit().equals(other.getScredit()))
            && (this.getSpassword() == null ? other.getSpassword() == null : this.getSpassword().equals(other.getSpassword()))
            && (this.getClno() == null ? other.getClno() == null : this.getClno().equals(other.getClno()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSno() == null) ? 0 : getSno().hashCode());
        result = prime * result + ((getSname() == null) ? 0 : getSname().hashCode());
        result = prime * result + ((getSage() == null) ? 0 : getSage().hashCode());
        result = prime * result + ((getSsex() == null) ? 0 : getSsex().hashCode());
        result = prime * result + ((getSgrade() == null) ? 0 : getSgrade().hashCode());
        result = prime * result + ((getScredit() == null) ? 0 : getScredit().hashCode());
        result = prime * result + ((getSpassword() == null) ? 0 : getSpassword().hashCode());
        result = prime * result + ((getClno() == null) ? 0 : getClno().hashCode());
        return result;
    }
}