package tools;

import java.awt.*;
import java.awt.event.*;
import javafx.beans.value.*;
import javafx.scene.control.TextField;

public class Limiter {

    // public static void Limit(TextField[] textFields , int maxLength, boolean prevent) {
    //     for (TextField textField : textFields) {
    //         Limit(textField, maxLength, prevent);
    //     }
    // }

    public static void Limit(TextField textField, int maxLength, boolean prevent) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue,
                    final String newValue) {
                if (textField.getText().length() > maxLength) {
                    textField.setText(textField.getText().substring(0, maxLength));
                }
                if (textField.getText().length() == maxLength) {
                    PressTab();
                }
                if (prevent) {
                    PreventWord(textField);
                }
            }
        });
    }

    private static void PressTab() {
        try {
            Robot Switcher = new Robot();
            Switcher.keyPress(KeyEvent.VK_TAB);
            Switcher.keyRelease(KeyEvent.VK_TAB);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    private static void PreventWord(TextField tf) {
        String txt = tf.getText();
        if (txt.length() > 0 && !(txt.charAt(txt.length() - 1) >= 48 && txt.charAt(txt.length() - 1) <= 57)) {
            tf.setText(txt.substring(0, txt.length() - 1));
        }
    }
}
