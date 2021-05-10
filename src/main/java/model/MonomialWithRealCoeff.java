package model;

public class MonomialWithRealCoeff extends Monomial {
    private float realCoeff;

    public MonomialWithRealCoeff(float realCoeff, Integer power) {
        super(power);
        this.realCoeff = realCoeff;
    }

    public float getRealCoeff() {
        return realCoeff;
    }

    public void setRealCoeff(float realCoeff) {
        this.realCoeff = realCoeff;
    }

    /**
     * @return The string which contains the polynomial
     */
    @Override
    public String toString() {
        if (getPower() != 1 && getPower() != 0) {
            if (getRealCoeff() > 0) {
                if (getRealCoeff() == 1.0) { return "+x^" + getPower();
                } else { if (getRealCoeff() == (int) getRealCoeff()) {
                            return "+" + (int) realCoeff + "x^" + getPower();
                    }   else {
                            return "+" + realCoeff + "x^" + getPower();
                    }
                }
            } else {
                if (getRealCoeff() == (int) getRealCoeff()) {  return (int) realCoeff + "x^" + getPower();
                } else {  return realCoeff + "x^" + getPower();
                }
            }
        } else if (getPower() == 1) {
            if (getRealCoeff() > 0) {
                if (getRealCoeff() == 1.0) return "+x";
                if (getRealCoeff() == (int) getRealCoeff()) {  return "+" + (int) realCoeff + "x";
                } else {  return "+" + realCoeff + "x";
                }
            } else { if (getRealCoeff() == (int) (getRealCoeff())) {
                        return (int) realCoeff + "x";
                }   else {
                        return realCoeff + "x";
                }
            }
        } else {
            if (getRealCoeff() > 0) {
                if (getRealCoeff() == (int) (getRealCoeff())) {
                    return "+" + (int) realCoeff + " ";
                } else {
                    return "+" + realCoeff + " ";
                }
            } else {
                if (getRealCoeff() == (int) getRealCoeff()) { return +(int) realCoeff + " ";
                } else{ return +realCoeff + " ";
                }
            }
        }
    }
}
