package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Monomial;
import model.MonomialWithIntegerCoeff;
import model.Operations;
import model.Polynomial;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {
    @FXML
    private TextField firstPol;
    @FXML
    private TextField secondPol;
    @FXML
    private TextField resultingPol;
    @FXML
    private Button writeInPol1 = new Button();
    @FXML
    private Button writeInPol2 = new Button();
    private boolean writeIn;

    /**
     * Check if the polynomials are written correctly, and in the case they are not, an allert will pop on the screen
     * @param polynomial1
     * @param polynomial2
     * @return 1 if the polynomials is introduced correctly and 0 otherwise
     */
    public Integer validate(Polynomial polynomial1, Polynomial polynomial2) {
        Integer valid = 1;
        if (!(" " + firstPol.getText() + " ").equals(polynomial1.toString())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Ooops, looks like the first polynomial is written incorrectly");
            valid = 0;
            alert.showAndWait();
        } else if (!(" " + secondPol.getText() + " ").equals(polynomial2.toString())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Ooops, looks like the second polynomial is written incorrectly");
            valid = 0;
            alert.showAndWait();
        }
        return valid;
    }

    /**
     * @param poli
     * @return The polynomial after dividing the initial polynomial into monomials in order to obtain the coefficients
     *         and the powers
     */
    public Polynomial parsePolynomial(TextField poli) {
        Pattern pattern = Pattern.compile("([+-]\\d*x?)(\\^\\d+)?", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(poli.getText());
        ArrayList<Monomial> monomials = new ArrayList<>();

        while (matcher.find()) {
            String coefficient = matcher.group(1);
            String power = matcher.group(2);

            if (power == null) {
                if (coefficient.contains("x")) {
                    power = "1";
                } else {
                    power = "0";
                }
            } else {
                power = power.substring(1, power.length());
            }

            if (coefficient.contains("x")) {
                coefficient = coefficient.substring(0, coefficient.length() - 1);
                if (coefficient.equals("+") || coefficient.equals("-")) {
                    coefficient = coefficient.concat("1");
                }
            }

            Integer coef = Integer.parseInt(coefficient);
            Integer pow = Integer.parseInt(power);

            MonomialWithIntegerCoeff monomial = new MonomialWithIntegerCoeff(coef, pow);
            monomials.add(monomial);
        }

        return new Polynomial(monomials);
    }

    /**
     * The next 6 methods compute the result and set the field of the result for all the operations
     * @param event
     */
    public void additionView(ActionEvent event) {
        Polynomial polynomial1 = parsePolynomial(firstPol);
        Polynomial polynomial2 = parsePolynomial(secondPol);
        if (validate(polynomial1, polynomial2) == 1) {
            Operations operations = new Operations();

            polynomial1 = polynomial1.reducePolynomial();
            polynomial2 = polynomial2.reducePolynomial();
            resultingPol.setText(operations.additionOrSubtraction(polynomial1, polynomial2, true).toString());
        }
    }

    public void subtractionView(ActionEvent event) {
        Polynomial polynomial1 = parsePolynomial(firstPol);
        Polynomial polynomial2 = parsePolynomial(secondPol);
        if (validate(polynomial1, polynomial2) == 1) {
            Operations operations = new Operations();

            polynomial1 = polynomial1.reducePolynomial();
            polynomial2 = polynomial2.reducePolynomial();
            resultingPol.setText(operations.additionOrSubtraction(polynomial1, polynomial2, false).toString());
        }
    }

    public void multiplicationView(ActionEvent event) {
        Polynomial polynomial1 = parsePolynomial(firstPol);
        Polynomial polynomial2 = parsePolynomial(secondPol);
        if (validate(polynomial1, polynomial2) == 1) {
            Operations operations = new Operations();

            polynomial1 = polynomial1.reducePolynomial();
            polynomial2 = polynomial2.reducePolynomial();
            resultingPol.setText(operations.multiply(polynomial1, polynomial2).toString());
        }
    }

    public void divisionView(ActionEvent event) {
        Polynomial polynomial1 = parsePolynomial(firstPol);
        Polynomial polynomial2 = parsePolynomial(secondPol);
        if (validate(polynomial1, polynomial2) == 1) {
            Operations operations = new Operations();

            polynomial1 = polynomial1.reducePolynomial();
            polynomial2 = polynomial2.reducePolynomial();
            resultingPol.setText(operations.division(polynomial1, polynomial2).get(0).toString() + "reminder: " + operations.division(polynomial1, polynomial2).get(1).toString());
        }
    }

    public void derivationView(ActionEvent event) {
        Polynomial polynomial1 = parsePolynomial(firstPol);
        Polynomial polynomial2 = parsePolynomial(secondPol);
        if (validate(polynomial1, polynomial2) == 1) {
            Operations operations = new Operations();

            polynomial1 = polynomial1.reducePolynomial();
            resultingPol.setText(operations.derive(polynomial1).toString());
        }
    }

    public void integrationView(ActionEvent event) {
        Polynomial polynomial1 = parsePolynomial(firstPol);
        Polynomial polynomial2 = parsePolynomial(secondPol);
        if (validate(polynomial1, polynomial2) == 1) {
            Operations operations = new Operations();

            polynomial1 = polynomial1.reducePolynomial();
            resultingPol.setText(operations.integrate(polynomial1).toString());
        }
    }
    /**
     * The next 14 methods write in the text field the text which is written on the button
     * @param event
     */
    public void putValueOnB0(ActionEvent event) {
        if (writeIn == true) {
            String text = firstPol.getText() + "0";
            firstPol.setText(text);
        } else {
            String text = secondPol.getText() + "0";
        }
    }
    public void putValueOnB1(ActionEvent event) {
        if (writeIn == true) {
            String text = firstPol.getText() + "1";
            firstPol.setText(text);
        } else {
            String text = secondPol.getText() + "1";
            secondPol.setText(text);
        }
    }
    public void putValueOnB2(ActionEvent event) {
        if (writeIn == true) {
            String text = firstPol.getText() + "2";
            firstPol.setText(text);
        } else {
            String text = secondPol.getText() + "2";
            secondPol.setText(text);
        }
    }
    public void putValueOnB3(ActionEvent event) {
        if (writeIn == true) {
            String text = firstPol.getText() + "3";
            firstPol.setText(text);
        } else {
            String text = secondPol.getText() + "3";
            secondPol.setText(text);
        }
    }
    public void putValueOnB4(ActionEvent event) {
        if (writeIn == true) {
            String text = firstPol.getText() + "4";
            firstPol.setText(text);
        } else {
            String text = secondPol.getText() + "4";
            secondPol.setText(text);
        }
    }
    public void putValueOnB5(ActionEvent event) {
        if (writeIn == true) {
            String text = firstPol.getText() + "5";
            firstPol.setText(text);
        } else {
            String text = secondPol.getText() + "5";
            secondPol.setText(text);
        }
    }
    public void putValueOnB6(ActionEvent event) {
        if (writeIn == true) {
            String text = firstPol.getText() + "6";
            firstPol.setText(text);
        } else {
            String text = secondPol.getText() + "6";
            secondPol.setText(text);
        }
    }
    public void putValueOnB7(ActionEvent event) {
        if (writeIn == true) {
            String text = firstPol.getText() + "7";
            firstPol.setText(text);
        } else {
            String text = secondPol.getText() + "7";
            secondPol.setText(text);
        }
    }
    public void putValueOnB8(ActionEvent event) {
        if (writeIn == true) {
            String text = firstPol.getText() + "8";
            firstPol.setText(text);
        } else {
            String text = secondPol.getText() + "8";
            secondPol.setText(text);
        }
    }
    public void putValueOnB9(ActionEvent event) {
        if (writeIn == true) {
            String text = firstPol.getText() + "9";
            firstPol.setText(text);
        } else {
            String text = secondPol.getText() + "9";
            secondPol.setText(text);
        }
    }
    public void putValueOnBadd(ActionEvent event) {
        if (writeIn == true) {
            String text = firstPol.getText() + "+";
            firstPol.setText(text);
        } else {
            String text = secondPol.getText() + "+";
            secondPol.setText(text);
        }
    }
    public void putValueOnBsub(ActionEvent event) {
        if (writeIn == true) {
            String text = firstPol.getText() + "-";
            firstPol.setText(text);
        } else {
            String text = secondPol.getText() + "-";
            secondPol.setText(text);
        }
    }
    public void putValueOnBpow(ActionEvent event) {
        if (writeIn == true) {
            String text = firstPol.getText() + "^";
            firstPol.setText(text);
        } else {
            String text = secondPol.getText() + "^";
            secondPol.setText(text);
        }
    }
    public void putValueOnBx(ActionEvent event) {
        if (writeIn == true) {
            String text = firstPol.getText() + "x";
            firstPol.setText(text);
        } else {
            String text = secondPol.getText() + "x";
            secondPol.setText(text);
        }
    }
    /**
     * I implemented those two methods in order to know in which polynomial the value on the buttons will be written
     * @param event
     */
    public void putValueOnPol1(ActionEvent event) {
        writeIn = true;
    }
    public void putValueOnPol2(ActionEvent event) {
        writeIn = false;
    }
}