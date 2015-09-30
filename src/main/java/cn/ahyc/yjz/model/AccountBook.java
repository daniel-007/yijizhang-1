package cn.ahyc.yjz.model;

import java.io.Serializable;
import java.util.Date;

public class AccountBook implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_book.id
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_book.book_name
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    private String bookName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_book.money_code
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    private String moneyCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_book.money_name
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    private String moneyName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_book.start_time
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    private Date startTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_book.init_year
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    private Integer initYear;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_book.init_period
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    private Integer initPeriod;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_book.company_name
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    private String companyName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_book.company_id
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    private String companyId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_book.dict_value_id
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    private Integer dictValueId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_book.over_flag
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    private Integer overFlag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_book.create_time
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_book.modify_time
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    private Date modifyTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table account_book
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_book
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    public AccountBook(Long id, String bookName, String moneyCode, String moneyName, Date startTime, Integer initYear, Integer initPeriod, String companyName, String companyId, Integer dictValueId, Integer overFlag, Date createTime, Date modifyTime) {
        this.id = id;
        this.bookName = bookName;
        this.moneyCode = moneyCode;
        this.moneyName = moneyName;
        this.startTime = startTime;
        this.initYear = initYear;
        this.initPeriod = initPeriod;
        this.companyName = companyName;
        this.companyId = companyId;
        this.dictValueId = dictValueId;
        this.overFlag = overFlag;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_book
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    public AccountBook() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_book.id
     *
     * @return the value of account_book.id
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account_book.id
     *
     * @param id the value for account_book.id
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_book.book_name
     *
     * @return the value of account_book.book_name
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    public String getBookName() {
        return bookName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account_book.book_name
     *
     * @param bookName the value for account_book.book_name
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    public void setBookName(String bookName) {
        this.bookName = bookName == null ? null : bookName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_book.money_code
     *
     * @return the value of account_book.money_code
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    public String getMoneyCode() {
        return moneyCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account_book.money_code
     *
     * @param moneyCode the value for account_book.money_code
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    public void setMoneyCode(String moneyCode) {
        this.moneyCode = moneyCode == null ? null : moneyCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_book.money_name
     *
     * @return the value of account_book.money_name
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    public String getMoneyName() {
        return moneyName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account_book.money_name
     *
     * @param moneyName the value for account_book.money_name
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    public void setMoneyName(String moneyName) {
        this.moneyName = moneyName == null ? null : moneyName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_book.start_time
     *
     * @return the value of account_book.start_time
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account_book.start_time
     *
     * @param startTime the value for account_book.start_time
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_book.init_year
     *
     * @return the value of account_book.init_year
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    public Integer getInitYear() {
        return initYear;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account_book.init_year
     *
     * @param initYear the value for account_book.init_year
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    public void setInitYear(Integer initYear) {
        this.initYear = initYear;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_book.init_period
     *
     * @return the value of account_book.init_period
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    public Integer getInitPeriod() {
        return initPeriod;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account_book.init_period
     *
     * @param initPeriod the value for account_book.init_period
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    public void setInitPeriod(Integer initPeriod) {
        this.initPeriod = initPeriod;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_book.company_name
     *
     * @return the value of account_book.company_name
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account_book.company_name
     *
     * @param companyName the value for account_book.company_name
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_book.company_id
     *
     * @return the value of account_book.company_id
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    public String getCompanyId() {
        return companyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account_book.company_id
     *
     * @param companyId the value for account_book.company_id
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_book.dict_value_id
     *
     * @return the value of account_book.dict_value_id
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    public Integer getDictValueId() {
        return dictValueId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account_book.dict_value_id
     *
     * @param dictValueId the value for account_book.dict_value_id
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    public void setDictValueId(Integer dictValueId) {
        this.dictValueId = dictValueId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_book.over_flag
     *
     * @return the value of account_book.over_flag
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    public Integer getOverFlag() {
        return overFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account_book.over_flag
     *
     * @param overFlag the value for account_book.over_flag
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    public void setOverFlag(Integer overFlag) {
        this.overFlag = overFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_book.create_time
     *
     * @return the value of account_book.create_time
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account_book.create_time
     *
     * @param createTime the value for account_book.create_time
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_book.modify_time
     *
     * @return the value of account_book.modify_time
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account_book.modify_time
     *
     * @param modifyTime the value for account_book.modify_time
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}