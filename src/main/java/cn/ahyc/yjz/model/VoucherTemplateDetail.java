package cn.ahyc.yjz.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class VoucherTemplateDetail implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column voucher_template_detail.id
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column voucher_template_detail.summary
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    private String summary;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column voucher_template_detail.subject_code
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    private Integer subjectCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column voucher_template_detail.debit
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    private BigDecimal debit;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column voucher_template_detail.credit
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    private BigDecimal credit;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column voucher_template_detail.template_id
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    private Long templateId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column voucher_template_detail.create_time
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column voucher_template_detail.modify_time
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    private Date modifyTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table voucher_template_detail
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voucher_template_detail
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    public VoucherTemplateDetail(Long id, String summary, Integer subjectCode, BigDecimal debit, BigDecimal credit, Long templateId, Date createTime, Date modifyTime) {
        this.id = id;
        this.summary = summary;
        this.subjectCode = subjectCode;
        this.debit = debit;
        this.credit = credit;
        this.templateId = templateId;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voucher_template_detail
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    public VoucherTemplateDetail() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column voucher_template_detail.id
     *
     * @return the value of voucher_template_detail.id
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column voucher_template_detail.id
     *
     * @param id the value for voucher_template_detail.id
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column voucher_template_detail.summary
     *
     * @return the value of voucher_template_detail.summary
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    public String getSummary() {
        return summary;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column voucher_template_detail.summary
     *
     * @param summary the value for voucher_template_detail.summary
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column voucher_template_detail.subject_code
     *
     * @return the value of voucher_template_detail.subject_code
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    public Integer getSubjectCode() {
        return subjectCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column voucher_template_detail.subject_code
     *
     * @param subjectCode the value for voucher_template_detail.subject_code
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    public void setSubjectCode(Integer subjectCode) {
        this.subjectCode = subjectCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column voucher_template_detail.debit
     *
     * @return the value of voucher_template_detail.debit
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    public BigDecimal getDebit() {
        return debit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column voucher_template_detail.debit
     *
     * @param debit the value for voucher_template_detail.debit
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    public void setDebit(BigDecimal debit) {
        this.debit = debit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column voucher_template_detail.credit
     *
     * @return the value of voucher_template_detail.credit
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    public BigDecimal getCredit() {
        return credit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column voucher_template_detail.credit
     *
     * @param credit the value for voucher_template_detail.credit
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column voucher_template_detail.template_id
     *
     * @return the value of voucher_template_detail.template_id
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    public Long getTemplateId() {
        return templateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column voucher_template_detail.template_id
     *
     * @param templateId the value for voucher_template_detail.template_id
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column voucher_template_detail.create_time
     *
     * @return the value of voucher_template_detail.create_time
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column voucher_template_detail.create_time
     *
     * @param createTime the value for voucher_template_detail.create_time
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column voucher_template_detail.modify_time
     *
     * @return the value of voucher_template_detail.modify_time
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column voucher_template_detail.modify_time
     *
     * @param modifyTime the value for voucher_template_detail.modify_time
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}