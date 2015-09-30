package cn.ahyc.yjz.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VoucherTemplateExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table voucher_template
     *
     * @mbggenerated Wed Sep 30 15:25:10 CST 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table voucher_template
     *
     * @mbggenerated Wed Sep 30 15:25:10 CST 2015
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table voucher_template
     *
     * @mbggenerated Wed Sep 30 15:25:10 CST 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voucher_template
     *
     * @mbggenerated Wed Sep 30 15:25:10 CST 2015
     */
    public VoucherTemplateExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voucher_template
     *
     * @mbggenerated Wed Sep 30 15:25:10 CST 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voucher_template
     *
     * @mbggenerated Wed Sep 30 15:25:10 CST 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voucher_template
     *
     * @mbggenerated Wed Sep 30 15:25:10 CST 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voucher_template
     *
     * @mbggenerated Wed Sep 30 15:25:10 CST 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voucher_template
     *
     * @mbggenerated Wed Sep 30 15:25:10 CST 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voucher_template
     *
     * @mbggenerated Wed Sep 30 15:25:10 CST 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voucher_template
     *
     * @mbggenerated Wed Sep 30 15:25:10 CST 2015
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voucher_template
     *
     * @mbggenerated Wed Sep 30 15:25:10 CST 2015
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
     * This method corresponds to the database table voucher_template
     *
     * @mbggenerated Wed Sep 30 15:25:10 CST 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voucher_template
     *
     * @mbggenerated Wed Sep 30 15:25:10 CST 2015
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table voucher_template
     *
     * @mbggenerated Wed Sep 30 15:25:10 CST 2015
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

        public Criteria andVoucherWordIsNull() {
            addCriterion("voucher_word is null");
            return (Criteria) this;
        }

        public Criteria andVoucherWordIsNotNull() {
            addCriterion("voucher_word is not null");
            return (Criteria) this;
        }

        public Criteria andVoucherWordEqualTo(String value) {
            addCriterion("voucher_word =", value, "voucherWord");
            return (Criteria) this;
        }

        public Criteria andVoucherWordNotEqualTo(String value) {
            addCriterion("voucher_word <>", value, "voucherWord");
            return (Criteria) this;
        }

        public Criteria andVoucherWordGreaterThan(String value) {
            addCriterion("voucher_word >", value, "voucherWord");
            return (Criteria) this;
        }

        public Criteria andVoucherWordGreaterThanOrEqualTo(String value) {
            addCriterion("voucher_word >=", value, "voucherWord");
            return (Criteria) this;
        }

        public Criteria andVoucherWordLessThan(String value) {
            addCriterion("voucher_word <", value, "voucherWord");
            return (Criteria) this;
        }

        public Criteria andVoucherWordLessThanOrEqualTo(String value) {
            addCriterion("voucher_word <=", value, "voucherWord");
            return (Criteria) this;
        }

        public Criteria andVoucherWordLike(String value) {
            addCriterion("voucher_word like", value, "voucherWord");
            return (Criteria) this;
        }

        public Criteria andVoucherWordNotLike(String value) {
            addCriterion("voucher_word not like", value, "voucherWord");
            return (Criteria) this;
        }

        public Criteria andVoucherWordIn(List<String> values) {
            addCriterion("voucher_word in", values, "voucherWord");
            return (Criteria) this;
        }

        public Criteria andVoucherWordNotIn(List<String> values) {
            addCriterion("voucher_word not in", values, "voucherWord");
            return (Criteria) this;
        }

        public Criteria andVoucherWordBetween(String value1, String value2) {
            addCriterion("voucher_word between", value1, value2, "voucherWord");
            return (Criteria) this;
        }

        public Criteria andVoucherWordNotBetween(String value1, String value2) {
            addCriterion("voucher_word not between", value1, value2, "voucherWord");
            return (Criteria) this;
        }

        public Criteria andBillNumIsNull() {
            addCriterion("bill_num is null");
            return (Criteria) this;
        }

        public Criteria andBillNumIsNotNull() {
            addCriterion("bill_num is not null");
            return (Criteria) this;
        }

        public Criteria andBillNumEqualTo(Integer value) {
            addCriterion("bill_num =", value, "billNum");
            return (Criteria) this;
        }

        public Criteria andBillNumNotEqualTo(Integer value) {
            addCriterion("bill_num <>", value, "billNum");
            return (Criteria) this;
        }

        public Criteria andBillNumGreaterThan(Integer value) {
            addCriterion("bill_num >", value, "billNum");
            return (Criteria) this;
        }

        public Criteria andBillNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("bill_num >=", value, "billNum");
            return (Criteria) this;
        }

        public Criteria andBillNumLessThan(Integer value) {
            addCriterion("bill_num <", value, "billNum");
            return (Criteria) this;
        }

        public Criteria andBillNumLessThanOrEqualTo(Integer value) {
            addCriterion("bill_num <=", value, "billNum");
            return (Criteria) this;
        }

        public Criteria andBillNumIn(List<Integer> values) {
            addCriterion("bill_num in", values, "billNum");
            return (Criteria) this;
        }

        public Criteria andBillNumNotIn(List<Integer> values) {
            addCriterion("bill_num not in", values, "billNum");
            return (Criteria) this;
        }

        public Criteria andBillNumBetween(Integer value1, Integer value2) {
            addCriterion("bill_num between", value1, value2, "billNum");
            return (Criteria) this;
        }

        public Criteria andBillNumNotBetween(Integer value1, Integer value2) {
            addCriterion("bill_num not between", value1, value2, "billNum");
            return (Criteria) this;
        }

        public Criteria andAuditUserIsNull() {
            addCriterion("audit_user is null");
            return (Criteria) this;
        }

        public Criteria andAuditUserIsNotNull() {
            addCriterion("audit_user is not null");
            return (Criteria) this;
        }

        public Criteria andAuditUserEqualTo(String value) {
            addCriterion("audit_user =", value, "auditUser");
            return (Criteria) this;
        }

        public Criteria andAuditUserNotEqualTo(String value) {
            addCriterion("audit_user <>", value, "auditUser");
            return (Criteria) this;
        }

        public Criteria andAuditUserGreaterThan(String value) {
            addCriterion("audit_user >", value, "auditUser");
            return (Criteria) this;
        }

        public Criteria andAuditUserGreaterThanOrEqualTo(String value) {
            addCriterion("audit_user >=", value, "auditUser");
            return (Criteria) this;
        }

        public Criteria andAuditUserLessThan(String value) {
            addCriterion("audit_user <", value, "auditUser");
            return (Criteria) this;
        }

        public Criteria andAuditUserLessThanOrEqualTo(String value) {
            addCriterion("audit_user <=", value, "auditUser");
            return (Criteria) this;
        }

        public Criteria andAuditUserLike(String value) {
            addCriterion("audit_user like", value, "auditUser");
            return (Criteria) this;
        }

        public Criteria andAuditUserNotLike(String value) {
            addCriterion("audit_user not like", value, "auditUser");
            return (Criteria) this;
        }

        public Criteria andAuditUserIn(List<String> values) {
            addCriterion("audit_user in", values, "auditUser");
            return (Criteria) this;
        }

        public Criteria andAuditUserNotIn(List<String> values) {
            addCriterion("audit_user not in", values, "auditUser");
            return (Criteria) this;
        }

        public Criteria andAuditUserBetween(String value1, String value2) {
            addCriterion("audit_user between", value1, value2, "auditUser");
            return (Criteria) this;
        }

        public Criteria andAuditUserNotBetween(String value1, String value2) {
            addCriterion("audit_user not between", value1, value2, "auditUser");
            return (Criteria) this;
        }

        public Criteria andPostingUserIsNull() {
            addCriterion("posting_user is null");
            return (Criteria) this;
        }

        public Criteria andPostingUserIsNotNull() {
            addCriterion("posting_user is not null");
            return (Criteria) this;
        }

        public Criteria andPostingUserEqualTo(String value) {
            addCriterion("posting_user =", value, "postingUser");
            return (Criteria) this;
        }

        public Criteria andPostingUserNotEqualTo(String value) {
            addCriterion("posting_user <>", value, "postingUser");
            return (Criteria) this;
        }

        public Criteria andPostingUserGreaterThan(String value) {
            addCriterion("posting_user >", value, "postingUser");
            return (Criteria) this;
        }

        public Criteria andPostingUserGreaterThanOrEqualTo(String value) {
            addCriterion("posting_user >=", value, "postingUser");
            return (Criteria) this;
        }

        public Criteria andPostingUserLessThan(String value) {
            addCriterion("posting_user <", value, "postingUser");
            return (Criteria) this;
        }

        public Criteria andPostingUserLessThanOrEqualTo(String value) {
            addCriterion("posting_user <=", value, "postingUser");
            return (Criteria) this;
        }

        public Criteria andPostingUserLike(String value) {
            addCriterion("posting_user like", value, "postingUser");
            return (Criteria) this;
        }

        public Criteria andPostingUserNotLike(String value) {
            addCriterion("posting_user not like", value, "postingUser");
            return (Criteria) this;
        }

        public Criteria andPostingUserIn(List<String> values) {
            addCriterion("posting_user in", values, "postingUser");
            return (Criteria) this;
        }

        public Criteria andPostingUserNotIn(List<String> values) {
            addCriterion("posting_user not in", values, "postingUser");
            return (Criteria) this;
        }

        public Criteria andPostingUserBetween(String value1, String value2) {
            addCriterion("posting_user between", value1, value2, "postingUser");
            return (Criteria) this;
        }

        public Criteria andPostingUserNotBetween(String value1, String value2) {
            addCriterion("posting_user not between", value1, value2, "postingUser");
            return (Criteria) this;
        }

        public Criteria andTouchingUserIsNull() {
            addCriterion("touching_user is null");
            return (Criteria) this;
        }

        public Criteria andTouchingUserIsNotNull() {
            addCriterion("touching_user is not null");
            return (Criteria) this;
        }

        public Criteria andTouchingUserEqualTo(String value) {
            addCriterion("touching_user =", value, "touchingUser");
            return (Criteria) this;
        }

        public Criteria andTouchingUserNotEqualTo(String value) {
            addCriterion("touching_user <>", value, "touchingUser");
            return (Criteria) this;
        }

        public Criteria andTouchingUserGreaterThan(String value) {
            addCriterion("touching_user >", value, "touchingUser");
            return (Criteria) this;
        }

        public Criteria andTouchingUserGreaterThanOrEqualTo(String value) {
            addCriterion("touching_user >=", value, "touchingUser");
            return (Criteria) this;
        }

        public Criteria andTouchingUserLessThan(String value) {
            addCriterion("touching_user <", value, "touchingUser");
            return (Criteria) this;
        }

        public Criteria andTouchingUserLessThanOrEqualTo(String value) {
            addCriterion("touching_user <=", value, "touchingUser");
            return (Criteria) this;
        }

        public Criteria andTouchingUserLike(String value) {
            addCriterion("touching_user like", value, "touchingUser");
            return (Criteria) this;
        }

        public Criteria andTouchingUserNotLike(String value) {
            addCriterion("touching_user not like", value, "touchingUser");
            return (Criteria) this;
        }

        public Criteria andTouchingUserIn(List<String> values) {
            addCriterion("touching_user in", values, "touchingUser");
            return (Criteria) this;
        }

        public Criteria andTouchingUserNotIn(List<String> values) {
            addCriterion("touching_user not in", values, "touchingUser");
            return (Criteria) this;
        }

        public Criteria andTouchingUserBetween(String value1, String value2) {
            addCriterion("touching_user between", value1, value2, "touchingUser");
            return (Criteria) this;
        }

        public Criteria andTouchingUserNotBetween(String value1, String value2) {
            addCriterion("touching_user not between", value1, value2, "touchingUser");
            return (Criteria) this;
        }

        public Criteria andTypeNameIsNull() {
            addCriterion("type_name is null");
            return (Criteria) this;
        }

        public Criteria andTypeNameIsNotNull() {
            addCriterion("type_name is not null");
            return (Criteria) this;
        }

        public Criteria andTypeNameEqualTo(String value) {
            addCriterion("type_name =", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotEqualTo(String value) {
            addCriterion("type_name <>", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameGreaterThan(String value) {
            addCriterion("type_name >", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("type_name >=", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameLessThan(String value) {
            addCriterion("type_name <", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameLessThanOrEqualTo(String value) {
            addCriterion("type_name <=", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameLike(String value) {
            addCriterion("type_name like", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotLike(String value) {
            addCriterion("type_name not like", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameIn(List<String> values) {
            addCriterion("type_name in", values, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotIn(List<String> values) {
            addCriterion("type_name not in", values, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameBetween(String value1, String value2) {
            addCriterion("type_name between", value1, value2, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotBetween(String value1, String value2) {
            addCriterion("type_name not between", value1, value2, "typeName");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
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
     * This class corresponds to the database table voucher_template
     *
     * @mbggenerated do_not_delete_during_merge Wed Sep 30 15:25:10 CST 2015
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table voucher_template
     *
     * @mbggenerated Wed Sep 30 15:25:10 CST 2015
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