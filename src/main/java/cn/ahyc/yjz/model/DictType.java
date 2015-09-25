package cn.ahyc.yjz.model;

import java.io.Serializable;
import java.util.Date;

public class DictType implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dict_type.id
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dict_type.type_code
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    private String typeCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dict_type.type_name
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    private String typeName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dict_type.create_time
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dict_type.modify_time
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    private Date modifyTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table dict_type
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dict_type
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    public DictType(Long id, String typeCode, String typeName, Date createTime, Date modifyTime) {
        this.id = id;
        this.typeCode = typeCode;
        this.typeName = typeName;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dict_type
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    public DictType() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dict_type.id
     *
     * @return the value of dict_type.id
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dict_type.id
     *
     * @param id the value for dict_type.id
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dict_type.type_code
     *
     * @return the value of dict_type.type_code
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    public String getTypeCode() {
        return typeCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dict_type.type_code
     *
     * @param typeCode the value for dict_type.type_code
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode == null ? null : typeCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dict_type.type_name
     *
     * @return the value of dict_type.type_name
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dict_type.type_name
     *
     * @param typeName the value for dict_type.type_name
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dict_type.create_time
     *
     * @return the value of dict_type.create_time
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dict_type.create_time
     *
     * @param createTime the value for dict_type.create_time
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dict_type.modify_time
     *
     * @return the value of dict_type.modify_time
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dict_type.modify_time
     *
     * @param modifyTime the value for dict_type.modify_time
     *
     * @mbggenerated Wed Sep 23 16:59:46 CST 2015
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}