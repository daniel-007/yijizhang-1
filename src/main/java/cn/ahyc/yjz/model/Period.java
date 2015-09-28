package cn.ahyc.yjz.model;

import java.io.Serializable;
import java.util.Date;

public class Period implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column period.id
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column period.start_time
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    private Date startTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column period.end_time
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    private Date endTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column period.current_period
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    private Integer currentPeriod;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column period.flag
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    private Integer flag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column period.book_id
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    private Long bookId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column period.company_id
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    private String companyId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column period.end_flag
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    private Integer endFlag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column period.create_time
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column period.modify_time
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    private Date modifyTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table period
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table period
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    public Period(Long id, Date startTime, Date endTime, Integer currentPeriod, Integer flag, Long bookId, String companyId, Integer endFlag, Date createTime, Date modifyTime) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.currentPeriod = currentPeriod;
        this.flag = flag;
        this.bookId = bookId;
        this.companyId = companyId;
        this.endFlag = endFlag;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table period
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    public Period() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column period.id
     *
     * @return the value of period.id
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column period.id
     *
     * @param id the value for period.id
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column period.start_time
     *
     * @return the value of period.start_time
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column period.start_time
     *
     * @param startTime the value for period.start_time
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column period.end_time
     *
     * @return the value of period.end_time
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column period.end_time
     *
     * @param endTime the value for period.end_time
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column period.current_period
     *
     * @return the value of period.current_period
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    public Integer getCurrentPeriod() {
        return currentPeriod;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column period.current_period
     *
     * @param currentPeriod the value for period.current_period
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    public void setCurrentPeriod(Integer currentPeriod) {
        this.currentPeriod = currentPeriod;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column period.flag
     *
     * @return the value of period.flag
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    public Integer getFlag() {
        return flag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column period.flag
     *
     * @param flag the value for period.flag
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column period.book_id
     *
     * @return the value of period.book_id
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    public Long getBookId() {
        return bookId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column period.book_id
     *
     * @param bookId the value for period.book_id
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column period.company_id
     *
     * @return the value of period.company_id
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    public String getCompanyId() {
        return companyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column period.company_id
     *
     * @param companyId the value for period.company_id
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column period.end_flag
     *
     * @return the value of period.end_flag
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    public Integer getEndFlag() {
        return endFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column period.end_flag
     *
     * @param endFlag the value for period.end_flag
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    public void setEndFlag(Integer endFlag) {
        this.endFlag = endFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column period.create_time
     *
     * @return the value of period.create_time
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column period.create_time
     *
     * @param createTime the value for period.create_time
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column period.modify_time
     *
     * @return the value of period.modify_time
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column period.modify_time
     *
     * @param modifyTime the value for period.modify_time
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}