package cn.ahyc.yjz.model;

import java.io.Serializable;
import java.util.Date;

public class VoucherTemplate implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column voucher_template.id
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column voucher_template.voucher_word
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    private String voucherWord;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column voucher_template.bill_num
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    private Integer billNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column voucher_template.audit_user
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    private String auditUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column voucher_template.posting_user
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    private String postingUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column voucher_template.touching_user
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    private String touchingUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column voucher_template.type_name
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    private String typeName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column voucher_template.name
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column voucher_template.company_id
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    private String companyId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column voucher_template.create_time
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column voucher_template.modify_time
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    private Date modifyTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table voucher_template
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voucher_template
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    public VoucherTemplate(Long id, String voucherWord, Integer billNum, String auditUser, String postingUser, String touchingUser, String typeName, String name, String companyId, Date createTime, Date modifyTime) {
        this.id = id;
        this.voucherWord = voucherWord;
        this.billNum = billNum;
        this.auditUser = auditUser;
        this.postingUser = postingUser;
        this.touchingUser = touchingUser;
        this.typeName = typeName;
        this.name = name;
        this.companyId = companyId;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voucher_template
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    public VoucherTemplate() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column voucher_template.id
     *
     * @return the value of voucher_template.id
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column voucher_template.id
     *
     * @param id the value for voucher_template.id
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column voucher_template.voucher_word
     *
     * @return the value of voucher_template.voucher_word
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    public String getVoucherWord() {
        return voucherWord;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column voucher_template.voucher_word
     *
     * @param voucherWord the value for voucher_template.voucher_word
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    public void setVoucherWord(String voucherWord) {
        this.voucherWord = voucherWord == null ? null : voucherWord.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column voucher_template.bill_num
     *
     * @return the value of voucher_template.bill_num
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    public Integer getBillNum() {
        return billNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column voucher_template.bill_num
     *
     * @param billNum the value for voucher_template.bill_num
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    public void setBillNum(Integer billNum) {
        this.billNum = billNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column voucher_template.audit_user
     *
     * @return the value of voucher_template.audit_user
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    public String getAuditUser() {
        return auditUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column voucher_template.audit_user
     *
     * @param auditUser the value for voucher_template.audit_user
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    public void setAuditUser(String auditUser) {
        this.auditUser = auditUser == null ? null : auditUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column voucher_template.posting_user
     *
     * @return the value of voucher_template.posting_user
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    public String getPostingUser() {
        return postingUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column voucher_template.posting_user
     *
     * @param postingUser the value for voucher_template.posting_user
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    public void setPostingUser(String postingUser) {
        this.postingUser = postingUser == null ? null : postingUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column voucher_template.touching_user
     *
     * @return the value of voucher_template.touching_user
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    public String getTouchingUser() {
        return touchingUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column voucher_template.touching_user
     *
     * @param touchingUser the value for voucher_template.touching_user
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    public void setTouchingUser(String touchingUser) {
        this.touchingUser = touchingUser == null ? null : touchingUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column voucher_template.type_name
     *
     * @return the value of voucher_template.type_name
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column voucher_template.type_name
     *
     * @param typeName the value for voucher_template.type_name
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column voucher_template.name
     *
     * @return the value of voucher_template.name
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column voucher_template.name
     *
     * @param name the value for voucher_template.name
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column voucher_template.company_id
     *
     * @return the value of voucher_template.company_id
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    public String getCompanyId() {
        return companyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column voucher_template.company_id
     *
     * @param companyId the value for voucher_template.company_id
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column voucher_template.create_time
     *
     * @return the value of voucher_template.create_time
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column voucher_template.create_time
     *
     * @param createTime the value for voucher_template.create_time
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column voucher_template.modify_time
     *
     * @return the value of voucher_template.modify_time
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column voucher_template.modify_time
     *
     * @param modifyTime the value for voucher_template.modify_time
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}