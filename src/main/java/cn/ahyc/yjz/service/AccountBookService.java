package cn.ahyc.yjz.service;/**
 * AccountBookService
 *
 * @author:sanlai_lee@qq.com
 * @date: 15/9/23
 */

import java.util.List;

import cn.ahyc.yjz.model.AccountBook;
import cn.ahyc.yjz.model.DictValue;

/**
 * Created by sanlli on 15/9/23.
 */
public interface AccountBookService {

      /**
       * 创建账套
       * @param accountBook
       * @throws Exception 
       */
	  String createAccountBook(AccountBook accountBook,int... level) throws Exception;
      /**
       * 根据ID查询账套.
       * @param id
       * @return
       */
      AccountBook selectAccountBookById(Long id);
      /**
       * 查询科目体系数据
       * @param id
       * @return
       */
      List<DictValue> selectSubjectSystem();
       
}
