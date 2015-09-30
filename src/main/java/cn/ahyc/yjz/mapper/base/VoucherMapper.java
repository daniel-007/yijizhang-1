package cn.ahyc.yjz.mapper.base;

import cn.ahyc.yjz.model.Voucher;
import cn.ahyc.yjz.model.VoucherExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VoucherMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voucher
     *
     * @mbggenerated Wed Sep 30 15:25:10 CST 2015
     */
    int countByExample(VoucherExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voucher
     *
     * @mbggenerated Wed Sep 30 15:25:10 CST 2015
     */
    int deleteByExample(VoucherExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voucher
     *
     * @mbggenerated Wed Sep 30 15:25:10 CST 2015
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voucher
     *
     * @mbggenerated Wed Sep 30 15:25:10 CST 2015
     */
    int insert(Voucher record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voucher
     *
     * @mbggenerated Wed Sep 30 15:25:10 CST 2015
     */
    int insertSelective(Voucher record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voucher
     *
     * @mbggenerated Wed Sep 30 15:25:10 CST 2015
     */
    List<Voucher> selectByExample(VoucherExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voucher
     *
     * @mbggenerated Wed Sep 30 15:25:10 CST 2015
     */
    Voucher selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voucher
     *
     * @mbggenerated Wed Sep 30 15:25:10 CST 2015
     */
    int updateByExampleSelective(@Param("record") Voucher record, @Param("example") VoucherExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voucher
     *
     * @mbggenerated Wed Sep 30 15:25:10 CST 2015
     */
    int updateByExample(@Param("record") Voucher record, @Param("example") VoucherExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voucher
     *
     * @mbggenerated Wed Sep 30 15:25:10 CST 2015
     */
    int updateByPrimaryKeySelective(Voucher record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table voucher
     *
     * @mbggenerated Wed Sep 30 15:25:10 CST 2015
     */
    int updateByPrimaryKey(Voucher record);
}