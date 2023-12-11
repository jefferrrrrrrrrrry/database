package com.example.buaadb.entity;

import java.util.ArrayList;
import java.util.List;

public class StudentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StudentExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andSnoIsNull() {
            addCriterion("Sno is null");
            return (Criteria) this;
        }

        public Criteria andSnoIsNotNull() {
            addCriterion("Sno is not null");
            return (Criteria) this;
        }

        public Criteria andSnoEqualTo(Integer value) {
            addCriterion("Sno =", value, "sno");
            return (Criteria) this;
        }

        public Criteria andSnoNotEqualTo(Integer value) {
            addCriterion("Sno <>", value, "sno");
            return (Criteria) this;
        }

        public Criteria andSnoGreaterThan(Integer value) {
            addCriterion("Sno >", value, "sno");
            return (Criteria) this;
        }

        public Criteria andSnoGreaterThanOrEqualTo(Integer value) {
            addCriterion("Sno >=", value, "sno");
            return (Criteria) this;
        }

        public Criteria andSnoLessThan(Integer value) {
            addCriterion("Sno <", value, "sno");
            return (Criteria) this;
        }

        public Criteria andSnoLessThanOrEqualTo(Integer value) {
            addCriterion("Sno <=", value, "sno");
            return (Criteria) this;
        }

        public Criteria andSnoIn(List<Integer> values) {
            addCriterion("Sno in", values, "sno");
            return (Criteria) this;
        }

        public Criteria andSnoNotIn(List<Integer> values) {
            addCriterion("Sno not in", values, "sno");
            return (Criteria) this;
        }

        public Criteria andSnoBetween(Integer value1, Integer value2) {
            addCriterion("Sno between", value1, value2, "sno");
            return (Criteria) this;
        }

        public Criteria andSnoNotBetween(Integer value1, Integer value2) {
            addCriterion("Sno not between", value1, value2, "sno");
            return (Criteria) this;
        }

        public Criteria andSnameIsNull() {
            addCriterion("Sname is null");
            return (Criteria) this;
        }

        public Criteria andSnameIsNotNull() {
            addCriterion("Sname is not null");
            return (Criteria) this;
        }

        public Criteria andSnameEqualTo(String value) {
            addCriterion("Sname =", value, "sname");
            return (Criteria) this;
        }

        public Criteria andSnameNotEqualTo(String value) {
            addCriterion("Sname <>", value, "sname");
            return (Criteria) this;
        }

        public Criteria andSnameGreaterThan(String value) {
            addCriterion("Sname >", value, "sname");
            return (Criteria) this;
        }

        public Criteria andSnameGreaterThanOrEqualTo(String value) {
            addCriterion("Sname >=", value, "sname");
            return (Criteria) this;
        }

        public Criteria andSnameLessThan(String value) {
            addCriterion("Sname <", value, "sname");
            return (Criteria) this;
        }

        public Criteria andSnameLessThanOrEqualTo(String value) {
            addCriterion("Sname <=", value, "sname");
            return (Criteria) this;
        }

        public Criteria andSnameLike(String value) {
            addCriterion("Sname like", value, "sname");
            return (Criteria) this;
        }

        public Criteria andSnameNotLike(String value) {
            addCriterion("Sname not like", value, "sname");
            return (Criteria) this;
        }

        public Criteria andSnameIn(List<String> values) {
            addCriterion("Sname in", values, "sname");
            return (Criteria) this;
        }

        public Criteria andSnameNotIn(List<String> values) {
            addCriterion("Sname not in", values, "sname");
            return (Criteria) this;
        }

        public Criteria andSnameBetween(String value1, String value2) {
            addCriterion("Sname between", value1, value2, "sname");
            return (Criteria) this;
        }

        public Criteria andSnameNotBetween(String value1, String value2) {
            addCriterion("Sname not between", value1, value2, "sname");
            return (Criteria) this;
        }

        public Criteria andSageIsNull() {
            addCriterion("Sage is null");
            return (Criteria) this;
        }

        public Criteria andSageIsNotNull() {
            addCriterion("Sage is not null");
            return (Criteria) this;
        }

        public Criteria andSageEqualTo(Integer value) {
            addCriterion("Sage =", value, "sage");
            return (Criteria) this;
        }

        public Criteria andSageNotEqualTo(Integer value) {
            addCriterion("Sage <>", value, "sage");
            return (Criteria) this;
        }

        public Criteria andSageGreaterThan(Integer value) {
            addCriterion("Sage >", value, "sage");
            return (Criteria) this;
        }

        public Criteria andSageGreaterThanOrEqualTo(Integer value) {
            addCriterion("Sage >=", value, "sage");
            return (Criteria) this;
        }

        public Criteria andSageLessThan(Integer value) {
            addCriterion("Sage <", value, "sage");
            return (Criteria) this;
        }

        public Criteria andSageLessThanOrEqualTo(Integer value) {
            addCriterion("Sage <=", value, "sage");
            return (Criteria) this;
        }

        public Criteria andSageIn(List<Integer> values) {
            addCriterion("Sage in", values, "sage");
            return (Criteria) this;
        }

        public Criteria andSageNotIn(List<Integer> values) {
            addCriterion("Sage not in", values, "sage");
            return (Criteria) this;
        }

        public Criteria andSageBetween(Integer value1, Integer value2) {
            addCriterion("Sage between", value1, value2, "sage");
            return (Criteria) this;
        }

        public Criteria andSageNotBetween(Integer value1, Integer value2) {
            addCriterion("Sage not between", value1, value2, "sage");
            return (Criteria) this;
        }

        public Criteria andSsexIsNull() {
            addCriterion("Ssex is null");
            return (Criteria) this;
        }

        public Criteria andSsexIsNotNull() {
            addCriterion("Ssex is not null");
            return (Criteria) this;
        }

        public Criteria andSsexEqualTo(String value) {
            addCriterion("Ssex =", value, "ssex");
            return (Criteria) this;
        }

        public Criteria andSsexNotEqualTo(String value) {
            addCriterion("Ssex <>", value, "ssex");
            return (Criteria) this;
        }

        public Criteria andSsexGreaterThan(String value) {
            addCriterion("Ssex >", value, "ssex");
            return (Criteria) this;
        }

        public Criteria andSsexGreaterThanOrEqualTo(String value) {
            addCriterion("Ssex >=", value, "ssex");
            return (Criteria) this;
        }

        public Criteria andSsexLessThan(String value) {
            addCriterion("Ssex <", value, "ssex");
            return (Criteria) this;
        }

        public Criteria andSsexLessThanOrEqualTo(String value) {
            addCriterion("Ssex <=", value, "ssex");
            return (Criteria) this;
        }

        public Criteria andSsexLike(String value) {
            addCriterion("Ssex like", value, "ssex");
            return (Criteria) this;
        }

        public Criteria andSsexNotLike(String value) {
            addCriterion("Ssex not like", value, "ssex");
            return (Criteria) this;
        }

        public Criteria andSsexIn(List<String> values) {
            addCriterion("Ssex in", values, "ssex");
            return (Criteria) this;
        }

        public Criteria andSsexNotIn(List<String> values) {
            addCriterion("Ssex not in", values, "ssex");
            return (Criteria) this;
        }

        public Criteria andSsexBetween(String value1, String value2) {
            addCriterion("Ssex between", value1, value2, "ssex");
            return (Criteria) this;
        }

        public Criteria andSsexNotBetween(String value1, String value2) {
            addCriterion("Ssex not between", value1, value2, "ssex");
            return (Criteria) this;
        }

        public Criteria andSgradeIsNull() {
            addCriterion("Sgrade is null");
            return (Criteria) this;
        }

        public Criteria andSgradeIsNotNull() {
            addCriterion("Sgrade is not null");
            return (Criteria) this;
        }

        public Criteria andSgradeEqualTo(Integer value) {
            addCriterion("Sgrade =", value, "sgrade");
            return (Criteria) this;
        }

        public Criteria andSgradeNotEqualTo(Integer value) {
            addCriterion("Sgrade <>", value, "sgrade");
            return (Criteria) this;
        }

        public Criteria andSgradeGreaterThan(Integer value) {
            addCriterion("Sgrade >", value, "sgrade");
            return (Criteria) this;
        }

        public Criteria andSgradeGreaterThanOrEqualTo(Integer value) {
            addCriterion("Sgrade >=", value, "sgrade");
            return (Criteria) this;
        }

        public Criteria andSgradeLessThan(Integer value) {
            addCriterion("Sgrade <", value, "sgrade");
            return (Criteria) this;
        }

        public Criteria andSgradeLessThanOrEqualTo(Integer value) {
            addCriterion("Sgrade <=", value, "sgrade");
            return (Criteria) this;
        }

        public Criteria andSgradeIn(List<Integer> values) {
            addCriterion("Sgrade in", values, "sgrade");
            return (Criteria) this;
        }

        public Criteria andSgradeNotIn(List<Integer> values) {
            addCriterion("Sgrade not in", values, "sgrade");
            return (Criteria) this;
        }

        public Criteria andSgradeBetween(Integer value1, Integer value2) {
            addCriterion("Sgrade between", value1, value2, "sgrade");
            return (Criteria) this;
        }

        public Criteria andSgradeNotBetween(Integer value1, Integer value2) {
            addCriterion("Sgrade not between", value1, value2, "sgrade");
            return (Criteria) this;
        }

        public Criteria andScreditIsNull() {
            addCriterion("Scredit is null");
            return (Criteria) this;
        }

        public Criteria andScreditIsNotNull() {
            addCriterion("Scredit is not null");
            return (Criteria) this;
        }

        public Criteria andScreditEqualTo(Integer value) {
            addCriterion("Scredit =", value, "scredit");
            return (Criteria) this;
        }

        public Criteria andScreditNotEqualTo(Integer value) {
            addCriterion("Scredit <>", value, "scredit");
            return (Criteria) this;
        }

        public Criteria andScreditGreaterThan(Integer value) {
            addCriterion("Scredit >", value, "scredit");
            return (Criteria) this;
        }

        public Criteria andScreditGreaterThanOrEqualTo(Integer value) {
            addCriterion("Scredit >=", value, "scredit");
            return (Criteria) this;
        }

        public Criteria andScreditLessThan(Integer value) {
            addCriterion("Scredit <", value, "scredit");
            return (Criteria) this;
        }

        public Criteria andScreditLessThanOrEqualTo(Integer value) {
            addCriterion("Scredit <=", value, "scredit");
            return (Criteria) this;
        }

        public Criteria andScreditIn(List<Integer> values) {
            addCriterion("Scredit in", values, "scredit");
            return (Criteria) this;
        }

        public Criteria andScreditNotIn(List<Integer> values) {
            addCriterion("Scredit not in", values, "scredit");
            return (Criteria) this;
        }

        public Criteria andScreditBetween(Integer value1, Integer value2) {
            addCriterion("Scredit between", value1, value2, "scredit");
            return (Criteria) this;
        }

        public Criteria andScreditNotBetween(Integer value1, Integer value2) {
            addCriterion("Scredit not between", value1, value2, "scredit");
            return (Criteria) this;
        }

        public Criteria andSpasswordIsNull() {
            addCriterion("Spassword is null");
            return (Criteria) this;
        }

        public Criteria andSpasswordIsNotNull() {
            addCriterion("Spassword is not null");
            return (Criteria) this;
        }

        public Criteria andSpasswordEqualTo(Integer value) {
            addCriterion("Spassword =", value, "spassword");
            return (Criteria) this;
        }

        public Criteria andSpasswordNotEqualTo(Integer value) {
            addCriterion("Spassword <>", value, "spassword");
            return (Criteria) this;
        }

        public Criteria andSpasswordGreaterThan(Integer value) {
            addCriterion("Spassword >", value, "spassword");
            return (Criteria) this;
        }

        public Criteria andSpasswordGreaterThanOrEqualTo(Integer value) {
            addCriterion("Spassword >=", value, "spassword");
            return (Criteria) this;
        }

        public Criteria andSpasswordLessThan(Integer value) {
            addCriterion("Spassword <", value, "spassword");
            return (Criteria) this;
        }

        public Criteria andSpasswordLessThanOrEqualTo(Integer value) {
            addCriterion("Spassword <=", value, "spassword");
            return (Criteria) this;
        }

        public Criteria andSpasswordIn(List<Integer> values) {
            addCriterion("Spassword in", values, "spassword");
            return (Criteria) this;
        }

        public Criteria andSpasswordNotIn(List<Integer> values) {
            addCriterion("Spassword not in", values, "spassword");
            return (Criteria) this;
        }

        public Criteria andSpasswordBetween(Integer value1, Integer value2) {
            addCriterion("Spassword between", value1, value2, "spassword");
            return (Criteria) this;
        }

        public Criteria andSpasswordNotBetween(Integer value1, Integer value2) {
            addCriterion("Spassword not between", value1, value2, "spassword");
            return (Criteria) this;
        }

        public Criteria andClnoIsNull() {
            addCriterion("Clno is null");
            return (Criteria) this;
        }

        public Criteria andClnoIsNotNull() {
            addCriterion("Clno is not null");
            return (Criteria) this;
        }

        public Criteria andClnoEqualTo(Integer value) {
            addCriterion("Clno =", value, "clno");
            return (Criteria) this;
        }

        public Criteria andClnoNotEqualTo(Integer value) {
            addCriterion("Clno <>", value, "clno");
            return (Criteria) this;
        }

        public Criteria andClnoGreaterThan(Integer value) {
            addCriterion("Clno >", value, "clno");
            return (Criteria) this;
        }

        public Criteria andClnoGreaterThanOrEqualTo(Integer value) {
            addCriterion("Clno >=", value, "clno");
            return (Criteria) this;
        }

        public Criteria andClnoLessThan(Integer value) {
            addCriterion("Clno <", value, "clno");
            return (Criteria) this;
        }

        public Criteria andClnoLessThanOrEqualTo(Integer value) {
            addCriterion("Clno <=", value, "clno");
            return (Criteria) this;
        }

        public Criteria andClnoIn(List<Integer> values) {
            addCriterion("Clno in", values, "clno");
            return (Criteria) this;
        }

        public Criteria andClnoNotIn(List<Integer> values) {
            addCriterion("Clno not in", values, "clno");
            return (Criteria) this;
        }

        public Criteria andClnoBetween(Integer value1, Integer value2) {
            addCriterion("Clno between", value1, value2, "clno");
            return (Criteria) this;
        }

        public Criteria andClnoNotBetween(Integer value1, Integer value2) {
            addCriterion("Clno not between", value1, value2, "clno");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}