package cn.ahyc.yjz.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ahyc.yjz.model.Period;
import cn.ahyc.yjz.service.SearchVoucherService;
import cn.ahyc.yjz.service.VoucherService;
import cn.ahyc.yjz.util.Constant;

/**
 * 凭证查询控制器.
 * Created by Joey Yan on 15-10-12.
 */

@Controller
@RequestMapping("/search/voucher")
public class SearchVoucherContrller extends BaseController {

    private final String pathPre = "search/voucher/";

    @Autowired
    private SearchVoucherService searchVoucherService;

    @Autowired
    private VoucherService voucherService;

    /**
     * 删除凭证.
     *
     * @param session
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Map delete(HttpSession session, Long voucherId) {

        Map map = new HashMap();
        map.put("success", true);
        Period period = (Period) session.getAttribute(Constant.CURRENT_PERIOD);

        try {
            voucherService.delete(voucherId, period.getId());
        } catch (Exception e) {
            map.put("success", false);
            map.put("msg", e.getMessage());
        }

        return map;
    }

    /**
     * 凭证整理.
     *
     * @param session
     * @return
     */
    @RequestMapping("/set")
    @ResponseBody
    public Map set(HttpSession session) {

        Map map = new HashMap();
        map.put("success", true);
        Period period = (Period) session.getAttribute(Constant.CURRENT_PERIOD);

        try {
            searchVoucherService.set(period);
        } catch (Exception e) {
            map.put("success", false);
            map.put("msg", e.getMessage());
        }

        return map;
    }

    /**
     * 获取当前账套期间.
     *
     * @param session
     * @return
     */
    @RequestMapping("/periods")
    @ResponseBody
    public List<Period> periods(HttpSession session) {
        Period period = (Period) session.getAttribute(Constant.CURRENT_PERIOD);
        return searchVoucherService.periods(period);
    }

    /**
     * 获取当前账单当前关联的凭证.
     *
     * @param session
     * @param periodId
     * @param keyword
     * @return
     */
    @RequestMapping("/vouchers")
    @ResponseBody
    public List<Map> vouchers(HttpSession session, Long periodId, String keyword) {
        Period period = (Period) session.getAttribute(Constant.CURRENT_PERIOD);
        if (periodId != null) {
            period.setId(periodId);
        }
        return searchVoucherService.vouchers(period, keyword);
    }


    @RequestMapping("/page/{view}")
    public String pageRedirect(@PathVariable String view, HttpSession session, Model model) {
        Period period = (Period) session.getAttribute(Constant.CURRENT_PERIOD);
        model.addAttribute("period", period);
        return view(this.pathPre.concat(view));
    }

}
