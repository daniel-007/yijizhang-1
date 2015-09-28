package cn.ahyc.yjz.mapper.base;

import cn.ahyc.yjz.model.AccountSubjectTemplate;
import cn.ahyc.yjz.model.AccountSubjectTemplateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountSubjectTemplateMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_subject_template
     *
     * @mbggenerated Mon Sep 28 10:26:40 CST 2015
     */
    int countByExample(AccountSubjectTemplateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_subject_template
     *
     * @mbggenerated Mon Sep 28 10:26:40 CST 2015
     */
    int deleteByExample(AccountSubjectTemplateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_subject_template
     *
     * @mbggenerated Mon Sep 28 10:26:40 CST 2015
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_subject_template
     *
     * @mbggenerated Mon Sep 28 10:26:40 CST 2015
     */
    int insert(AccountSubjectTemplate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_subject_template
     *
     * @mbggenerated Mon Sep 28 10:26:40 CST 2015
     */
    int insertSelective(AccountSubjectTemplate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_subject_template
     *
     * @mbggenerated Mon Sep 28 10:26:40 CST 2015
     */
    List<AccountSubjectTemplate> selectByExample(AccountSubjectTemplateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_subject_template
     *
     * @mbggenerated Mon Sep 28 10:26:40 CST 2015
     */
    AccountSubjectTemplate selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_subject_template
     *
     * @mbggenerated Mon Sep 28 10:26:40 CST 2015
     */
    int updateByExampleSelective(@Param("record") AccountSubjectTemplate record, @Param("example") AccountSubjectTemplateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_subject_template
     *
     * @mbggenerated Mon Sep 28 10:26:40 CST 2015
     */
    int updateByExample(@Param("record") AccountSubjectTemplate record, @Param("example") AccountSubjectTemplateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_subject_template
     *
     * @mbggenerated Mon Sep 28 10:26:40 CST 2015
     */
    int updateByPrimaryKeySelective(AccountSubjectTemplate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_subject_template
     *
     * @mbggenerated Mon Sep 28 10:26:40 CST 2015
     */
    int updateByPrimaryKey(AccountSubjectTemplate record);
}