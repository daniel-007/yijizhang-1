package cn.ahyc.yjz.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import cn.ahyc.yjz.mapper.extend.SubjectBalanceExtendMapper;

/**
 * @ClassName: ExpressionThread
 * @Description: 公式计算线程
 * @author chengjiarui 1256064203@qq.com
 * @date 2015年10月27日 上午9:54:57
 * 
 */
public class ExpressionThread implements Callable<Long> {

    private String cell;

    private CountDownLatch latch;

    private Integer currentPeriod;

    private Long bookId;

    private SubjectBalanceExtendMapper subjectBalanceExtendMapper;

    public ExpressionThread() {
    }

    public ExpressionThread(String cell, CountDownLatch latch, Integer currentPeriod, Long bookId,
            SubjectBalanceExtendMapper subjectBalanceExtendMapper) {
        this.cell = cell;
        this.latch = latch;
        this.currentPeriod = currentPeriod;
        this.bookId = bookId;
        this.subjectBalanceExtendMapper = subjectBalanceExtendMapper;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.concurrent.Callable#call()
     */
    @Override
    public Long call() throws Exception {
        Long value = work();
        latch.countDown();
        System.out.println("while call " + cell);
        return value;
    }

    /**
     * 解析原子，账上取数
     */
    private Long work() {
        
        String subjectCodeStart;
        String subjectCodeEnd;
        String searchFeild = null;
        String year = null;
        Integer period = currentPeriod;

        // 会计科目
        Pattern pattern = Pattern.compile("<(.*?)>");
        Matcher matcher = pattern.matcher(cell);
        String subjectCode = matcher.group();
        if (StringUtils.contains(subjectCode, ":")) {
            subjectCodeStart = subjectCode.split(":")[0];
            subjectCodeEnd = subjectCode.split(":")[1];
        } else {
            subjectCodeStart = subjectCode;
            subjectCodeEnd = subjectCode;
        }
        // 取数类型
        pattern = Pattern.compile(">(.*?)@*");
        matcher = pattern.matcher(cell);
        if (matcher.find()) {
            searchFeild = matcher.group();
        }
        // 会计年度、会计期间
        pattern = Pattern.compile("@/((.*?),(.*?)/)");
        matcher = pattern.matcher(cell);
        if (matcher.find()) {
            year = matcher.group(1);
            period = Integer.valueOf(matcher.group(2));
            period = period < 0 ? currentPeriod + period : period;
        }
        pattern = Pattern.compile("@(^-?\\d+)$");
        matcher = pattern.matcher(cell);
        if (matcher.find()) {
            period = Integer.valueOf(matcher.group());
            period = period < 0 ? currentPeriod + period : period;
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("subjectCodeStart", subjectCodeStart);
        map.put("subjectCodeEnd", Integer.valueOf(subjectCodeEnd) + 1);
        map.put("searchFeild", searchFeild);
        map.put("year", year);
        map.put("period", period);
        map.put("bookId", bookId);
        return subjectBalanceExtendMapper.getExpressCellValueWithBook(map);
    }
}
