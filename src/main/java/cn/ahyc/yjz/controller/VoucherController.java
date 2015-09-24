/**
 * Copyright (c) 2015, AnHui Xin Hua She Group. All rights reserved.
 */
package cn.ahyc.yjz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * VoucherController
 *
 * @author sanlai_lee@qq.com
 */
@Controller
@RequestMapping("/voucher")
public class VoucherController extends BaseController {

      public VoucherController() {
            this.pathPrefix = "module/voucher/";
      }

      @RequestMapping("/main")
      public String voucher(Map<String, Object> model) {


            return view("voucher");
      }
}
