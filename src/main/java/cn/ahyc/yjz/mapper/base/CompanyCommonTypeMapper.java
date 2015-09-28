package cn.ahyc.yjz.mapper.base;

import cn.ahyc.yjz.model.CompanyCommonType;
import cn.ahyc.yjz.model.CompanyCommonTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CompanyCommonTypeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table company_common_type
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    int countByExample(CompanyCommonTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table company_common_type
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    int deleteByExample(CompanyCommonTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table company_common_type
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table company_common_type
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    int insert(CompanyCommonType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table company_common_type
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    int insertSelective(CompanyCommonType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table company_common_type
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    List<CompanyCommonType> selectByExample(CompanyCommonTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table company_common_type
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    CompanyCommonType selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table company_common_type
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    int updateByExampleSelective(@Param("record") CompanyCommonType record, @Param("example") CompanyCommonTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table company_common_type
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    int updateByExample(@Param("record") CompanyCommonType record, @Param("example") CompanyCommonTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table company_common_type
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    int updateByPrimaryKeySelective(CompanyCommonType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table company_common_type
     *
     * @mbggenerated Mon Sep 28 13:58:17 CST 2015
     */
    int updateByPrimaryKey(CompanyCommonType record);
}