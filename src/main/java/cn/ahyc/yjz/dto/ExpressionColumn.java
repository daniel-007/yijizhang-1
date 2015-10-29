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
     * @param cD
     * @param cE
     * @param cF
     * @param cG
     * @param cH
     */
    public ExpressionColumn(String cA, String cB, String cC, String cD, String cE, String cF, String cG, String cH) {
        this.cA = cA;
        this.cB = cB;
        this.cC = cC;
        this.cD = cD;
        this.cE = cE;
        this.cF = cF;
        this.cG = cG;
        this.cH = cH;
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
}
