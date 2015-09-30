package cn.ahyc.yjz.mapper.base;

import cn.ahyc.yjz.model.VoucherTemplateDetail;
import cn.ahyc.yjz.model.VoucherTemplateDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VoucherTemplateDetailMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voucher_template_detail
     *
     * @mbggenerated Wed Sep 30 15:25:10 CST 2015
     */
    int countByExample(VoucherTemplateDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voucher_template_detail
     *
     * @mbggenerated Wed Sep 30 15:25:10 CST 2015
     */
    int deleteByExample(VoucherTemplateDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voucher_template_detail
     *
     * @mbggenerated Wed Sep 30 15:25:10 CST 2015
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voucher_template_detail
     *
     * @mbggenerated Wed Sep 30 15:25:10 CST 2015
     */
    int insert(VoucherTemplateDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voucher_template_detail
     *
     * @mbggenerated Wed Sep 30 15:25:10 CST 2015
     */
    int insertSelective(VoucherTemplateDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voucher_template_detail
     *
     * @mbggenerated Wed Sep 30 15:25:10 CST 2015
     */
    List<VoucherTemplateDetail> selectByExample(VoucherTemplateDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voucher_template_detail
     *
     * @mbggenerated Wed Sep 30 15:25:10 CST 2015
     */
    VoucherTemplateDetail selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voucher_template_detail
     *
     * @mbggenerated Wed Sep 30 15:25:10 CST 2015
     */
    int updateByExampleSelective(@Param("record") VoucherTemplateDetail record, @Param("example") VoucherTemplateDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voucher_template_detail
     *
     * @mbggenerated Wed Sep 30 15:25:10 CST 2015
     */
    int updateByExample(@Param("record") VoucherTemplateDetail record, @Param("example") VoucherTemplateDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voucher_template_detail
     *
     * @mbggenerated Wed Sep 30 15:25:10 CST 2015
     */
    int updateByPrimaryKeySelective(VoucherTemplateDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voucher_template_detail
     *
     * @mbggenerated Wed Sep 30 15:25:10 CST 2015
     */
    int updateByPrimaryKey(VoucherTemplateDetail record);
}