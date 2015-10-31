/**
 * @Title: ExpressionColumn.java 
 * @Package cn.ahyc.yjz.dto 
 * @Description: TODO
 * @author chengjiarui 1256064203@qq.com
 * @date 2015年10月27日 下午6:49:27 
 * @version V1.0   
 */
package cn.ahyc.yjz.dto;

/**
 * @ClassName: ExpressionColumn
 * @Description: TODO
 * @author chengjiarui 1256064203@qq.com
 * @date 2015年10月27日 下午6:49:27
 * 
 */
public class ExpressionColumn {

    private String cA;
    private String cB;
    private String cC;
    private String cD;
    private String cE;
    private String cF;
    private String cG;
    private String cH;

    private String cAVal;
    private String cBVal;
    private String cCVal;
    private String cDVal;
    private String cEVal;
    private String cFVal;
    private String cGVal;
    private String cHVal;

    /**
     * 
     */
    public ExpressionColumn() {
    }

    @Override
    public String toString() {
        return "ExpressionColumn [cA=" + cA + ", cB=" + cB + ", cC=" + cC + ", cAVal=" + cAVal + ", cBVal=" + cBVal
                + ", cCVal=" + cCVal + "]";
    }

    /**
     * @param cA
     * @param cB
     * @param cC
     */
    public ExpressionColumn(String cA, String cB, String cC) {
        this.cA = cA;
        this.cB = cB;
        this.cC = cC;
    }

    /**
     * @param cA
     * @param cB
     * @param cC
     * @param cAVal
     * @param cBVal
     * @param cCVal
     */
    public ExpressionColumn(String cA, String cB, String cC, String cAVal, String cBVal, String cCVal) {
        this.cA = cA;
        this.cB = cB;
        this.cC = cC;
        this.cAVal = cAVal;
        this.cBVal = cBVal;
        this.cCVal = cCVal;
    }

    public String getcA() {
        return cA;
    }

    public void setcA(String cA) {
        this.cA = cA;
    }

    public String getcB() {
        return cB;
    }

    public void setcB(String cB) {
        this.cB = cB;
    }

    public String getcC() {
        return cC;
    }

    public void setcC(String cC) {
        this.cC = cC;
    }

    public String getcD() {
        return cD;
    }

    public void setcD(String cD) {
        this.cD = cD;
    }

    public String getcE() {
        return cE;
    }

    public void setcE(String cE) {
        this.cE = cE;
    }

    public String getcF() {
        return cF;
    }

    public void setcF(String cF) {
        this.cF = cF;
    }

    public String getcG() {
        return cG;
    }

    public void setcG(String cG) {
        this.cG = cG;
    }

    public String getcH() {
        return cH;
    }

    public void setcH(String cH) {
        this.cH = cH;
    }

    public String getcAVal() {
        return cAVal;
    }

    public void setcAVal(String cAVal) {
        this.cAVal = cAVal;
    }

    public String getcBVal() {
        return cBVal;
    }

    public void setcBVal(String cBVal) {
        this.cBVal = cBVal;
    }

    public String getcCVal() {
        return cCVal;
    }

    public void setcCVal(String cCVal) {
        this.cCVal = cCVal;
    }

    public String getcDVal() {
        return cDVal;
    }

    public void setcDVal(String cDVal) {
        this.cDVal = cDVal;
    }

    public String getcEVal() {
        return cEVal;
    }

    public void setcEVal(String cEVal) {
        this.cEVal = cEVal;
    }

    public String getcFVal() {
        return cFVal;
    }

    public void setcFVal(String cFVal) {
        this.cFVal = cFVal;
    }

    public String getcGVal() {
        return cGVal;
    }

    public void setcGVal(String cGVal) {
        this.cGVal = cGVal;
    }

    public String getcHVal() {
        return cHVal;
    }

    public void setcHVal(String cHVal) {
        this.cHVal = cHVal;
    }
}
