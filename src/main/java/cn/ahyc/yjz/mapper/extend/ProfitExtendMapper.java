package cn.ahyc.yjz.mapper.extend;

import java.util.List;

import cn.ahyc.yjz.dto.ReportRow;
import cn.ahyc.yjz.mapper.base.ProfitMapper;

/**
 * @ClassName: ProfitExtendMapper
 * @Description: TODO
 * @author chengjiarui 1256064203@qq.com
 * @date 2015年10月27日 下午7:14:33
 * 
 */
public interface ProfitExtendMapper extends ProfitMapper {

    /**
     * 查询利润表
     * 
     * @return
     */
    List<ReportRow> selectProfitExpressionColumn();

}