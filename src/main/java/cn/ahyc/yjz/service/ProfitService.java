package cn.ahyc.yjz.service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ProfitService
 * @Description: TODO
 * @author chengjiarui 1256064203@qq.com
 * @date 2015年10月23日 下午3:41:29
 * 
 */
public interface ProfitService {

    /**
     * 查询统计数据列表
     * 
     * @param currentPeriod
     * @param bookId
     * @return
     */
    List<Map<String, Object>> getList(Integer currentPeriod, Long bookId);

    /**
     * 公式计算
     * 
     * @param currentPeriod
     * @param bookId
     * @param expStr
     */
    Object countExp(Integer currentPeriod, Long bookId, String expStr);

}
