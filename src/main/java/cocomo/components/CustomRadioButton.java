package cocomo.components;

import javafx.scene.control.RadioButton;

public class CustomRadioButton extends RadioButton {
    private double value;

    public CustomRadioButton(String text) {
        super(text);
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}