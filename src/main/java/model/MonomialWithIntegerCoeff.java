package model;

public class MonomialWithIntegerCoeff extends Monomial {
    Integer integerCoeff;

    public MonomialWithIntegerCoeff(Integer integerCoeff, Integer power) {
        super(power);
        this.integerCoeff = integerCoeff;
    }

    public Integer getIntegerCoeff() {
        return integerCoeff;
    }

    public void setIntegerCoeff(Integer integerCoeff) {
        this.integerCoeff = integerCoeff;
    }

    /**
     * @return The string which contains the polynomial
     */
    @Override
    public String toString() {
        if (getPower() != 1 && getPower() != 0) {
            if(getIntegerCoeff() > 0) {
                if(getIntegerCoeff() == 1){
                    return "+x^" + getPower();
                }
                else {
                    return "+" + integerCoeff + "x^" + getPower();
                }
            }
            else{
                return integerCoeff + "x^" + getPower();
            }
        } else if (getPower() == 1) {
            if(getIntegerCoeff() > 0) {
                if(getIntegerCoeff() == 1){
                    return "+x";
                }
                else {
                    return "+" + integerCoeff + "x";
                }
            }
            else{
                return integerCoeff + "x";
            }
        } else {
            if(getIntegerCoeff() > 0) {
                return "+" + integerCoeff + " ";
            }
            else{
                return integerCoeff + " ";
            }
        }
    }
}
