package model;

import java.security.PublicKey;
import java.util.ArrayList;

public class Polynomial {
    private ArrayList<Monomial> monomials;

    public Polynomial(ArrayList<Monomial> monomials) {
        this.monomials = monomials;
    }

    public ArrayList<Monomial> getMonomials() {
        return monomials;
    }

    public void setMonomials(ArrayList<Monomial> monomials) {
        this.monomials = monomials;
    }

    public Integer getMaxPower(){
        Integer maxPower = 0;

        for (Monomial monomial : monomials) {
            if(monomial.getPower() > maxPower){
                maxPower = monomial.getPower();
            }
        }
        return maxPower;
    }

    /**
     * Adds the monomials which have the same power of x
     * @return The reduced polynomial
     */
    public Polynomial reducePolynomial(){
        Integer maxPower = getMaxPower();
        ArrayList<Monomial> reducedMonomials = new ArrayList<>();

        while (maxPower >=0) {
            Integer integerCoefficient = 0;
            float realCoefficient = 0;
            for (Monomial monomial : monomials) {
                if(monomial.getPower().equals(maxPower)){
                    if(monomial instanceof MonomialWithIntegerCoeff)
                    {
                        integerCoefficient = integerCoefficient + ((MonomialWithIntegerCoeff)monomial).getIntegerCoeff();
                    }
                    else{
                        realCoefficient = realCoefficient + ((MonomialWithRealCoeff)monomial).getRealCoeff();

                    }
                }
            }

            if(integerCoefficient != 0){
                MonomialWithIntegerCoeff reducedMonimial = new MonomialWithIntegerCoeff(integerCoefficient, maxPower);
                reducedMonomials.add(reducedMonimial);
            }

            if(realCoefficient != 0){
                MonomialWithRealCoeff reducedMonimial = new MonomialWithRealCoeff(realCoefficient, maxPower);
                reducedMonomials.add(reducedMonimial);
            }

            maxPower--;
        }

        return new Polynomial(reducedMonomials);
    }

    /**
     * @return The polynomial as a string
     */
    @Override
    public String toString() {
        String polynomial = " ";

        for (Monomial monomial : monomials) {
            polynomial = polynomial.concat(monomial.toString());
        }
        return polynomial;
    }
}
