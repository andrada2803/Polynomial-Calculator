package model;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class Operations {
    @FXML
    Label reminder;
    /**
     * Addition or subtraction method. If the choice is true then it will perform addition, else it will perform the substraction.
     * @param choice true for Addition, false for Subtraction
     * @return the new Polynomial - the addition or subtraction
     */
    public Polynomial additionOrSubtraction(Polynomial polynomial1, Polynomial polynomial2, boolean choice) {
        ArrayList<Monomial> newPolynomial = new ArrayList<>();
        iterate(polynomial1, polynomial2, newPolynomial, choice);
        return new Polynomial(newPolynomial);
    }
    /**
     * Also the choice is to know which operation between the addition or subtraction is performed. Go through both polhynomials.
     * If the polynomial 1 has higher power of x, will be added to the result in polynomial, for both addition and subtraction.
     * If the polynomial 2 has higher power of x, will be added in case of addition and subtracted in case of subtraction.
     * If the powers are equal, in the case of addition, the coefficients will be added, else will the new coefficient will be the first coefficient - second coefficient
     * @param polynomial1
     * @param polynomial2
     * @param operatedMonomial
     * @param choice
     */
    public static void iterate(Polynomial polynomial1, Polynomial polynomial2, ArrayList<Monomial> operatedMonomial, boolean choice) {
        Integer index1 = 0, index2 = 0;
        while (index1 < polynomial1.getMonomials().size() && index2 < polynomial2.getMonomials().size()) {
            Monomial monomial1 = polynomial1.getMonomials().get(index1), monomial2 = polynomial2.getMonomials().get(index2);
            if (monomial1.getPower() > monomial2.getPower()) {
                operatedMonomial.add(monomial1);
                index1++;
            } else if (monomial1.getPower() < monomial2.getPower()) {
                if (!choice) operatedMonomial.add(new MonomialWithIntegerCoeff(-((MonomialWithIntegerCoeff) monomial2).getIntegerCoeff(), monomial2.getPower()));
                else operatedMonomial.add(monomial2);
                index2++;
            } else {
                Integer newCoefficent = 0;
                if (choice) newCoefficent = ((MonomialWithIntegerCoeff) monomial1).getIntegerCoeff() + ((MonomialWithIntegerCoeff) monomial2).getIntegerCoeff();
                else newCoefficent = ((MonomialWithIntegerCoeff) monomial1).getIntegerCoeff() - ((MonomialWithIntegerCoeff) monomial2).getIntegerCoeff();
                if (newCoefficent != 0) {
                    operatedMonomial.add(new MonomialWithIntegerCoeff(newCoefficent, monomial1.getPower()));
                }
                index1++;
                index2++;
            }
        }
        //While we still have elements in one of the polynomials
        while (index1 < polynomial1.getMonomials().size()) {
            operatedMonomial.add(polynomial1.getMonomials().get(index1));
            index1++;
        }
        while (index2 < polynomial2.getMonomials().size()) {
            operatedMonomial.add(polynomial2.getMonomials().get(index2));
            index2++;
        }
    }

    /**
     *The new coefficient will be the oold coefficient * the power, and the new power will be the old power - 1
     * @param polynomial1
     * @return The first polynomial after the derivation operation
     */
    public Polynomial derive (Polynomial polynomial1) {
        ArrayList<Monomial> derivedMonomials = new ArrayList<>();

        for (Monomial m1 : polynomial1.getMonomials()) {
            Integer derivedCoefficient = ((MonomialWithIntegerCoeff) m1).getIntegerCoeff();
            derivedCoefficient = derivedCoefficient * m1.getPower();
            Integer derivedPower = m1.getPower() - 1;
            if (derivedCoefficient != 0) {
                MonomialWithIntegerCoeff derivMoomial = new MonomialWithIntegerCoeff(derivedCoefficient, derivedPower);
                derivedMonomials.add(derivMoomial);
            }
        }
        return new Polynomial(derivedMonomials);
    }

    /**
     * The new coefficient will be the old coefficient over the (power + 1), and the new power will be the old power + 1
     * @param polynomial1
     * @return The polynomial after the integration operation
     */
    public Polynomial integrate(Polynomial polynomial1) {
        ArrayList<Monomial> integratedMonomials = new ArrayList<>();

        for (Monomial m1 : polynomial1.getMonomials()) {
            float coefficient = ((MonomialWithIntegerCoeff) m1).getIntegerCoeff();
            float integratedCoefficient = coefficient / (m1.getPower() + 1);
            Integer integratedPower = m1.getPower() + 1;
            if (integratedPower != 0) {
                MonomialWithRealCoeff integratedMonomial = new MonomialWithRealCoeff(integratedCoefficient, integratedPower);
                integratedMonomials.add(integratedMonomial);
            }
        }
        return new Polynomial(integratedMonomials);
    }

    /**
     * Go through the first polynomial, then with the monomial from the first polynomial I went through the second polynomial and multiply them,
     * and then put all the results into a polynomial. The returning polynomial is reduced in order to not have multiple elements
     * with the same power of x
     * @param polynomial1
     * @param polynomial2
     * @return The polynomial after the multiplication operation
     */
    public Polynomial multiply(Polynomial polynomial1, Polynomial polynomial2){
        ArrayList<Monomial> multipliedMonomials = new ArrayList<>();

        Integer index1 = 0, index2;

        while (index1 < polynomial1.getMonomials().size()){
            Monomial monomial1 = polynomial1.getMonomials().get(index1);
            index2 = 0;
            while (index2 < polynomial2.getMonomials().size()){
                Monomial monomial2 = polynomial2.getMonomials().get(index2);
                Integer multipliedCoefficient = ((MonomialWithIntegerCoeff)monomial1).getIntegerCoeff() * ((MonomialWithIntegerCoeff)monomial2).getIntegerCoeff();
                Integer multipliedPower = monomial1.getPower() + monomial2.getPower();
                MonomialWithIntegerCoeff multipliedMonomial = new MonomialWithIntegerCoeff(multipliedCoefficient, multipliedPower);
                multipliedMonomials.add(multipliedMonomial);
                index2++;
            }
            index1++;
        }
        return new Polynomial(multipliedMonomials).reducePolynomial();
    }

    /**
     * @param polynomial1
     * @param polynomial2
     * @return The multiplied polynomial with real coefficients
     */
    public Polynomial multiplyWithRealCoeff(Polynomial polynomial1, Polynomial polynomial2){
        ArrayList<Monomial> multipliedMonomials = new ArrayList<>();

        Integer index1 = 0, index2;

        while (index1 < polynomial1.getMonomials().size()){
            Monomial monomial1 = polynomial1.getMonomials().get(index1);
            index2 = 0;
            while (index2 < polynomial2.getMonomials().size()){
                Monomial monomial2 = polynomial2.getMonomials().get(index2);
                float multipliedCoefficient = ((MonomialWithIntegerCoeff)monomial1).getIntegerCoeff() * ((MonomialWithRealCoeff)monomial2).getRealCoeff();
                Integer multipliedPower = monomial1.getPower() + monomial2.getPower();
                MonomialWithRealCoeff multipliedMonomial = new MonomialWithRealCoeff(multipliedCoefficient, multipliedPower);
                multipliedMonomials.add(multipliedMonomial);
                index2++;
            }
            index1++;
        }
        return new Polynomial(multipliedMonomials).reducePolynomial();
    }

    /**
     *
     * @param polynomial1
     * @param polynomial2
     * @return The polynomial after the substraction with real coefficients
     */
    public Polynomial realSubtraction(Polynomial polynomial1, Polynomial polynomial2) {
        ArrayList<Monomial> newPolynomial = new ArrayList<>();

        iterate(polynomial1, polynomial2, newPolynomial);

        return new Polynomial(newPolynomial);
    }

    /**
     * Iterate with real coefficients
     * @param polynomial1
     * @param polynomial2
     * @param operatedMonomial
     */
    public static void iterate(Polynomial polynomial1, Polynomial polynomial2, ArrayList<Monomial> operatedMonomial) {
        Integer index1 = 0, index2 = 0;
        while (index1 < polynomial1.getMonomials().size() && index2 < polynomial2.getMonomials().size()) {
            Monomial monomial1 = polynomial1.getMonomials().get(index1), monomial2 = polynomial2.getMonomials().get(index2);
            if (monomial1.getPower() > monomial2.getPower()) {
                operatedMonomial.add(monomial1);
                index1++;
            } else if (monomial1.getPower() < monomial2.getPower()) {
                operatedMonomial.add(new MonomialWithRealCoeff(-((MonomialWithRealCoeff) monomial2).getRealCoeff(), monomial2.getPower()));
                index2++;
            } else {
                float newCoefficent = 0;
                if(monomial1 instanceof MonomialWithRealCoeff) newCoefficent = ((MonomialWithRealCoeff) monomial1).getRealCoeff() - ((MonomialWithRealCoeff) monomial2).getRealCoeff();
                else newCoefficent = ((MonomialWithIntegerCoeff) monomial1).getIntegerCoeff() - ((MonomialWithRealCoeff) monomial2).getRealCoeff();
                if (newCoefficent != 0) {
                    operatedMonomial.add(new MonomialWithRealCoeff(newCoefficent, monomial1.getPower()));
                }
                index1++;
                index2++;
            }
        }
        while (index1 < polynomial1.getMonomials().size()) {
            operatedMonomial.add(polynomial1.getMonomials().get(index1));
            index1++;
        }
        while (index2 < polynomial2.getMonomials().size()) {
            operatedMonomial.add(polynomial2.getMonomials().get(index2));
            index2++;
        }
    }

    /**
     * For this operation we look to the first element of the two polhynomials (in descending order of their power).
     * Check which polynomial has higher power in order to know how to do the division. While the polynomial which we divide is not empty
     * and still has a greater power, the division operation is performed. For this operation, real coefficients can appear, that is why
     * I used "instanceof" to know which type of monomial do I have. The coefficients are divided and the new power will be the higher power - the lower power.
     * The first polynomial will be updated: the old polynomial 1 - (the resulting monomial * polynomial 2).
     * @param polynomial1
     * @param polynomial2
     * @return The an ArrayList of polynomial with the result and the reminder of the division
     */
    public ArrayList<Polynomial> division(Polynomial polynomial1, Polynomial polynomial2) {
        ArrayList<Polynomial> divisionResult = new ArrayList<>();
        ArrayList<Monomial> dividedMonomisls = new ArrayList<>();
        Polynomial divPolynomial1, divPolynomial2;
        if (polynomial1.getMonomials().get(0).getPower() >= polynomial2.getMonomials().get(0).getPower()) {
            divPolynomial1 = polynomial1;
            divPolynomial2 = polynomial2;
        } else {
            divPolynomial1 = polynomial2;
            divPolynomial2 = polynomial1;
        }
        while (!divPolynomial1.getMonomials().isEmpty() && (divPolynomial1.getMonomials().get(0).getPower() >= divPolynomial2.getMonomials().get(0).getPower())) {
            float dividedCoefficient;
            Monomial monomial1 = divPolynomial1.getMonomials().get(0);
            Monomial monomial2 = divPolynomial2.getMonomials().get(0);
            if (divPolynomial1.getMonomials().get(0) instanceof MonomialWithIntegerCoeff) {
                dividedCoefficient = ((float) ((MonomialWithIntegerCoeff) monomial1).getIntegerCoeff()) / ((MonomialWithIntegerCoeff) monomial2).getIntegerCoeff();
            } else {
                dividedCoefficient = ((float) ((MonomialWithRealCoeff) monomial1).getRealCoeff()) / ((MonomialWithIntegerCoeff) monomial2).getIntegerCoeff();
            }
            Integer dividedPower = monomial1.getPower() - monomial2.getPower();
            MonomialWithRealCoeff divMonomial = new MonomialWithRealCoeff(dividedCoefficient, dividedPower);
            dividedMonomisls.add(divMonomial);
            ArrayList<Monomial> multiplyMonom = new ArrayList<>();
            multiplyMonom.add(divMonomial);
            Polynomial monomialToBeMultiplied= new Polynomial(multiplyMonom);
            Polynomial subtractFromTheFirstPolynomial;
            subtractFromTheFirstPolynomial = multiplyWithRealCoeff(divPolynomial2, monomialToBeMultiplied);
            divPolynomial1 = realSubtraction(divPolynomial1, subtractFromTheFirstPolynomial);
        }
        Polynomial reminder = divPolynomial1.reducePolynomial();
        Polynomial result = new Polynomial(dividedMonomisls).reducePolynomial();
        divisionResult.add(result);
        divisionResult.add(reminder);
        return divisionResult;
    }
}
