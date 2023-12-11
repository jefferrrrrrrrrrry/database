package com.example.buaadb.entity;

import java.util.ArrayList;
import java.util.List;

public class CourseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CourseExample() {
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

        public Criteria andCnoIsNull() {
            addCriterion("Cno is null");
            return (Criteria) this;
        }

        public Criteria andCnoIsNotNull() {
            addCriterion("Cno is not null");
            return (Criteria) this;
        }

        public Criteria andCnoEqualTo(String value) {
            addCriterion("Cno =", value, "cno");
            return (Criteria) this;
        }

        public Criteria andCnoNotEqualTo(String value) {
            addCriterion("Cno <>", value, "cno");
            return (Criteria) this;
        }

        public Criteria andCnoGreaterThan(String value) {
            addCriterion("Cno >", value, "cno");
            return (Criteria) this;
        }

        public Criteria andCnoGreaterThanOrEqualTo(String value) {
            addCriterion("Cno >=", value, "cno");
            return (Criteria) this;
        }

        public Criteria andCnoLessThan(String value) {
            addCriterion("Cno <", value, "cno");
            return (Criteria) this;
        }

        public Criteria andCnoLessThanOrEqualTo(String value) {
            addCriterion("Cno <=", value, "cno");
            return (Criteria) this;
        }

        public Criteria andCnoLike(String value) {
            addCriterion("Cno like", value, "cno");
            return (Criteria) this;
        }

        public Criteria andCnoNotLike(String value) {
            addCriterion("Cno not like", value, "cno");
            return (Criteria) this;
        }

        public Criteria andCnoIn(List<String> values) {
            addCriterion("Cno in", values, "cno");
            return (Criteria) this;
        }

        public Criteria andCnoNotIn(List<String> values) {
            addCriterion("Cno not in", values, "cno");
            return (Criteria) this;
        }

        public Criteria andCnoBetween(String value1, String value2) {
            addCriterion("Cno between", value1, value2, "cno");
            return (Criteria) this;
        }

        public Criteria andCnoNotBetween(String value1, String value2) {
            addCriterion("Cno not between", value1, value2, "cno");
            return (Criteria) this;
        }

        public Criteria andCnameIsNull() {
            addCriterion("Cname is null");
            return (Criteria) this;
        }

        public Criteria andCnameIsNotNull() {
            addCriterion("Cname is not null");
            return (Criteria) this;
        }

        public Criteria andCnameEqualTo(String value) {
            addCriterion("Cname =", value, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameNotEqualTo(String value) {
            addCriterion("Cname <>", value, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameGreaterThan(String value) {
            addCriterion("Cname >", value, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameGreaterThanOrEqualTo(String value) {
            addCriterion("Cname >=", value, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameLessThan(String value) {
            addCriterion("Cname <", value, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameLessThanOrEqualTo(String value) {
            addCriterion("Cname <=", value, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameLike(String value) {
            addCriterion("Cname like", value, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameNotLike(String value) {
            addCriterion("Cname not like", value, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameIn(List<String> values) {
            addCriterion("Cname in", values, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameNotIn(List<String> values) {
            addCriterion("Cname not in", values, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameBetween(String value1, String value2) {
            addCriterion("Cname between", value1, value2, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameNotBetween(String value1, String value2) {
            addCriterion("Cname not between", value1, value2, "cname");
            return (Criteria) this;
        }

        public Criteria andCposIsNull() {
            addCriterion("Cpos is null");
            return (Criteria) this;
        }

        public Criteria andCposIsNotNull() {
            addCriterion("Cpos is not null");
            return (Criteria) this;
        }

        public Criteria andCposEqualTo(String value) {
            addCriterion("Cpos =", value, "cpos");
            return (Criteria) this;
        }

        public Criteria andCposNotEqualTo(String value) {
            addCriterion("Cpos <>", value, "cpos");
            return (Criteria) this;
        }

        public Criteria andCposGreaterThan(String value) {
            addCriterion("Cpos >", value, "cpos");
            return (Criteria) this;
        }

        public Criteria andCposGreaterThanOrEqualTo(String value) {
            addCriterion("Cpos >=", value, "cpos");
            return (Criteria) this;
        }

        public Criteria andCposLessThan(String value) {
            addCriterion("Cpos <", value, "cpos");
            return (Criteria) this;
        }

        public Criteria andCposLessThanOrEqualTo(String value) {
            addCriterion("Cpos <=", value, "cpos");
            return (Criteria) this;
        }

        public Criteria andCposLike(String value) {
            addCriterion("Cpos like", value, "cpos");
            return (Criteria) this;
        }

        public Criteria andCposNotLike(String value) {
            addCriterion("Cpos not like", value, "cpos");
            return (Criteria) this;
        }

        public Criteria andCposIn(List<String> values) {
            addCriterion("Cpos in", values, "cpos");
            return (Criteria) this;
        }

        public Criteria andCposNotIn(List<String> values) {
            addCriterion("Cpos not in", values, "cpos");
            return (Criteria) this;
        }

        public Criteria andCposBetween(String value1, String value2) {
            addCriterion("Cpos between", value1, value2, "cpos");
            return (Criteria) this;
        }

        public Criteria andCposNotBetween(String value1, String value2) {
            addCriterion("Cpos not between", value1, value2, "cpos");
            return (Criteria) this;
        }

        public Criteria andCtypeIsNull() {
            addCriterion("Ctype is null");
            return (Criteria) this;
        }

        public Criteria andCtypeIsNotNull() {
            addCriterion("Ctype is not null");
            return (Criteria) this;
        }

        public Criteria andCtypeEqualTo(String value) {
            addCriterion("Ctype =", value, "ctype");
            return (Criteria) this;
        }

        public Criteria andCtypeNotEqualTo(String value) {
            addCriterion("Ctype <>", value, "ctype");
            return (Criteria) this;
        }

        public Criteria andCtypeGreaterThan(String value) {
            addCriterion("Ctype >", value, "ctype");
            return (Criteria) this;
        }

        public Criteria andCtypeGreaterThanOrEqualTo(String value) {
            addCriterion("Ctype >=", value, "ctype");
            return (Criteria) this;
        }

        public Criteria andCtypeLessThan(String value) {
            addCriterion("Ctype <", value, "ctype");
            return (Criteria) this;
        }

        public Criteria andCtypeLessThanOrEqualTo(String value) {
            addCriterion("Ctype <=", value, "ctype");
            return (Criteria) this;
        }

        public Criteria andCtypeLike(String value) {
            addCriterion("Ctype like", value, "ctype");
            return (Criteria) this;
        }

        public Criteria andCtypeNotLike(String value) {
            addCriterion("Ctype not like", value, "ctype");
            return (Criteria) this;
        }

        public Criteria andCtypeIn(List<String> values) {
            addCriterion("Ctype in", values, "ctype");
            return (Criteria) this;
        }

        public Criteria andCtypeNotIn(List<String> values) {
            addCriterion("Ctype not in", values, "ctype");
            return (Criteria) this;
        }

        public Criteria andCtypeBetween(String value1, String value2) {
            addCriterion("Ctype between", value1, value2, "ctype");
            return (Criteria) this;
        }

        public Criteria andCtypeNotBetween(String value1, String value2) {
            addCriterion("Ctype not between", value1, value2, "ctype");
            return (Criteria) this;
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

        public Criteria andCcreditIsNull() {
            addCriterion("Ccredit is null");
            return (Criteria) this;
        }

        public Criteria andCcreditIsNotNull() {
            addCriterion("Ccredit is not null");
            return (Criteria) this;
        }

        public Criteria andCcreditEqualTo(Integer value) {
            addCriterion("Ccredit =", value, "ccredit");
            return (Criteria) this;
        }

        public Criteria andCcreditNotEqualTo(Integer value) {
            addCriterion("Ccredit <>", value, "ccredit");
            return (Criteria) this;
        }

        public Criteria andCcreditGreaterThan(Integer value) {
            addCriterion("Ccredit >", value, "ccredit");
            return (Criteria) this;
        }

        public Criteria andCcreditGreaterThanOrEqualTo(Integer value) {
            addCriterion("Ccredit >=", value, "ccredit");
            return (Criteria) this;
        }

        public Criteria andCcreditLessThan(Integer value) {
            addCriterion("Ccredit <", value, "ccredit");
            return (Criteria) this;
        }

        public Criteria andCcreditLessThanOrEqualTo(Integer value) {
            addCriterion("Ccredit <=", value, "ccredit");
            return (Criteria) this;
        }

        public Criteria andCcreditIn(List<Integer> values) {
            addCriterion("Ccredit in", values, "ccredit");
            return (Criteria) this;
        }

        public Criteria andCcreditNotIn(List<Integer> values) {
            addCriterion("Ccredit not in", values, "ccredit");
            return (Criteria) this;
        }

        public Criteria andCcreditBetween(Integer value1, Integer value2) {
            addCriterion("Ccredit between", value1, value2, "ccredit");
            return (Criteria) this;
        }

        public Criteria andCcreditNotBetween(Integer value1, Integer value2) {
            addCriterion("Ccredit not between", value1, value2, "ccredit");
            return (Criteria) this;
        }

        public Criteria andCcapacityIsNull() {
            addCriterion("Ccapacity is null");
            return (Criteria) this;
        }

        public Criteria andCcapacityIsNotNull() {
            addCriterion("Ccapacity is not null");
            return (Criteria) this;
        }

        public Criteria andCcapacityEqualTo(Integer value) {
            addCriterion("Ccapacity =", value, "ccapacity");
            return (Criteria) this;
        }

        public Criteria andCcapacityNotEqualTo(Integer value) {
            addCriterion("Ccapacity <>", value, "ccapacity");
            return (Criteria) this;
        }

        public Criteria andCcapacityGreaterThan(Integer value) {
            addCriterion("Ccapacity >", value, "ccapacity");
            return (Criteria) this;
        }

        public Criteria andCcapacityGreaterThanOrEqualTo(Integer value) {
            addCriterion("Ccapacity >=", value, "ccapacity");
            return (Criteria) this;
        }

        public Criteria andCcapacityLessThan(Integer value) {
            addCriterion("Ccapacity <", value, "ccapacity");
            return (Criteria) this;
        }

        public Criteria andCcapacityLessThanOrEqualTo(Integer value) {
            addCriterion("Ccapacity <=", value, "ccapacity");
            return (Criteria) this;
        }

        public Criteria andCcapacityIn(List<Integer> values) {
            addCriterion("Ccapacity in", values, "ccapacity");
            return (Criteria) this;
        }

        public Criteria andCcapacityNotIn(List<Integer> values) {
            addCriterion("Ccapacity not in", values, "ccapacity");
            return (Criteria) this;
        }

        public Criteria andCcapacityBetween(Integer value1, Integer value2) {
            addCriterion("Ccapacity between", value1, value2, "ccapacity");
            return (Criteria) this;
        }

        public Criteria andCcapacityNotBetween(Integer value1, Integer value2) {
            addCriterion("Ccapacity not between", value1, value2, "ccapacity");
            return (Criteria) this;
        }

        public Criteria andCremainIsNull() {
            addCriterion("Cremain is null");
            return (Criteria) this;
        }

        public Criteria andCremainIsNotNull() {
            addCriterion("Cremain is not null");
            return (Criteria) this;
        }

        public Criteria andCremainEqualTo(Integer value) {
            addCriterion("Cremain =", value, "cremain");
            return (Criteria) this;
        }

        public Criteria andCremainNotEqualTo(Integer value) {
            addCriterion("Cremain <>", value, "cremain");
            return (Criteria) this;
        }

        public Criteria andCremainGreaterThan(Integer value) {
            addCriterion("Cremain >", value, "cremain");
            return (Criteria) this;
        }

        public Criteria andCremainGreaterThanOrEqualTo(Integer value) {
            addCriterion("Cremain >=", value, "cremain");
            return (Criteria) this;
        }

        public Criteria andCremainLessThan(Integer value) {
            addCriterion("Cremain <", value, "cremain");
            return (Criteria) this;
        }

        public Criteria andCremainLessThanOrEqualTo(Integer value) {
            addCriterion("Cremain <=", value, "cremain");
            return (Criteria) this;
        }

        public Criteria andCremainIn(List<Integer> values) {
            addCriterion("Cremain in", values, "cremain");
            return (Criteria) this;
        }

        public Criteria andCremainNotIn(List<Integer> values) {
            addCriterion("Cremain not in", values, "cremain");
            return (Criteria) this;
        }

        public Criteria andCremainBetween(Integer value1, Integer value2) {
            addCriterion("Cremain between", value1, value2, "cremain");
            return (Criteria) this;
        }

        public Criteria andCremainNotBetween(Integer value1, Integer value2) {
            addCriterion("Cremain not between", value1, value2, "cremain");
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