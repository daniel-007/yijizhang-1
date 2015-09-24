package cn.ahyc.yjz.controller;

import cn.ahyc.yjz.model.AccountBook;
import cn.ahyc.yjz.service.AccountBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 新建账套.
 * Created by john Hu on 15-9-22.
 * AccountBookController
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
