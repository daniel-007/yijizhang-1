package cn.ahyc.yjz.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CashFlow implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cash_flow.id
     *
     * @mbggenerated Wed Oct 28 17:00:31 CST 2015
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cash_flow.cash_code
     *
     * @mbggenerated Wed Oct 28 17:00:31 CST 2015
     */
    private String cashCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cash_flow.name
     *
     * @mbggenerated Wed Oct 28 17:00:31 CST 2015
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cash_flow.row_num
     *
     * @mbggenerated Wed Oct 28 17:00:31 CST 2015
     */
    private Integer rowNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cash_flow.money
     *
     * @mbggenerated Wed Oct 28 17:00:31 CST 2015
     */
    private BigDecimal money;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cash_flow.create_time
     *
     * @mbggenerated Wed Oct 28 17:00:31 CST 2015
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cash_flow.modify_time
     *
     * @mbggenerated Wed Oct 28 17:00:31 CST 2015
     */
    private Date modifyTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table cash_flow
     *
     * @mbggenerated Wed Oct 28 17:00:31 CST 2015
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cash_flow
     *
     * @mbggenerated Wed Oct 28 17:00:31 CST 2015
     */
    public CashFlow(Long id, String cashCode, String name, Integer rowNum, BigDecimal money, Date createTime, Date modifyTime) {
        this.id = id;
        this.cashCode = cashCode;
        this.name = name;
        this.rowNum = rowNum;
        this.money = money;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cash_flow
     *
     * @mbggenerated Wed Oct 28 17:00:31 CST 2015
     */
    public CashFlow() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cash_flow.id
     *
     * @return the value of cash_flow.id
     *
     * @mbggenerated Wed Oct 28 17:00:31 CST 2015
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cash_flow.id
     *
     * @param id the value for cash_flow.id
     *
     * @mbggenerated Wed Oct 28 17:00:31 CST 2015
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cash_flow.cash_code
     *
     * @return the value of cash_flow.cash_code
     *
     * @mbggenerated Wed Oct 28 17:00:31 CST 2015
     */
    public String getCashCode() {
        return cashCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cash_flow.cash_code
     *
     * @param cashCode the value for cash_flow.cash_code
     *
     * @mbggenerated Wed Oct 28 17:00:31 CST 2015
     */
    public void setCashCode(String cashCode) {
        this.cashCode = cashCode == null ? null : cashCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cash_flow.name
     *
     * @return the value of cash_flow.name
     *
     * @mbggenerated Wed Oct 28 17:00:31 CST 2015
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cash_flow.name
     *
     * @param name the value for cash_flow.name
     *
     * @mbggenerated Wed Oct 28 17:00:31 CST 2015
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cash_flow.row_num
     *
     * @return the value of cash_flow.row_num
     *
     * @mbggenerated Wed Oct 28 17:00:31 CST 2015
     */
    public Integer getRowNum() {
        return rowNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cash_flow.row_num
     *
     * @param rowNum the value for cash_flow.row_num
     *
     * @mbggenerated Wed Oct 28 17:00:31 CST 2015
     */
    public void setRowNum(Integer rowNum) {
        this.rowNum = rowNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cash_flow.money
     *
     * @return the value of cash_flow.money
     *
     * @mbggenerated Wed Oct 28 17:00:31 CST 2015
     */
    public BigDecimal getMoney() {
        return money;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cash_flow.money
     *
     * @param money the value for cash_flow.money
     *
     * @mbggenerated Wed Oct 28 17:00:31 CST 2015
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cash_flow.create_time
     *
     * @return the value of cash_flow.create_time
     *
     * @mbggenerated Wed Oct 28 17:00:31 CST 2015
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cash_flow.create_time
     *
     * @param createTime the value for cash_flow.create_time
     *
     * @mbggenerated Wed Oct 28 17:00:31 CST 2015
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cash_flow.modify_time
     *
     * @return the value of cash_flow.modify_time
     *
     * @mbggenerated Wed Oct 28 17:00:31 CST 2015
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cash_flow.modify_time
     *
     * @param modifyTime the value for cash_flow.modify_time
     *
     * @mbggenerated Wed Oct 28 17:00:31 CST 2015
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}