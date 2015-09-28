package cn.ahyc.yjz.mapper.extend;
/**
 * AccountBookExtendMapper
 *
 * @author:sanlai_lee@qq.com
 * @date: 15/9/26
 */

import cn.ahyc.yjz.mapper.base.AccountBookMapper;
import cn.ahyc.yjz.model.AccountBook;

import java.util.List;
import java.util.Map;

/**
 * Created by sanlli on 15/9/26.
 */
public interface AccountBookExtendMapper extends AccountBookMapper {

		/**
		 * 根据账套名称查询.
		 * @param map
		 * @return
		 */
		List selectByName(Map map);


		/**
		 * 查询账套列表.
		 * @return
		 */
		List selectAll();

		/**
		 * 查询最新的账套.
		 *
		 * @return
		 */
		AccountBook selectLatestAccountBook();

}
