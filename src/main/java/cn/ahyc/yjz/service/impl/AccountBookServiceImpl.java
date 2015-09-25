
package cn.ahyc.yjz.service.impl;/**
 * AccountBookServiceImpl
 *
 * @author:sanlai_lee@qq.com
 * @date: 15/9/23
 */

import cn.ahyc.yjz.mapper.base.AccountBookMapper;
import cn.ahyc.yjz.mapper.base.AccountSubjectTemplateMapper;
import cn.ahyc.yjz.mapper.base.DictValueMapper;
import cn.ahyc.yjz.mapper.base.PeriodMapper;
import cn.ahyc.yjz.mapper.base.SubjectLengthMapper;
import cn.ahyc.yjz.model.AccountBook;
import cn.ahyc.yjz.model.DictValue;
import cn.ahyc.yjz.model.DictValueExample;
import cn.ahyc.yjz.model.DictValueExample.Criteria;
import cn.ahyc.yjz.model.Period;
import cn.ahyc.yjz.model.SubjectLength;
import cn.ahyc.yjz.service.AccountBookService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by sanlli on 15/9/23.
 */
@Service
public class AccountBookServiceImpl implements AccountBookService {

      @Autowired
      private AccountBookMapper accountBookMapper;
      @Autowired
      private DictValueMapper dictValueMapper;
      @Autowired
      private SubjectLengthMapper subjectLengthMapper;
      @Autowired
      private AccountSubjectTemplateMapper accountSubjectTemplateMapper;
      @Autowired
      private PeriodMapper periodMapper;

      /**
       * 创建账套
       *
       * @param accountBook
     * @throws Exception 
       */
      @Transactional(rollbackFor = Exception.class)
      @Override
      public String createAccountBook(AccountBook accountBook,int... level) throws Exception {
    	  Date startTime=generateStartTime(accountBook);
    	  //保存账套表
    	  accountBook.setStartTime(startTime);
    	  accountBook.setOverFlag(0);
    	  accountBookMapper.insertSelective(accountBook);
    	  //保存科目代码长度表
    	  for(int i=0;i<level.length;i++){
    		  SubjectLength subjectLength=new SubjectLength();
    		  subjectLength.setBookId(accountBook.getId());
    		  subjectLength.setLength(level[i]);
    		  subjectLength.setLevel(i+1);
    		  subjectLengthMapper.insertSelective(subjectLength);
    	  }
    	  //复制科目体系对应的会计科目数据
    	  Map<String, Object> map = new HashMap<String, Object>();
          map.put("dictValueId", accountBook.getDictValueId());
          map.put("bookId", accountBook.getId());
          accountSubjectTemplateMapper.copyAccountSubject(map);
          //插入期表，当前期
          Period period=new Period();
          period.setStartTime(startTime);
          period.setCurrentPeriod(accountBook.getInitPeriod());
          period.setFlag(1);
          period.setBookId(accountBook.getId());
          period.setEndFlag(0);
    	  periodMapper.insertSelective(period);
    	  return "1";
      }
      //生成账套启用启用,字符串转化成日期
      public Date generateStartTime(AccountBook accountBook) throws Exception{
    	  String str=accountBook.getInitYear()+"-"+accountBook.getInitPeriod()+"-01";
    	  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	  Date date = null;
    	  date = format.parse(str);
    	  return date;
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
      /**
       * 查询科目体系数据
       * @param id
       * @return
       */
      @Override
      public List<DictValue> selectSubjectSystem(){
    	  DictValueExample example=new DictValueExample();
    	  Criteria criteria=example.createCriteria(); 
    	  criteria.andDictTypeIdEqualTo(1L);
    	  return dictValueMapper.selectByExample(example);
      }
}
