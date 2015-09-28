package cn.ahyc.yjz.model;

import java.io.Serializable;
import java.util.Date;

public class Voucher implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column voucher.id
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column voucher.voucher_word
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    private String voucherWord;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column voucher.voucher_no
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    private Integer voucherNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column voucher.voucher_time
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    private Date voucherTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column voucher.bill_num
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    private Integer billNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column voucher.audit_user
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    private String auditUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column voucher.posting_user
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    private String postingUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column voucher.touching_user
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    private String touchingUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column voucher.period_id
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    private Long periodId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column voucher.company_id
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    private String companyId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column voucher.create_time
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column voucher.modify_time
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    private Date modifyTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table voucher
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voucher
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    public Voucher(Long id, String voucherWord, Integer voucherNo, Date voucherTime, Integer billNum, String auditUser, String postingUser, String touchingUser, Long periodId, String companyId, Date createTime, Date modifyTime) {
        this.id = id;
        this.voucherWord = voucherWord;
        this.voucherNo = voucherNo;
        this.voucherTime = voucherTime;
        this.billNum = billNum;
        this.auditUser = auditUser;
        this.postingUser = postingUser;
        this.touchingUser = touchingUser;
        this.periodId = periodId;
        this.companyId = companyId;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voucher
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    public Voucher() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column voucher.id
     *
     * @return the value of voucher.id
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column voucher.id
     *
     * @param id the value for voucher.id
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column voucher.voucher_word
     *
     * @return the value of voucher.voucher_word
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    public String getVoucherWord() {
        return voucherWord;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column voucher.voucher_word
     *
     * @param voucherWord the value for voucher.voucher_word
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    public void setVoucherWord(String voucherWord) {
        this.voucherWord = voucherWord == null ? null : voucherWord.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column voucher.voucher_no
     *
     * @return the value of voucher.voucher_no
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    public Integer getVoucherNo() {
        return voucherNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column voucher.voucher_no
     *
     * @param voucherNo the value for voucher.voucher_no
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    public void setVoucherNo(Integer voucherNo) {
        this.voucherNo = voucherNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column voucher.voucher_time
     *
     * @return the value of voucher.voucher_time
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    public Date getVoucherTime() {
        return voucherTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column voucher.voucher_time
     *
     * @param voucherTime the value for voucher.voucher_time
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    public void setVoucherTime(Date voucherTime) {
        this.voucherTime = voucherTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column voucher.bill_num
     *
     * @return the value of voucher.bill_num
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    public Integer getBillNum() {
        return billNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column voucher.bill_num
     *
     * @param billNum the value for voucher.bill_num
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    public void setBillNum(Integer billNum) {
        this.billNum = billNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column voucher.audit_user
     *
     * @return the value of voucher.audit_user
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    public String getAuditUser() {
        return auditUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column voucher.audit_user
     *
     * @param auditUser the value for voucher.audit_user
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    public void setAuditUser(String auditUser) {
        this.auditUser = auditUser == null ? null : auditUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column voucher.posting_user
     *
     * @return the value of voucher.posting_user
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    public String getPostingUser() {
        return postingUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column voucher.posting_user
     *
     * @param postingUser the value for voucher.posting_user
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    public void setPostingUser(String postingUser) {
        this.postingUser = postingUser == null ? null : postingUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column voucher.touching_user
     *
     * @return the value of voucher.touching_user
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    public String getTouchingUser() {
        return touchingUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column voucher.touching_user
     *
     * @param touchingUser the value for voucher.touching_user
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    public void setTouchingUser(String touchingUser) {
        this.touchingUser = touchingUser == null ? null : touchingUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column voucher.period_id
     *
     * @return the value of voucher.period_id
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    public Long getPeriodId() {
        return periodId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column voucher.period_id
     *
     * @param periodId the value for voucher.period_id
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    public void setPeriodId(Long periodId) {
        this.periodId = periodId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column voucher.company_id
     *
     * @return the value of voucher.company_id
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    public String getCompanyId() {
        return companyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column voucher.company_id
     *
     * @param companyId the value for voucher.company_id
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column voucher.create_time
     *
     * @return the value of voucher.create_time
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column voucher.create_time
     *
     * @param createTime the value for voucher.create_time
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column voucher.modify_time
     *
     * @return the value of voucher.modify_time
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column voucher.modify_time
     *
     * @param modifyTime the value for voucher.modify_time
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}