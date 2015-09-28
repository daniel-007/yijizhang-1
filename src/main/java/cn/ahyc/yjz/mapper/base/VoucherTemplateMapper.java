package cn.ahyc.yjz.mapper.base;

import cn.ahyc.yjz.model.VoucherTemplate;
import cn.ahyc.yjz.model.VoucherTemplateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VoucherTemplateMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voucher_template
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    int countByExample(VoucherTemplateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voucher_template
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    int deleteByExample(VoucherTemplateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voucher_template
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voucher_template
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    int insert(VoucherTemplate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voucher_template
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    int insertSelective(VoucherTemplate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voucher_template
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    List<VoucherTemplate> selectByExample(VoucherTemplateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voucher_template
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    VoucherTemplate selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voucher_template
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    int updateByExampleSelective(@Param("record") VoucherTemplate record, @Param("example") VoucherTemplateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voucher_template
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    int updateByExample(@Param("record") VoucherTemplate record, @Param("example") VoucherTemplateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voucher_template
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    int updateByPrimaryKeySelective(VoucherTemplate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voucher_template
     *
     * @mbggenerated Mon Sep 28 15:49:31 CST 2015
     */
    int updateByPrimaryKey(VoucherTemplate record);
}