package cn.ahyc.yjz.service.impl;/**
 * AccountBookServiceImpl
 *
 * @author:sanlai_lee@qq.com
 * @date: 15/9/23
 */

import cn.ahyc.yjz.mapper.base.AccountBookMapper;
import cn.ahyc.yjz.model.AccountBook;
import cn.ahyc.yjz.service.AccountBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by sanlli on 15/9/23.
 */
@Service
public class AccountBookServiceImpl implements AccountBookService {

      @Autowired
      private AccountBookMapper accountBookMapper;

      /**
       * 创建账套
       *
       * @param accountBook
       */
      @Override
      public void createAccountBook(AccountBook accountBook) {

      }

      /**
       * 根据ID查询账套.
       *
       * @param id
       * @return
       */
      @Override
      public AccountBook selectAccountBookById(Long id) {
            return accountBookMapper.selectByPrimaryKey(id);
      }
}
