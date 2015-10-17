/**
 * Copyright (c) 2015, AnHui Xin Hua She Group. All rights reserved.
 */
package cn.ahyc.yjz.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ahyc.yjz.model.AccountBook;
import cn.ahyc.yjz.model.AccountSubject;
import cn.ahyc.yjz.model.Period;
import cn.ahyc.yjz.model.Voucher;
import cn.ahyc.yjz.model.VoucherDetail;
import cn.ahyc.yjz.model.VoucherTemplate;
import cn.ahyc.yjz.model.VoucherTemplateDetail;
import cn.ahyc.yjz.service.PeriodService;
import cn.ahyc.yjz.service.VoucherService;
import cn.ahyc.yjz.util.Constant;

/**
 * 记账凭证
 *
 * @author sanlai_lee@qq.com
 */
@Controller
@RequestMapping("/voucher")
public class VoucherController extends BaseController {

		private static final Logger LOGGER = LoggerFactory.getLogger(VoucherController.class);

		@Autowired
		private VoucherService voucherService;

		@Autowired
		private PeriodService periodService;

		public VoucherController() {
				this.pathPrefix = "module/voucher/";
		}

<<<<<<< HEAD
		@RequestMapping("/main")
		public String voucher(Model model, Long voucherId, HttpSession session) {
				Voucher voucher = new Voucher();
				Period voucherPeriod = null;
				if (voucherId != null) {
						voucher = voucherService.queryVoucher(voucherId);
						voucherPeriod = periodService.queryPeriod(voucher.getPeriodId());
						model.addAttribute("currentPeriod", voucherPeriod.getCurrentPeriod());
				}
				model.addAttribute("voucher", voucher);
				Period period = (Period) session.getAttribute(Constant.CURRENT_PERIOD);
				AccountBook accountBook = (AccountBook) session.getAttribute(Constant.CURRENT_ACCOUNT_BOOK);
				model.addAttribute("sessionBook", accountBook.getInitYear());
				model.addAttribute("sessionPeriod", period.getCurrentPeriod());
				model.addAttribute("voucherNo", voucherService.queryNextVoucherNo(period.getId()));
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String sessiontime = accountBook.getInitYear() + "-" + String.format("%02d", period.getCurrentPeriod());
				String systemtime = dateFormat.format(new Date());
				model.addAttribute("voucherTime", systemtime.startsWith(sessiontime) ? systemtime : sessiontime + "-01");
				return view("voucher");
		}
=======
    /**
     * 凭证页面
     * 
     * @param model
     * @param voucherId
     * @param voucherTemplateId
     * @param session
     * @return
     */
	@RequestMapping("/main")
    public String voucher(Model model, Long voucherId, Long voucherTemplateId, HttpSession session) {
        if (voucherTemplateId != null) {// 从模式凭证新增
            VoucherTemplate template = voucherService.queryVoucherTemplate(voucherTemplateId);
            model.addAttribute("voucher", template);
        } else if (voucherId != null) {// 修改
            Voucher voucher = voucherService.queryVoucher(voucherId);
            Period voucherPeriod = periodService.queryPeriod(voucher.getPeriodId());
            model.addAttribute("currentPeriod", voucherPeriod.getCurrentPeriod());
            model.addAttribute("voucher", voucher);
        } else {
            model.addAttribute("voucher", new Voucher());
        }
        Period period = (Period) session.getAttribute(Constant.CURRENT_PERIOD);
        AccountBook accountBook = (AccountBook) session.getAttribute(Constant.CURRENT_ACCOUNT_BOOK);
        model.addAttribute("sessionBook", accountBook.getInitYear());
        model.addAttribute("sessionPeriod", period.getCurrentPeriod());
        model.addAttribute("voucherNo", voucherService.queryNextVoucherNo(period.getId()));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String sessiontime = accountBook.getInitYear() + "-" + String.format("%02d", period.getCurrentPeriod());
        String systemtime = dateFormat.format(new Date());
        model.addAttribute("voucherTime", systemtime.startsWith(sessiontime) ? systemtime : sessiontime + "-01");
	    return view("voucher");
	}
	
    /**
     * 凭证明细列表
     * 
     * @param voucherId
     * @param session
     * @return
     */
    @RequestMapping("/voucherDetailList")
    @ResponseBody
    public Map<String, Object> voucherDetailList(Long voucherId, HttpSession session) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> footerlist = new ArrayList<Map<String, Object>>();
        if (voucherId != null) {
            long bookId = ((AccountBook) session.getAttribute(Constant.CURRENT_ACCOUNT_BOOK)).getId();
            list = voucherService.queryVoucherDetailList(voucherId, bookId);
            footerlist.add(voucherService.queryDetailTotal(voucherId));
        } else {
            footerlist.add(new HashMap<String, Object>());
        }
>>>>>>> dev

		@RequestMapping("/voucherDetailList")
		@ResponseBody
		public Map<String, Object> voucherDetailList(Long voucherId, HttpSession session) {
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				List<Map<String, Object>> footerlist = new ArrayList<Map<String, Object>>();
				if (voucherId != null) {
						long bookId = ((AccountBook) session.getAttribute(Constant.CURRENT_ACCOUNT_BOOK)).getId();
						list = voucherService.queryVoucherDetailList(voucherId, bookId);
						footerlist.add(voucherService.queryDetailTotal(voucherId));
				} else {
						footerlist.add(new HashMap<String, Object>());
				}

<<<<<<< HEAD
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("total", list != null && list.size() > 0 ? list.size() : 0);
				map.put("rows", list);
				map.put("footer", footerlist);
				return map;
		}

		@RequestMapping("/voucherWordList")
		@ResponseBody
		public List<CompanyCommonValue> voucherWordList() {
				List<CompanyCommonValue> list = voucherService.queryVoucherWordList(1L);// 1L：凭证字
				return list;
		}

		@RequestMapping("/voucherTemplateTypeList")
		@ResponseBody
		public List<CompanyCommonValue> voucherTemplateTypeList() {
				List<CompanyCommonValue> list = voucherService.queryVoucherWordList(2L);// 2L：模式凭证类别
				return list;
		}
=======
    /**
     * 凭证保存
     * 
     * @param session
     * @param model
     * @param request
     * @param voucher
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(HttpSession session, Model model, HttpServletRequest request, Voucher voucher) {
>>>>>>> dev

		@RequestMapping(value = "/save", method = RequestMethod.POST)
		@ResponseBody
		public Map<String, Object> save(HttpSession session, Model model, HttpServletRequest request, Voucher voucher) {

<<<<<<< HEAD
				Map<String, Object> map = new HashMap<String, Object>();
				try {

						Period period = (Period) session.getAttribute(Constant.CURRENT_PERIOD);
						AccountBook accountBook = (AccountBook) session.getAttribute(Constant.CURRENT_ACCOUNT_BOOK);
						if (period.getId().equals(voucher.getPeriodId())) {
								throw new RuntimeException("当前会计期间id：" + period.getId() + "不等于保存凭证会计期间id：" + voucher.getPeriodId());
						}
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						String sessiontime = accountBook.getInitYear() + "-" + String.format("%02d", period.getCurrentPeriod());
						String voucherTime = dateFormat.format(voucher.getVoucherTime());
						if (!StringUtils.startsWith(voucherTime, sessiontime)) {
								throw new RuntimeException("凭证日期" + voucherTime + "不在当前会计期间日期" + sessiontime + "范围内");
						}
						voucher.setPeriodId(period.getId());
						/** 组织凭证明细数据 **/
						List<VoucherDetail> details = new ArrayList<VoucherDetail>();
						String[] subjectCodeArr = request.getParameterValues("subjectCode");
						if (subjectCodeArr != null) {
								if (subjectCodeArr.length <= 0) {
										throw new RuntimeException("必须存在凭证分录");
								}
								String[] summaryArr = request.getParameterValues("summary");
								String[] debitArr = request.getParameterValues("newdebit");
								String[] crebitArr = request.getParameterValues("newcrebit");
								VoucherDetail voucherDetail;
								BigDecimal debit;
								BigDecimal credit;
								BigDecimal debitSum = new BigDecimal(0);
								BigDecimal crebitSum = new BigDecimal(0);
								for (int i = 0; i < subjectCodeArr.length; i++) {
										LOGGER.info("借方：{}|贷方：{}", debitArr[i], crebitArr[i]);
										if (StringUtils.isBlank(debitArr[i]) && StringUtils.isBlank(crebitArr[i])) {
												throw new RuntimeException("同一凭证分录中借方金额、贷方金额必须存在一个");
										}
										if (StringUtils.isNotBlank(debitArr[i]) && StringUtils.isNotBlank(crebitArr[i])) {
												throw new RuntimeException("同一凭证分录中借方金额、贷方金额只能存在一个");
										}
										voucherDetail = new VoucherDetail();
										voucherDetail.setSummary(summaryArr[i]);
										voucherDetail.setSubjectCode(Long.valueOf(subjectCodeArr[i]));
										if (StringUtils.isNotBlank(debitArr[i])) {
												debit = new BigDecimal(debitArr[i]);
												debitSum = debitSum.add(debit);
												voucherDetail.setDebit(debit);
										}
										if (StringUtils.isNotBlank(crebitArr[i])) {
												credit = new BigDecimal(crebitArr[i]);
												crebitSum = crebitSum.add(credit);
												voucherDetail.setCredit(credit);
										}
										details.add(voucherDetail);
								}
								if (debitSum.compareTo(crebitSum) != 0) {
										throw new RuntimeException("记账凭证借贷不平衡，借方：" + debitSum + "|贷方：" + crebitSum);
								}
						}
						/** 保存 **/
						map.put("result", voucherService.save(voucher, details));
				} catch (Exception e) {
						e.printStackTrace();
						map.put("message", "系统异常！");
				}
				return map;
		}

		@RequestMapping("/accountSubjectList")
		@ResponseBody
		public List<AccountSubject> accountSubjectList(HttpSession session) {
				long bookId = ((AccountBook) session.getAttribute(Constant.CURRENT_ACCOUNT_BOOK)).getId();
				List<AccountSubject> list = voucherService.queryAccountSubjectList(bookId);
				return list;
		}

		@RequestMapping("/help")
		public String help() {
				return view("help");
		}

		@RequestMapping("/subjectBalance")
		public String subjectBalance(Model model, String subjectCode, Long voucherId, HttpSession session) {
				Period voucherPeriod = null;
				if (voucherId != null) {
						Voucher voucher = voucherService.queryVoucher(voucherId);
						voucherPeriod = periodService.queryPeriod(voucher.getPeriodId());
				}
				AccountBook accountBook = (AccountBook) session.getAttribute(Constant.CURRENT_ACCOUNT_BOOK);
				Period period = (Period) session.getAttribute(Constant.CURRENT_PERIOD);
				model.addAttribute("currentPeriod",
							voucherPeriod != null ? voucherPeriod.getCurrentPeriod() : period.getCurrentPeriod());
				model.addAttribute("moneyCode", accountBook.getMoneyCode());
				model.addAttribute("subjectName", subjectCode);
				model.addAttribute("subjectCode", StringUtils.split(subjectCode, " ")[0]);
				return view("subjectBalance");
		}

		@RequestMapping("/voucherTemplate")
		public String voucherTemplate(Model model) {
				return view("voucherTemplate");
		}

		@RequestMapping("/voucherTemplateList")
		@ResponseBody
		public Map<String, Object> voucherTemplateList(HttpSession session) {
				List<VoucherTemplate> list = voucherService.queryVoucherTemplateList();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("total", list != null && list.size() > 0 ? list.size() : 0);
				map.put("rows", list);
				return map;
		}

		@RequestMapping("/voucherTemplateAdd")
		public String voucherTemplateAdd(Model model, Long voucherTemplateId, HttpSession session) {
				VoucherTemplate voucherTemplate = new VoucherTemplate();
				if (voucherTemplateId != null) {
						voucherTemplate = voucherService.queryVoucherTemplate(voucherTemplateId);
				}
				model.addAttribute("voucherTemplate", voucherTemplate);
				return view("voucherTemplateAdd");
		}

		@RequestMapping("/voucherTemplateDetailList")
		@ResponseBody
		public Map<String, Object> voucherTemplateDetailList(HttpSession session, Long voucherTemplateId) {
				List<VoucherTemplateDetail> list = new ArrayList<VoucherTemplateDetail>();
				if (voucherTemplateId != null) {
						AccountBook accountBook = (AccountBook) session.getAttribute(Constant.CURRENT_ACCOUNT_BOOK);
						list = voucherService.queryVoucherTemplateDetailList(voucherTemplateId, accountBook.getId());
				}
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("total", list != null && list.size() > 0 ? list.size() : 0);
				map.put("rows", list);
				return map;
		}

		@RequestMapping("/checkTemplateName")
		@ResponseBody
		public Map<String, Object> checkTemplateName(HttpSession session, String name, Long id) {
				Map<String, Object> map = new HashMap<String, Object>();
				try {
						if (StringUtils.isNoneBlank(name) && voucherService.checkTemplateName(name, id)) {
								map.put("result", "success");
						}
				} catch (Exception e) {
						e.printStackTrace();
						map.put("msg", e.getMessage());
				}
				return map;
		}

		@RequestMapping("/deleteTemplate")
		@ResponseBody
		public Map<String, Object> deleteTemplate(Long id) {
				Map<String, Object> map = new HashMap<String, Object>();
				try {
						if (id != null) {
								voucherService.deleteTemplate(id);
								map.put("result", "success");
						}
				} catch (Exception e) {
						e.printStackTrace();
						map.put("msg", e.getMessage());
				}
				return map;
		}
=======
            Period period = (Period) session.getAttribute(Constant.CURRENT_PERIOD);
            AccountBook accountBook = (AccountBook) session.getAttribute(Constant.CURRENT_ACCOUNT_BOOK);
            if (voucher.getPeriodId() == null) {
                voucher.setPeriodId(period.getId());
            }
            if (!period.getId().equals(voucher.getPeriodId())) {
                throw new RuntimeException("当前会计期间id：" + period.getId() + "不等于保存凭证会计期间id：" + voucher.getPeriodId());
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String sessiontime = accountBook.getInitYear() + "-" + String.format("%02d", period.getCurrentPeriod());
            String voucherTime = dateFormat.format(voucher.getVoucherTime());
            if (!StringUtils.startsWith(voucherTime, sessiontime)) {
                throw new RuntimeException("凭证日期" + voucherTime + "不在当前会计期间日期" + sessiontime + "范围内");
            }
            /** 组织凭证明细数据 **/
            List<VoucherDetail> details = new ArrayList<VoucherDetail>();
            String[] subjectCodeArr = request.getParameterValues("subjectCode");
            if (subjectCodeArr != null) {
                if (subjectCodeArr.length <= 0) {
                    throw new RuntimeException("必须存在凭证分录");
                }
                String[] summaryArr = request.getParameterValues("summary");
                String[] debitArr = request.getParameterValues("newdebit");
                String[] crebitArr = request.getParameterValues("newcrebit");
                VoucherDetail voucherDetail;
                BigDecimal debit;
                BigDecimal credit;
                BigDecimal debitSum = new BigDecimal(0);
                BigDecimal crebitSum = new BigDecimal(0);
                for (int i = 0; i < subjectCodeArr.length; i++) {
                    LOGGER.info("借方：{}|贷方：{}", debitArr[i], crebitArr[i]);
                    if (StringUtils.isBlank(debitArr[i]) && StringUtils.isBlank(crebitArr[i])) {
                        throw new RuntimeException("同一凭证分录中借方金额、贷方金额必须存在一个");
                    }
                    if (StringUtils.isNotBlank(debitArr[i]) && StringUtils.isNotBlank(crebitArr[i])) {
                        throw new RuntimeException("同一凭证分录中借方金额、贷方金额只能存在一个");
                    }
                    voucherDetail = new VoucherDetail();
                    voucherDetail.setSummary(summaryArr[i]);
                    voucherDetail.setSubjectCode(Long.valueOf(subjectCodeArr[i]));
                    if (StringUtils.isNotBlank(debitArr[i])) {
                        debit = new BigDecimal(debitArr[i]);
                        debitSum = debitSum.add(debit);
                        voucherDetail.setDebit(debit);
                    }
                    if (StringUtils.isNotBlank(crebitArr[i])) {
                        credit = new BigDecimal(crebitArr[i]);
                        crebitSum = crebitSum.add(credit);
                        voucherDetail.setCredit(credit);
                    }
                    details.add(voucherDetail);
                }
                if (debitSum.compareTo(crebitSum) != 0) {
                    throw new RuntimeException("记账凭证借贷不平衡，借方：" + debitSum + "|贷方：" + crebitSum);
                }
            }
            /** 保存 **/
            map.put("result", voucherService.save(voucher, details));
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message", "系统异常！");
        }
        return map;
    }

    /**
     * 会计科目叶子节点列表
     * 
     * @param session
     * @return
     */
    @RequestMapping("/accountSubjectList")
    @ResponseBody
    public List<AccountSubject> accountSubjectList(HttpSession session) {
        long bookId = ((AccountBook) session.getAttribute(Constant.CURRENT_ACCOUNT_BOOK)).getId();
        List<AccountSubject> list = voucherService.queryAccountSubjectList(bookId);
        return list;
    }

    /**
     * 凭证制作说明页面
     * 
     * @return
     */
    @RequestMapping("/help")
    public String help() {
        return view("help");
    }

    /**
     * 凭证-科目余额页面
     * 
     * @param model
     * @param subjectCode
     * @param voucherId
     * @param session
     * @return
     */
    @RequestMapping("/subjectBalance")
    public String subjectBalance(Model model, String subjectCode, Long voucherId, HttpSession session) {
        Period voucherPeriod = null;
        if (voucherId != null) {
            Voucher voucher = voucherService.queryVoucher(voucherId);
            voucherPeriod = periodService.queryPeriod(voucher.getPeriodId());
        }
        AccountBook accountBook = (AccountBook) session.getAttribute(Constant.CURRENT_ACCOUNT_BOOK);
        Period period = (Period) session.getAttribute(Constant.CURRENT_PERIOD);
        model.addAttribute("currentPeriod",
                voucherPeriod != null ? voucherPeriod.getCurrentPeriod() : period.getCurrentPeriod());
        model.addAttribute("moneyCode", accountBook.getMoneyCode());
        model.addAttribute("subjectName", subjectCode);
        model.addAttribute("subjectCode", StringUtils.split(subjectCode, " ")[0]);
        return view("subjectBalance");
    }

    /**
     * 模式凭证页面
     * 
     * @param model
     * @return
     */
    @RequestMapping("/voucherTemplate")
    public String voucherTemplate(Model model) {
        return view("voucherTemplate");
    }

    /**
     * 模式凭证列表
     * 
     * @param session
     * @return
     */
    @RequestMapping("/voucherTemplateList")
    @ResponseBody
    public Map<String, Object> voucherTemplateList(HttpSession session) {
        List<VoucherTemplate> list = voucherService.queryVoucherTemplateList();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", list != null && list.size() > 0 ? list.size() : 0);
        map.put("rows", list);
        return map;
    }

    /**
     * 模式凭证编辑页面
     * 
     * @param model
     * @param voucherTemplateId
     * @param session
     * @return
     */
    @RequestMapping("/voucherTemplateAdd")
    public String voucherTemplateAdd(Model model, Long voucherTemplateId, HttpSession session) {
        VoucherTemplate voucherTemplate = new VoucherTemplate();
        if (voucherTemplateId != null) {
            voucherTemplate = voucherService.queryVoucherTemplate(voucherTemplateId);
        }
        model.addAttribute("voucherTemplate", voucherTemplate);
        return view("voucherTemplateAdd");
    }

    /**
     * 模式凭证明细列表
     * 
     * @param session
     * @param voucherTemplateId
     * @return
     */
    @RequestMapping("/voucherTemplateDetailList")
    @ResponseBody
    public Map<String, Object> voucherTemplateDetailList(HttpSession session, Long voucherTemplateId) {
        List<VoucherTemplateDetail> list = new ArrayList<VoucherTemplateDetail>();
        if (voucherTemplateId != null) {
            AccountBook accountBook = (AccountBook) session.getAttribute(Constant.CURRENT_ACCOUNT_BOOK);
            list = voucherService.queryVoucherTemplateDetailList(voucherTemplateId, accountBook.getId());
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", list != null && list.size() > 0 ? list.size() : 0);
        map.put("rows", list);
        return map;
    }

    /**
     * 检查模式凭证名称是否重复
     * 
     * @param session
     * @param name
     * @param id
     * @return
     */
    @RequestMapping("/checkTemplateName")
    @ResponseBody
    public Map<String, Object> checkTemplateName(HttpSession session, String name, Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (StringUtils.isNoneBlank(name) && voucherService.checkTemplateName(name, id)) {
                map.put("result", "success");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", e.getMessage());
        }
        return map;
    }

    /**
     * 删除模式凭证
     * 
     * @param id
     * @return
     */
    @RequestMapping("/deleteTemplate")
    @ResponseBody
    public Map<String, Object> deleteTemplate(Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (id!=null) {
                voucherService.deleteTemplate(id);
                map.put("result", "success");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", e.getMessage());
        }
        return map;
    }

    /**
     * 保存模式凭证
     * 
     * @param session
     * @param model
     * @param request
     * @param voucherTemplate
     * @return
     */
    @RequestMapping(value = "/saveTemplate", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveTemplate(HttpSession session, Model model, HttpServletRequest request,
            VoucherTemplate voucherTemplate) {
>>>>>>> dev

		@RequestMapping(value = "/saveTemplate", method = RequestMethod.POST)
		@ResponseBody
		public Map<String, Object> saveTemplate(HttpSession session, Model model, HttpServletRequest request,
		                                        VoucherTemplate voucherTemplate) {

				Map<String, Object> map = new HashMap<String, Object>();
				try {

						/** 组织凭证明细数据 **/
						List<VoucherTemplateDetail> details = new ArrayList<VoucherTemplateDetail>();
						String[] subjectCodeArr = request.getParameterValues("subjectCode");
						if (subjectCodeArr != null) {
								String[] summaryArr = request.getParameterValues("summary");
								String[] debitArr = request.getParameterValues("debit");
								String[] crebitArr = request.getParameterValues("credit");
								VoucherTemplateDetail voucherTemplateDetail;
								boolean addFlag;
								for (int i = 0; i < subjectCodeArr.length; i++) {
										if (StringUtils.isNotBlank(debitArr[i]) && StringUtils.isNotBlank(crebitArr[i])) {
												throw new RuntimeException("同一凭证分录中借方金额、贷方金额只能存在一个");
										}
										voucherTemplateDetail = new VoucherTemplateDetail();
										addFlag = false;
										if (StringUtils.isNotBlank(summaryArr[i])) {
												addFlag = true;
												voucherTemplateDetail.setSummary(summaryArr[i]);
										}
										if (StringUtils.isNotBlank(subjectCodeArr[i])) {
												addFlag = true;
												voucherTemplateDetail.setSubjectCode(Long.valueOf(subjectCodeArr[i]));
										}
										if (StringUtils.isNotBlank(debitArr[i])) {
												addFlag = true;
												voucherTemplateDetail.setDebit(new BigDecimal(debitArr[i]));
										}
										if (StringUtils.isNotBlank(crebitArr[i])) {
												addFlag = true;
												voucherTemplateDetail.setCredit(new BigDecimal(crebitArr[i]));
										}
										if (addFlag) {
												details.add(voucherTemplateDetail);
										}
								}
						}
						/** 保存 **/
						voucherService.saveTemplate(voucherTemplate, details);
						map.put("result", "保存成功");
				} catch (Exception e) {
						e.printStackTrace();
						map.put("message", "系统异常！");
				}
				return map;
		}
}