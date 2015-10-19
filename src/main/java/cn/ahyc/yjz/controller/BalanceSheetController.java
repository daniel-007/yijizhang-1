package cn.ahyc.yjz.controller;

import cn.ahyc.yjz.model.BalanceSheet;
import cn.ahyc.yjz.model.Period;
import cn.ahyc.yjz.service.BalanceSheetService;
import cn.ahyc.yjz.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 资产负债控制器.
 * Created by Joey Yan on 15-10-15.
 */
@Controller
@RequestMapping("/balance/sheet")
public class BalanceSheetController extends BaseController {

    @Autowired
    private BalanceSheetService balanceSheetService;

    /**
     * 资产负债表入口.
     *
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/main")
    public String main(HttpSession session, Model model) {
        Period period = (Period) session.getAttribute(Constant.CURRENT_PERIOD);

        List<BalanceSheet> balanceSheets = balanceSheetService.balanceSheetsByParentCode(-9999l);
        model.addAttribute("balanceSheets", balanceSheets);

        return view("balanceSheet/main");
    }


    /**
     * 获取所有code下数据.
     *
     * @param session
     * @param code
     * @return
     */
    @RequestMapping("/balanceSheets")
    @ResponseBody
    public List<Map> balanceSheets(HttpSession session, Long code) {
        Period period = (Period) session.getAttribute(Constant.CURRENT_PERIOD);

        return balanceSheetService.balanceSheets(period, code);
    }


}
