package cn.ahyc.yjz.service;/**
 * AccountBookService
 *
 * @author:sanlai_lee@qq.com
 * @date: 15/9/23
 */

import cn.ahyc.yjz.model.AccountBook;

import java.util.List;

/**
 * Created by sanlli on 15/9/23.
 */
public interface AccountBookService {

      /**
       * 创建账套
       * @param accountBook
       */
      void createAccountBook(AccountBook accountBook);

      /**
       * 根据ID查询账套.
       * @param id
       * @return
       */
      AccountBook selectAccountBookById(Long id);
}
