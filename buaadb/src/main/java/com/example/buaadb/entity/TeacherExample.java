package com.example.buaadb.entity;

import java.util.ArrayList;
import java.util.List;

public class TeacherExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TeacherExample() {
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

        public Criteria andTnoIsNull() {
            addCriterion("Tno is null");
            return (Criteria) this;
        }

        public Criteria andTnoIsNotNull() {
            addCriterion("Tno is not null");
            return (Criteria) this;
        }

        public Criteria andTnoEqualTo(Integer value) {
            addCriterion("Tno =", value, "tno");
            return (Criteria) this;
        }

        public Criteria andTnoNotEqualTo(Integer value) {
            addCriterion("Tno <>", value, "tno");
            return (Criteria) this;
        }

        public Criteria andTnoGreaterThan(Integer value) {
            addCriterion("Tno >", value, "tno");
            return (Criteria) this;
        }

        public Criteria andTnoGreaterThanOrEqualTo(Integer value) {
            addCriterion("Tno >=", value, "tno");
            return (Criteria) this;
        }

        public Criteria andTnoLessThan(Integer value) {
            addCriterion("Tno <", value, "tno");
            return (Criteria) this;
        }

        public Criteria andTnoLessThanOrEqualTo(Integer value) {
            addCriterion("Tno <=", value, "tno");
            return (Criteria) this;
        }

        public Criteria andTnoIn(List<Integer> values) {
            addCriterion("Tno in", values, "tno");
            return (Criteria) this;
        }

        public Criteria andTnoNotIn(List<Integer> values) {
            addCriterion("Tno not in", values, "tno");
            return (Criteria) this;
        }

        public Criteria andTnoBetween(Integer value1, Integer value2) {
            addCriterion("Tno between", value1, value2, "tno");
            return (Criteria) this;
        }

        public Criteria andTnoNotBetween(Integer value1, Integer value2) {
            addCriterion("Tno not between", value1, value2, "tno");
            return (Criteria) this;
        }

        public Criteria andTnameIsNull() {
            addCriterion("Tname is null");
            return (Criteria) this;
        }

        public Criteria andTnameIsNotNull() {
            addCriterion("Tname is not null");
            return (Criteria) this;
        }

        public Criteria andTnameEqualTo(String value) {
            addCriterion("Tname =", value, "tname");
            return (Criteria) this;
        }

        public Criteria andTnameNotEqualTo(String value) {
            addCriterion("Tname <>", value, "tname");
            return (Criteria) this;
        }

        public Criteria andTnameGreaterThan(String value) {
            addCriterion("Tname >", value, "tname");
            return (Criteria) this;
        }

        public Criteria andTnameGreaterThanOrEqualTo(String value) {
            addCriterion("Tname >=", value, "tname");
            return (Criteria) this;
        }

        public Criteria andTnameLessThan(String value) {
            addCriterion("Tname <", value, "tname");
            return (Criteria) this;
        }

        public Criteria andTnameLessThanOrEqualTo(String value) {
            addCriterion("Tname <=", value, "tname");
            return (Criteria) this;
        }

        public Criteria andTnameLike(String value) {
            addCriterion("Tname like", value, "tname");
            return (Criteria) this;
        }

        public Criteria andTnameNotLike(String value) {
            addCriterion("Tname not like", value, "tname");
            return (Criteria) this;
        }

        public Criteria andTnameIn(List<String> values) {
            addCriterion("Tname in", values, "tname");
            return (Criteria) this;
        }

        public Criteria andTnameNotIn(List<String> values) {
            addCriterion("Tname not in", values, "tname");
            return (Criteria) this;
        }

        public Criteria andTnameBetween(String value1, String value2) {
            addCriterion("Tname between", value1, value2, "tname");
            return (Criteria) this;
        }

        public Criteria andTnameNotBetween(String value1, String value2) {
            addCriterion("Tname not between", value1, value2, "tname");
            return (Criteria) this;
        }

        public Criteria andTsexIsNull() {
            addCriterion("Tsex is null");
            return (Criteria) this;
        }

        public Criteria andTsexIsNotNull() {
            addCriterion("Tsex is not null");
            return (Criteria) this;
        }

        public Criteria andTsexEqualTo(String value) {
            addCriterion("Tsex =", value, "tsex");
            return (Criteria) this;
        }

        public Criteria andTsexNotEqualTo(String value) {
            addCriterion("Tsex <>", value, "tsex");
            return (Criteria) this;
        }

        public Criteria andTsexGreaterThan(String value) {
            addCriterion("Tsex >", value, "tsex");
            return (Criteria) this;
        }

        public Criteria andTsexGreaterThanOrEqualTo(String value) {
            addCriterion("Tsex >=", value, "tsex");
            return (Criteria) this;
        }

        public Criteria andTsexLessThan(String value) {
            addCriterion("Tsex <", value, "tsex");
            return (Criteria) this;
        }

        public Criteria andTsexLessThanOrEqualTo(String value) {
            addCriterion("Tsex <=", value, "tsex");
            return (Criteria) this;
        }

        public Criteria andTsexLike(String value) {
            addCriterion("Tsex like", value, "tsex");
            return (Criteria) this;
        }

        public Criteria andTsexNotLike(String value) {
            addCriterion("Tsex not like", value, "tsex");
            return (Criteria) this;
        }

        public Criteria andTsexIn(List<String> values) {
            addCriterion("Tsex in", values, "tsex");
            return (Criteria) this;
        }

        public Criteria andTsexNotIn(List<String> values) {
            addCriterion("Tsex not in", values, "tsex");
            return (Criteria) this;
        }

        public Criteria andTsexBetween(String value1, String value2) {
            addCriterion("Tsex between", value1, value2, "tsex");
            return (Criteria) this;
        }

        public Criteria andTsexNotBetween(String value1, String value2) {
            addCriterion("Tsex not between", value1, value2, "tsex");
            return (Criteria) this;
        }

        public Criteria andTtitleIsNull() {
            addCriterion("Ttitle is null");
            return (Criteria) this;
        }

        public Criteria andTtitleIsNotNull() {
            addCriterion("Ttitle is not null");
            return (Criteria) this;
        }

        public Criteria andTtitleEqualTo(String value) {
            addCriterion("Ttitle =", value, "ttitle");
            return (Criteria) this;
        }

        public Criteria andTtitleNotEqualTo(String value) {
            addCriterion("Ttitle <>", value, "ttitle");
            return (Criteria) this;
        }

        public Criteria andTtitleGreaterThan(String value) {
            addCriterion("Ttitle >", value, "ttitle");
            return (Criteria) this;
        }

        public Criteria andTtitleGreaterThanOrEqualTo(String value) {
            addCriterion("Ttitle >=", value, "ttitle");
            return (Criteria) this;
        }

        public Criteria andTtitleLessThan(String value) {
            addCriterion("Ttitle <", value, "ttitle");
            return (Criteria) this;
        }

        public Criteria andTtitleLessThanOrEqualTo(String value) {
            addCriterion("Ttitle <=", value, "ttitle");
            return (Criteria) this;
        }

        public Criteria andTtitleLike(String value) {
            addCriterion("Ttitle like", value, "ttitle");
            return (Criteria) this;
        }

        public Criteria andTtitleNotLike(String value) {
            addCriterion("Ttitle not like", value, "ttitle");
            return (Criteria) this;
        }

        public Criteria andTtitleIn(List<String> values) {
            addCriterion("Ttitle in", values, "ttitle");
            return (Criteria) this;
        }

        public Criteria andTtitleNotIn(List<String> values) {
            addCriterion("Ttitle not in", values, "ttitle");
            return (Criteria) this;
        }

        public Criteria andTtitleBetween(String value1, String value2) {
            addCriterion("Ttitle between", value1, value2, "ttitle");
            return (Criteria) this;
        }

        public Criteria andTtitleNotBetween(String value1, String value2) {
            addCriterion("Ttitle not between", value1, value2, "ttitle");
            return (Criteria) this;
        }

        public Criteria andTpasswordIsNull() {
            addCriterion("Tpassword is null");
            return (Criteria) this;
        }

        public Criteria andTpasswordIsNotNull() {
            addCriterion("Tpassword is not null");
            return (Criteria) this;
        }

        public Criteria andTpasswordEqualTo(Integer value) {
            addCriterion("Tpassword =", value, "tpassword");
            return (Criteria) this;
        }

        public Criteria andTpasswordNotEqualTo(Integer value) {
            addCriterion("Tpassword <>", value, "tpassword");
            return (Criteria) this;
        }

        public Criteria andTpasswordGreaterThan(Integer value) {
            addCriterion("Tpassword >", value, "tpassword");
            return (Criteria) this;
        }

        public Criteria andTpasswordGreaterThanOrEqualTo(Integer value) {
            addCriterion("Tpassword >=", value, "tpassword");
            return (Criteria) this;
        }

        public Criteria andTpasswordLessThan(Integer value) {
            addCriterion("Tpassword <", value, "tpassword");
            return (Criteria) this;
        }

        public Criteria andTpasswordLessThanOrEqualTo(Integer value) {
            addCriterion("Tpassword <=", value, "tpassword");
            return (Criteria) this;
        }

        public Criteria andTpasswordIn(List<Integer> values) {
            addCriterion("Tpassword in", values, "tpassword");
            return (Criteria) this;
        }

        public Criteria andTpasswordNotIn(List<Integer> values) {
            addCriterion("Tpassword not in", values, "tpassword");
            return (Criteria) this;
        }

        public Criteria andTpasswordBetween(Integer value1, Integer value2) {
            addCriterion("Tpassword between", value1, value2, "tpassword");
            return (Criteria) this;
        }

        public Criteria andTpasswordNotBetween(Integer value1, Integer value2) {
            addCriterion("Tpassword not between", value1, value2, "tpassword");
            return (Criteria) this;
        }

        public Criteria andScnoIsNull() {
            addCriterion("Scno is null");
            return (Criteria) this;
        }

        public Criteria andScnoIsNotNull() {
            addCriterion("Scno is not null");
            return (Criteria) this;
        }

        public Criteria andScnoEqualTo(Integer value) {
            addCriterion("Scno =", value, "scno");
            return (Criteria) this;
        }

        public Criteria andScnoNotEqualTo(Integer value) {
            addCriterion("Scno <>", value, "scno");
            return (Criteria) this;
        }

        public Criteria andScnoGreaterThan(Integer value) {
            addCriterion("Scno >", value, "scno");
            return (Criteria) this;
        }

        public Criteria andScnoGreaterThanOrEqualTo(Integer value) {
            addCriterion("Scno >=", value, "scno");
            return (Criteria) this;
        }

        public Criteria andScnoLessThan(Integer value) {
            addCriterion("Scno <", value, "scno");
            return (Criteria) this;
        }

        public Criteria andScnoLessThanOrEqualTo(Integer value) {
            addCriterion("Scno <=", value, "scno");
            return (Criteria) this;
        }

        public Criteria andScnoIn(List<Integer> values) {
            addCriterion("Scno in", values, "scno");
            return (Criteria) this;
        }

        public Criteria andScnoNotIn(List<Integer> values) {
            addCriterion("Scno not in", values, "scno");
            return (Criteria) this;
        }

        public Criteria andScnoBetween(Integer value1, Integer value2) {
            addCriterion("Scno between", value1, value2, "scno");
            return (Criteria) this;
        }

        public Criteria andScnoNotBetween(Integer value1, Integer value2) {
            addCriterion("Scno not between", value1, value2, "scno");
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