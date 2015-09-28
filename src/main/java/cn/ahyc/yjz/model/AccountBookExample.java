package cn.ahyc.yjz.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class AccountBookExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table account_book
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table account_book
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table account_book
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_book
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    public AccountBookExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_book
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_book
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_book
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_book
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_book
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_book
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_book
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_book
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_book
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_book
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table account_book
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andBookNameIsNull() {
            addCriterion("book_name is null");
            return (Criteria) this;
        }

        public Criteria andBookNameIsNotNull() {
            addCriterion("book_name is not null");
            return (Criteria) this;
        }

        public Criteria andBookNameEqualTo(String value) {
            addCriterion("book_name =", value, "bookName");
            return (Criteria) this;
        }

        public Criteria andBookNameNotEqualTo(String value) {
            addCriterion("book_name <>", value, "bookName");
            return (Criteria) this;
        }

        public Criteria andBookNameGreaterThan(String value) {
            addCriterion("book_name >", value, "bookName");
            return (Criteria) this;
        }

        public Criteria andBookNameGreaterThanOrEqualTo(String value) {
            addCriterion("book_name >=", value, "bookName");
            return (Criteria) this;
        }

        public Criteria andBookNameLessThan(String value) {
            addCriterion("book_name <", value, "bookName");
            return (Criteria) this;
        }

        public Criteria andBookNameLessThanOrEqualTo(String value) {
            addCriterion("book_name <=", value, "bookName");
            return (Criteria) this;
        }

        public Criteria andBookNameLike(String value) {
            addCriterion("book_name like", value, "bookName");
            return (Criteria) this;
        }

        public Criteria andBookNameNotLike(String value) {
            addCriterion("book_name not like", value, "bookName");
            return (Criteria) this;
        }

        public Criteria andBookNameIn(List<String> values) {
            addCriterion("book_name in", values, "bookName");
            return (Criteria) this;
        }

        public Criteria andBookNameNotIn(List<String> values) {
            addCriterion("book_name not in", values, "bookName");
            return (Criteria) this;
        }

        public Criteria andBookNameBetween(String value1, String value2) {
            addCriterion("book_name between", value1, value2, "bookName");
            return (Criteria) this;
        }

        public Criteria andBookNameNotBetween(String value1, String value2) {
            addCriterion("book_name not between", value1, value2, "bookName");
            return (Criteria) this;
        }

        public Criteria andMoneyCodeIsNull() {
            addCriterion("money_code is null");
            return (Criteria) this;
        }

        public Criteria andMoneyCodeIsNotNull() {
            addCriterion("money_code is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyCodeEqualTo(String value) {
            addCriterion("money_code =", value, "moneyCode");
            return (Criteria) this;
        }

        public Criteria andMoneyCodeNotEqualTo(String value) {
            addCriterion("money_code <>", value, "moneyCode");
            return (Criteria) this;
        }

        public Criteria andMoneyCodeGreaterThan(String value) {
            addCriterion("money_code >", value, "moneyCode");
            return (Criteria) this;
        }

        public Criteria andMoneyCodeGreaterThanOrEqualTo(String value) {
            addCriterion("money_code >=", value, "moneyCode");
            return (Criteria) this;
        }

        public Criteria andMoneyCodeLessThan(String value) {
            addCriterion("money_code <", value, "moneyCode");
            return (Criteria) this;
        }

        public Criteria andMoneyCodeLessThanOrEqualTo(String value) {
            addCriterion("money_code <=", value, "moneyCode");
            return (Criteria) this;
        }

        public Criteria andMoneyCodeLike(String value) {
            addCriterion("money_code like", value, "moneyCode");
            return (Criteria) this;
        }

        public Criteria andMoneyCodeNotLike(String value) {
            addCriterion("money_code not like", value, "moneyCode");
            return (Criteria) this;
        }

        public Criteria andMoneyCodeIn(List<String> values) {
            addCriterion("money_code in", values, "moneyCode");
            return (Criteria) this;
        }

        public Criteria andMoneyCodeNotIn(List<String> values) {
            addCriterion("money_code not in", values, "moneyCode");
            return (Criteria) this;
        }

        public Criteria andMoneyCodeBetween(String value1, String value2) {
            addCriterion("money_code between", value1, value2, "moneyCode");
            return (Criteria) this;
        }

        public Criteria andMoneyCodeNotBetween(String value1, String value2) {
            addCriterion("money_code not between", value1, value2, "moneyCode");
            return (Criteria) this;
        }

        public Criteria andMoneyNameIsNull() {
            addCriterion("money_name is null");
            return (Criteria) this;
        }

        public Criteria andMoneyNameIsNotNull() {
            addCriterion("money_name is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyNameEqualTo(String value) {
            addCriterion("money_name =", value, "moneyName");
            return (Criteria) this;
        }

        public Criteria andMoneyNameNotEqualTo(String value) {
            addCriterion("money_name <>", value, "moneyName");
            return (Criteria) this;
        }

        public Criteria andMoneyNameGreaterThan(String value) {
            addCriterion("money_name >", value, "moneyName");
            return (Criteria) this;
        }

        public Criteria andMoneyNameGreaterThanOrEqualTo(String value) {
            addCriterion("money_name >=", value, "moneyName");
            return (Criteria) this;
        }

        public Criteria andMoneyNameLessThan(String value) {
            addCriterion("money_name <", value, "moneyName");
            return (Criteria) this;
        }

        public Criteria andMoneyNameLessThanOrEqualTo(String value) {
            addCriterion("money_name <=", value, "moneyName");
            return (Criteria) this;
        }

        public Criteria andMoneyNameLike(String value) {
            addCriterion("money_name like", value, "moneyName");
            return (Criteria) this;
        }

        public Criteria andMoneyNameNotLike(String value) {
            addCriterion("money_name not like", value, "moneyName");
            return (Criteria) this;
        }

        public Criteria andMoneyNameIn(List<String> values) {
            addCriterion("money_name in", values, "moneyName");
            return (Criteria) this;
        }

        public Criteria andMoneyNameNotIn(List<String> values) {
            addCriterion("money_name not in", values, "moneyName");
            return (Criteria) this;
        }

        public Criteria andMoneyNameBetween(String value1, String value2) {
            addCriterion("money_name between", value1, value2, "moneyName");
            return (Criteria) this;
        }

        public Criteria andMoneyNameNotBetween(String value1, String value2) {
            addCriterion("money_name not between", value1, value2, "moneyName");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("start_time is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Date value) {
            addCriterionForJDBCDate("start_time =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("start_time <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("start_time >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("start_time >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Date value) {
            addCriterionForJDBCDate("start_time <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("start_time <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Date> values) {
            addCriterionForJDBCDate("start_time in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("start_time not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("start_time between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("start_time not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andInitYearIsNull() {
            addCriterion("init_year is null");
            return (Criteria) this;
        }

        public Criteria andInitYearIsNotNull() {
            addCriterion("init_year is not null");
            return (Criteria) this;
        }

        public Criteria andInitYearEqualTo(Integer value) {
            addCriterion("init_year =", value, "initYear");
            return (Criteria) this;
        }

        public Criteria andInitYearNotEqualTo(Integer value) {
            addCriterion("init_year <>", value, "initYear");
            return (Criteria) this;
        }

        public Criteria andInitYearGreaterThan(Integer value) {
            addCriterion("init_year >", value, "initYear");
            return (Criteria) this;
        }

        public Criteria andInitYearGreaterThanOrEqualTo(Integer value) {
            addCriterion("init_year >=", value, "initYear");
            return (Criteria) this;
        }

        public Criteria andInitYearLessThan(Integer value) {
            addCriterion("init_year <", value, "initYear");
            return (Criteria) this;
        }

        public Criteria andInitYearLessThanOrEqualTo(Integer value) {
            addCriterion("init_year <=", value, "initYear");
            return (Criteria) this;
        }

        public Criteria andInitYearIn(List<Integer> values) {
            addCriterion("init_year in", values, "initYear");
            return (Criteria) this;
        }

        public Criteria andInitYearNotIn(List<Integer> values) {
            addCriterion("init_year not in", values, "initYear");
            return (Criteria) this;
        }

        public Criteria andInitYearBetween(Integer value1, Integer value2) {
            addCriterion("init_year between", value1, value2, "initYear");
            return (Criteria) this;
        }

        public Criteria andInitYearNotBetween(Integer value1, Integer value2) {
            addCriterion("init_year not between", value1, value2, "initYear");
            return (Criteria) this;
        }

        public Criteria andInitPeriodIsNull() {
            addCriterion("init_period is null");
            return (Criteria) this;
        }

        public Criteria andInitPeriodIsNotNull() {
            addCriterion("init_period is not null");
            return (Criteria) this;
        }

        public Criteria andInitPeriodEqualTo(Integer value) {
            addCriterion("init_period =", value, "initPeriod");
            return (Criteria) this;
        }

        public Criteria andInitPeriodNotEqualTo(Integer value) {
            addCriterion("init_period <>", value, "initPeriod");
            return (Criteria) this;
        }

        public Criteria andInitPeriodGreaterThan(Integer value) {
            addCriterion("init_period >", value, "initPeriod");
            return (Criteria) this;
        }

        public Criteria andInitPeriodGreaterThanOrEqualTo(Integer value) {
            addCriterion("init_period >=", value, "initPeriod");
            return (Criteria) this;
        }

        public Criteria andInitPeriodLessThan(Integer value) {
            addCriterion("init_period <", value, "initPeriod");
            return (Criteria) this;
        }

        public Criteria andInitPeriodLessThanOrEqualTo(Integer value) {
            addCriterion("init_period <=", value, "initPeriod");
            return (Criteria) this;
        }

        public Criteria andInitPeriodIn(List<Integer> values) {
            addCriterion("init_period in", values, "initPeriod");
            return (Criteria) this;
        }

        public Criteria andInitPeriodNotIn(List<Integer> values) {
            addCriterion("init_period not in", values, "initPeriod");
            return (Criteria) this;
        }

        public Criteria andInitPeriodBetween(Integer value1, Integer value2) {
            addCriterion("init_period between", value1, value2, "initPeriod");
            return (Criteria) this;
        }

        public Criteria andInitPeriodNotBetween(Integer value1, Integer value2) {
            addCriterion("init_period not between", value1, value2, "initPeriod");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIsNull() {
            addCriterion("company_name is null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIsNotNull() {
            addCriterion("company_name is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameEqualTo(String value) {
            addCriterion("company_name =", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotEqualTo(String value) {
            addCriterion("company_name <>", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThan(String value) {
            addCriterion("company_name >", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThanOrEqualTo(String value) {
            addCriterion("company_name >=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThan(String value) {
            addCriterion("company_name <", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThanOrEqualTo(String value) {
            addCriterion("company_name <=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLike(String value) {
            addCriterion("company_name like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotLike(String value) {
            addCriterion("company_name not like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIn(List<String> values) {
            addCriterion("company_name in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotIn(List<String> values) {
            addCriterion("company_name not in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameBetween(String value1, String value2) {
            addCriterion("company_name between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotBetween(String value1, String value2) {
            addCriterion("company_name not between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNull() {
            addCriterion("company_id is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNotNull() {
            addCriterion("company_id is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdEqualTo(String value) {
            addCriterion("company_id =", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotEqualTo(String value) {
            addCriterion("company_id <>", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThan(String value) {
            addCriterion("company_id >", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThanOrEqualTo(String value) {
            addCriterion("company_id >=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThan(String value) {
            addCriterion("company_id <", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThanOrEqualTo(String value) {
            addCriterion("company_id <=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLike(String value) {
            addCriterion("company_id like", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotLike(String value) {
            addCriterion("company_id not like", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIn(List<String> values) {
            addCriterion("company_id in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotIn(List<String> values) {
            addCriterion("company_id not in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdBetween(String value1, String value2) {
            addCriterion("company_id between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotBetween(String value1, String value2) {
            addCriterion("company_id not between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andDictValueIdIsNull() {
            addCriterion("dict_value_id is null");
            return (Criteria) this;
        }

        public Criteria andDictValueIdIsNotNull() {
            addCriterion("dict_value_id is not null");
            return (Criteria) this;
        }

        public Criteria andDictValueIdEqualTo(Integer value) {
            addCriterion("dict_value_id =", value, "dictValueId");
            return (Criteria) this;
        }

        public Criteria andDictValueIdNotEqualTo(Integer value) {
            addCriterion("dict_value_id <>", value, "dictValueId");
            return (Criteria) this;
        }

        public Criteria andDictValueIdGreaterThan(Integer value) {
            addCriterion("dict_value_id >", value, "dictValueId");
            return (Criteria) this;
        }

        public Criteria andDictValueIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("dict_value_id >=", value, "dictValueId");
            return (Criteria) this;
        }

        public Criteria andDictValueIdLessThan(Integer value) {
            addCriterion("dict_value_id <", value, "dictValueId");
            return (Criteria) this;
        }

        public Criteria andDictValueIdLessThanOrEqualTo(Integer value) {
            addCriterion("dict_value_id <=", value, "dictValueId");
            return (Criteria) this;
        }

        public Criteria andDictValueIdIn(List<Integer> values) {
            addCriterion("dict_value_id in", values, "dictValueId");
            return (Criteria) this;
        }

        public Criteria andDictValueIdNotIn(List<Integer> values) {
            addCriterion("dict_value_id not in", values, "dictValueId");
            return (Criteria) this;
        }

        public Criteria andDictValueIdBetween(Integer value1, Integer value2) {
            addCriterion("dict_value_id between", value1, value2, "dictValueId");
            return (Criteria) this;
        }

        public Criteria andDictValueIdNotBetween(Integer value1, Integer value2) {
            addCriterion("dict_value_id not between", value1, value2, "dictValueId");
            return (Criteria) this;
        }

        public Criteria andOverFlagIsNull() {
            addCriterion("over_flag is null");
            return (Criteria) this;
        }

        public Criteria andOverFlagIsNotNull() {
            addCriterion("over_flag is not null");
            return (Criteria) this;
        }

        public Criteria andOverFlagEqualTo(Integer value) {
            addCriterion("over_flag =", value, "overFlag");
            return (Criteria) this;
        }

        public Criteria andOverFlagNotEqualTo(Integer value) {
            addCriterion("over_flag <>", value, "overFlag");
            return (Criteria) this;
        }

        public Criteria andOverFlagGreaterThan(Integer value) {
            addCriterion("over_flag >", value, "overFlag");
            return (Criteria) this;
        }

        public Criteria andOverFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("over_flag >=", value, "overFlag");
            return (Criteria) this;
        }

        public Criteria andOverFlagLessThan(Integer value) {
            addCriterion("over_flag <", value, "overFlag");
            return (Criteria) this;
        }

        public Criteria andOverFlagLessThanOrEqualTo(Integer value) {
            addCriterion("over_flag <=", value, "overFlag");
            return (Criteria) this;
        }

        public Criteria andOverFlagIn(List<Integer> values) {
            addCriterion("over_flag in", values, "overFlag");
            return (Criteria) this;
        }

        public Criteria andOverFlagNotIn(List<Integer> values) {
            addCriterion("over_flag not in", values, "overFlag");
            return (Criteria) this;
        }

        public Criteria andOverFlagBetween(Integer value1, Integer value2) {
            addCriterion("over_flag between", value1, value2, "overFlag");
            return (Criteria) this;
        }

        public Criteria andOverFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("over_flag not between", value1, value2, "overFlag");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNull() {
            addCriterion("modify_time is null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNotNull() {
            addCriterion("modify_time is not null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeEqualTo(Date value) {
            addCriterion("modify_time =", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotEqualTo(Date value) {
            addCriterion("modify_time <>", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThan(Date value) {
            addCriterion("modify_time >", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("modify_time >=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThan(Date value) {
            addCriterion("modify_time <", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThanOrEqualTo(Date value) {
            addCriterion("modify_time <=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIn(List<Date> values) {
            addCriterion("modify_time in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotIn(List<Date> values) {
            addCriterion("modify_time not in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeBetween(Date value1, Date value2) {
            addCriterion("modify_time between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotBetween(Date value1, Date value2) {
            addCriterion("modify_time not between", value1, value2, "modifyTime");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table account_book
     *
     * @mbggenerated do_not_delete_during_merge Mon Sep 28 13:58:17 CST 2015
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table account_book
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
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