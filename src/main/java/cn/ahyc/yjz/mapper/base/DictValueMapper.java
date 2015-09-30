package cn.ahyc.yjz.mapper.base;

import cn.ahyc.yjz.model.DictValue;
import cn.ahyc.yjz.model.DictValueExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DictValueMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dict_value
     *
     * @mbggenerated Wed Sep 30 15:25:10 CST 2015
     */
    int countByExample(DictValueExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dict_value
     *
     * @mbggenerated Wed Sep 30 15:25:10 CST 2015
     */
    int deleteByExample(DictValueExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dict_value
     *
     * @mbggenerated Wed Sep 30 15:25:10 CST 2015
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dict_value
     *
     * @mbggenerated Wed Sep 30 15:25:10 CST 2015
     */
    int insert(DictValue record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dict_value
     *
     * @mbggenerated Wed Sep 30 15:25:10 CST 2015
     */
    int insertSelective(DictValue record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dict_value
     *
     * @mbggenerated Wed Sep 30 15:25:10 CST 2015
     */
    List<DictValue> selectByExample(DictValueExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dict_value
     *
     * @mbggenerated Wed Sep 30 15:25:10 CST 2015
     */
    DictValue selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dict_value
     *
     * @mbggenerated Wed Sep 30 15:25:10 CST 2015
     */
    int updateByExampleSelective(@Param("record") DictValue record, @Param("example") DictValueExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dict_value
     *
     * @mbggenerated Wed Sep 30 15:25:10 CST 2015
     */
    int updateByExample(@Param("record") DictValue record, @Param("example") DictValueExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dict_value
     *
     * @mbggenerated Wed Sep 30 15:25:10 CST 2015
     */
    int updateByPrimaryKeySelective(DictValue record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dict_value
     *
     * @mbggenerated Wed Sep 30 15:25:10 CST 2015
     */
    int updateByPrimaryKey(DictValue record);
}