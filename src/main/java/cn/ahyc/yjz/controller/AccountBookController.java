package cn.ahyc.yjz.controller;/**
 * AccountBookController
 *
 * @author:sanlai_lee@qq.com
 * @date: 15/9/23
 */

import cn.ahyc.yjz.model.AccountBook;
import cn.ahyc.yjz.service.AccountBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by sanlli on 15/9/23.
 */
@Controller
@RequestMapping("/account/book")
public class AccountBookController extends BaseController{

      @Autowired
      private AccountBookService accountBookService;

      /**
       * 测试
       * @param id
       * @return
       */
      @RequestMapping("/{id}")
      @ResponseBody
      public AccountBook accountBook(@PathVariable("id") Long id){
            return accountBookService.selectAccountBookById(id);
      }

}
