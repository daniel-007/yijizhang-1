package cn.ahyc.yjz.mapper.extend;

import cn.ahyc.yjz.dto.ReportRow;
import cn.ahyc.yjz.mapper.base.CashFlowMapper;

import java.util.List;
import java.util.Map;

/**
 * Created by Joey Yan on 15-11-3.
 */
public interface CashFlowExtendMapper extends CashFlowMapper {

    /**
     * 关联现金流转表和数据.
     *
     * @param param
     * @return
     */
    List<Map> cashFlowsByPeriod(Map param);

    /**
     * 查询现金流量公式
     * @return
     */
    List<ReportRow> selectCashFlowExpressionColumn();
}
