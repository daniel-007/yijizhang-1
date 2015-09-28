package cn.ahyc.yjz.mapper.extend;

import cn.ahyc.yjz.mapper.base.PeriodMapper;
import cn.ahyc.yjz.model.Period;
import cn.ahyc.yjz.model.PeriodExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PeriodExtendMapper extends PeriodMapper{

    /**
     * 当然账套ID所对应的当前期.
     * @param bookId
     * @return
     */
    Period selectCurrentPeriod(Long bookId);

}