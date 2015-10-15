package cn.ahyc.yjz.service.impl;

import java.util.HashMap;

/**
 * AccountBookServiceImpl
 *
 * @author:sanlai_lee@qq.com
 * @date: 15/9/23
 */

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ahyc.yjz.mapper.base.CompanyCommonValueMapper;
import cn.ahyc.yjz.mapper.extend.AccountSubjectExtendMapper;
import cn.ahyc.yjz.mapper.extend.SubjectBalanceExtendMapper;
import cn.ahyc.yjz.mapper.extend.VoucherDetailExtendMapper;
import cn.ahyc.yjz.mapper.extend.VoucherExtendMapper;
import cn.ahyc.yjz.mapper.extend.VoucherTemplateDetailExtendMapper;
import cn.ahyc.yjz.mapper.extend.VoucherTemplateExtendMapper;
import cn.ahyc.yjz.model.AccountSubject;
import cn.ahyc.yjz.model.CompanyCommonValue;
import cn.ahyc.yjz.model.CompanyCommonValueExample;
import cn.ahyc.yjz.model.CompanyCommonValueExample.Criteria;
import cn.ahyc.yjz.model.Voucher;
import cn.ahyc.yjz.model.VoucherDetail;
import cn.ahyc.yjz.model.VoucherDetailExample;
import cn.ahyc.yjz.model.VoucherTemplate;
import cn.ahyc.yjz.model.VoucherTemplateDetail;
import cn.ahyc.yjz.model.VoucherTemplateDetailExample;
import cn.ahyc.yjz.model.VoucherTemplateExample;
import cn.ahyc.yjz.service.VoucherService;

@Service
public class VoucherServiceImpl implements VoucherService {

    @Autowired
    private VoucherExtendMapper voucherExtendMapper;

    @Autowired
    private VoucherDetailExtendMapper voucherDetailExtendMapper;

    @Autowired
    private CompanyCommonValueMapper companyCommonValueMapper;

    @Autowired
    private AccountSubjectExtendMapper accountSubjectExtendMapper;

    @Autowired
    private SubjectBalanceExtendMapper subjectBalanceExtendMapper;

    @Autowired
    private VoucherTemplateExtendMapper voucherTemplateExtendMapper;

    @Autowired
    private VoucherTemplateDetailExtendMapper voucherTemplateDetailExtendMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String save(Voucher voucher, List<VoucherDetail> details) {
        long voucherId;
        int voucherNo;
        /** 新增、更新记账凭证 **/
        if (voucher != null && voucher.getId() != null) {
            voucherId = voucher.getId();
            voucherNo = voucher.getVoucherNo();
            voucherExtendMapper.updateByPrimaryKeySelective(voucher);
            /** 删除凭证明细 **/
            VoucherDetailExample example = new VoucherDetailExample();
            cn.ahyc.yjz.model.VoucherDetailExample.Criteria criteria = example.createCriteria();
            criteria.andVoucherIdEqualTo(voucherId);
            voucherDetailExtendMapper.deleteByExample(example);
        } else {
            voucherNo = queryNextVoucherNo(voucher.getPeriodId());
            voucher.setVoucherNo(voucherNo);
            voucherExtendMapper.insertSelectiveReturnId(voucher);
            voucherId = voucher.getId();
        }
        /** 新增凭证明细 **/
        for (VoucherDetail detail : details) {
            detail.setVoucherId(voucherId);
            detail.setId(null);
            voucherDetailExtendMapper.insertSelective(detail);
        }
        /** 科目余额统计 **/
        subjectBalanceExtendMapper.insertOrUpdateSubjectBalance(voucher.getPeriodId());
        subjectBalanceExtendMapper.collectSubjectBalance(voucher.getPeriodId());
        return voucher.getVoucherWord() + "字第" + voucherNo + "号";
    }

    @Override
    public int queryNextVoucherNo(Long periodId) {
        return voucherExtendMapper.selectMaxVoucherNo(periodId) + 1;
    }

    @Override
    public List<CompanyCommonValue> queryVoucherWordList(Long typeId) {
        CompanyCommonValueExample example = new CompanyCommonValueExample();
        Criteria criteria = example.createCriteria();
        criteria.andTypeIdEqualTo(typeId);
        return companyCommonValueMapper.selectByExample(example);
    }

    @Override
    public List<Map<String, Object>> queryVoucherDetailList(Long voucherId, Long bookId) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("voucherId", voucherId);
        param.put("bookId", bookId);
        return voucherDetailExtendMapper.selectDetailList(param);
    }

    @Override
    public Voucher queryVoucher(Long voucherId) {
        return voucherExtendMapper.selectByPrimaryKey(voucherId);
    }

    @Override
    public List<AccountSubject> queryAccountSubjectList(Long bookId) {
        return accountSubjectExtendMapper.selectLastChildSubject(bookId);
    }

    @Override
    public Map<String, Object> queryDetailTotal(Long voucherId) {
        return voucherDetailExtendMapper.selectDetailTotal(voucherId);
    }

    @Override
    public List<VoucherTemplate> queryVoucherTemplateList() {
        VoucherTemplateExample example = new VoucherTemplateExample();
        return voucherTemplateExtendMapper.selectByExample(example);
    }

    @Override
    public VoucherTemplate queryVoucherTemplate(Long voucherTemplateId) {
        return voucherTemplateExtendMapper.selectByPrimaryKey(voucherTemplateId);
    }

    @Override
    public List<VoucherTemplateDetail> queryVoucherTemplateDetailList(Long voucherTemplateId, Long bookId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("bookId", bookId);
        map.put("voucherTemplateId", voucherTemplateId);
        return voucherTemplateDetailExtendMapper.selectTemplateDetailList(map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveTemplate(VoucherTemplate voucherTemplate, List<VoucherTemplateDetail> details) {
        Long templateId;
        /** 新增、更新记账模式凭证 **/
        if (voucherTemplate != null && voucherTemplate.getId() != null) {
            templateId = voucherTemplate.getId();
            voucherTemplateExtendMapper.updateByPrimaryKeySelective(voucherTemplate);
            /** 删除模式凭证明细 **/
            deleteTemplateDetails(templateId);
        } else {
            voucherTemplateExtendMapper.insertSelectiveReturnId(voucherTemplate);
            templateId = voucherTemplate.getId();
        }
        /** 新增模式凭证明细 **/
        for (VoucherTemplateDetail detail : details) {
            detail.setTemplateId(templateId);
            detail.setId(null);
            voucherTemplateDetailExtendMapper.insertSelective(detail);
        }
    }

    @Override
    public boolean checkTemplateName(String name, Long id) {
        VoucherTemplateExample example = new VoucherTemplateExample();
        cn.ahyc.yjz.model.VoucherTemplateExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        if (id != null) {
            criteria.andIdNotEqualTo(id);
        }
        return voucherTemplateExtendMapper.countByExample(example) < 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteTemplate(Long id) {
        voucherTemplateExtendMapper.deleteByPrimaryKey(id);
        deleteTemplateDetails(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteTemplateDetails(Long id) {
        VoucherTemplateDetailExample example = new VoucherTemplateDetailExample();
        cn.ahyc.yjz.model.VoucherTemplateDetailExample.Criteria criteria = example.createCriteria();
        criteria.andTemplateIdEqualTo(id);
        voucherTemplateDetailExtendMapper.deleteByExample(example);
    }
}
