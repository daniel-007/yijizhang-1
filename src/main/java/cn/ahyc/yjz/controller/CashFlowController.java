package cn.ahyc.yjz.controller;

import cn.ahyc.yjz.model.Period;
import cn.ahyc.yjz.service.CashFlowService;
import cn.ahyc.yjz.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 现金流转控制器.
 * <p>
 * Created by Joey Yan on 15-11-3.
 */
@Controller
@RequestMapping("/cash/flow")
public class CashFlowController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(CashFlowController.class);

    @Autowired
    private CashFlowService cashFlowService;

    @RequestMapping("/main")
    public String main() {
        return view("cashFlow/main");
    }


    /**
     * 获取现金流转数据.
     *
     * @param session
     * @param startPeriod
     * @param endPeriod
     * @return
     */
    @RequestMapping("/cashflows")
    @ResponseBody
    public List<Map> cashflows(HttpSession session, Integer startPeriod, Integer endPeriod) {

        logger.info("获取现金流转数据，startPeriod={}, endPeriod={}", startPeriod, endPeriod);
        Period period = (Period) session.getAttribute(Constant.CURRENT_PERIOD);

        return cashFlowService.cashflows(period, startPeriod, endPeriod);
    }

}
