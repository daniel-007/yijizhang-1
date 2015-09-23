package cn.ahyc.yjz.model;

import java.io.Serializable;
import java.util.Date;

public class CompanyCommonValue implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column company_common_value.id
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column company_common_value.type_id
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    private Long typeId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column company_common_value.value
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    private String value;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column company_common_value.show_value
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    private String showValue;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column company_common_value.company_id
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    private String companyId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column company_common_value.create_time
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column company_common_value.modify_time
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    private Date modifyTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table company_common_value
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table company_common_value
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    public CompanyCommonValue(Long id, Long typeId, String value, String showValue, String companyId, Date createTime, Date modifyTime) {
        this.id = id;
        this.typeId = typeId;
        this.value = value;
        this.showValue = showValue;
        this.companyId = companyId;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table company_common_value
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    public CompanyCommonValue() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column company_common_value.id
     *
     * @return the value of company_common_value.id
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column company_common_value.id
     *
     * @param id the value for company_common_value.id
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column company_common_value.type_id
     *
     * @return the value of company_common_value.type_id
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    public Long getTypeId() {
        return typeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column company_common_value.type_id
     *
     * @param typeId the value for company_common_value.type_id
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column company_common_value.value
     *
     * @return the value of company_common_value.value
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    public String getValue() {
        return value;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column company_common_value.value
     *
     * @param value the value for company_common_value.value
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column company_common_value.show_value
     *
     * @return the value of company_common_value.show_value
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    public String getShowValue() {
        return showValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column company_common_value.show_value
     *
     * @param showValue the value for company_common_value.show_value
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    public void setShowValue(String showValue) {
        this.showValue = showValue == null ? null : showValue.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column company_common_value.company_id
     *
     * @return the value of company_common_value.company_id
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    public String getCompanyId() {
        return companyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column company_common_value.company_id
     *
     * @param companyId the value for company_common_value.company_id
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column company_common_value.create_time
     *
     * @return the value of company_common_value.create_time
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column company_common_value.create_time
     *
     * @param createTime the value for company_common_value.create_time
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column company_common_value.modify_time
     *
     * @return the value of company_common_value.modify_time
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column company_common_value.modify_time
     *
     * @param modifyTime the value for company_common_value.modify_time
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}