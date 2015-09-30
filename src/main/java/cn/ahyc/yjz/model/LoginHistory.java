package cn.ahyc.yjz.model;

import java.io.Serializable;
import java.util.Date;

public class LoginHistory implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column login_history.id
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column login_history.username
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column login_history.login_ip
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    private String loginIp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column login_history.login_time
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    private Date loginTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column login_history.login_times
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    private Long loginTimes;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column login_history.account_book_id
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    private Long accountBookId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column login_history.login_result
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    private Boolean loginResult;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table login_history
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login_history
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    public LoginHistory(Long id, String username, String loginIp, Date loginTime, Long loginTimes, Long accountBookId, Boolean loginResult) {
        this.id = id;
        this.username = username;
        this.loginIp = loginIp;
        this.loginTime = loginTime;
        this.loginTimes = loginTimes;
        this.accountBookId = accountBookId;
        this.loginResult = loginResult;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login_history
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    public LoginHistory() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column login_history.id
     *
     * @return the value of login_history.id
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column login_history.id
     *
     * @param id the value for login_history.id
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column login_history.username
     *
     * @return the value of login_history.username
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column login_history.username
     *
     * @param username the value for login_history.username
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column login_history.login_ip
     *
     * @return the value of login_history.login_ip
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    public String getLoginIp() {
        return loginIp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column login_history.login_ip
     *
     * @param loginIp the value for login_history.login_ip
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp == null ? null : loginIp.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column login_history.login_time
     *
     * @return the value of login_history.login_time
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column login_history.login_time
     *
     * @param loginTime the value for login_history.login_time
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column login_history.login_times
     *
     * @return the value of login_history.login_times
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    public Long getLoginTimes() {
        return loginTimes;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column login_history.login_times
     *
     * @param loginTimes the value for login_history.login_times
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    public void setLoginTimes(Long loginTimes) {
        this.loginTimes = loginTimes;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column login_history.account_book_id
     *
     * @return the value of login_history.account_book_id
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    public Long getAccountBookId() {
        return accountBookId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column login_history.account_book_id
     *
     * @param accountBookId the value for login_history.account_book_id
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    public void setAccountBookId(Long accountBookId) {
        this.accountBookId = accountBookId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column login_history.login_result
     *
     * @return the value of login_history.login_result
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    public Boolean getLoginResult() {
        return loginResult;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column login_history.login_result
     *
     * @param loginResult the value for login_history.login_result
     *
     * @mbggenerated Wed Sep 30 16:20:19 CST 2015
     */
    public void setLoginResult(Boolean loginResult) {
        this.loginResult = loginResult;
    }
}