package user.visual;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

import java.util.Random;
import java.util.StringTokenizer;

public class Captcha extends AnchorPane {

    private String CaptchaResult = "";

    public String getCaptchaResult() {
        return CaptchaResult;
    }

    public void setCaptchaResult(String captchaResult) {
        CaptchaResult = captchaResult;
    }

    Random r;
    int x;
    int y;
    int count;

    public Captcha(int x, int y, int count) {
        this.setStyle("-fx-backgrond-color:black;");
        this.x = x;
        this.y = y;
        this.count = count;
        this.setPrefSize(x, y);
        this.setMaxSize(x, y);
        this.setMinSize(x, y);
        r = new Random();
        String chars = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,1,2,3,4,5,6,7,8,9,0,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z";
        StringTokenizer tokenizer = new StringTokenizer(chars, ",");
        int i = 0;
        String[] cs = new String[tokenizer.countTokens()];
        while (tokenizer.hasMoreElements()) {
            cs[i] = (String) tokenizer.nextElement();
            i++;
        }
        for (int j = 0; j < count; j++) {
            String t = cs[r.nextInt(cs.length)];
            Label label = new Label(t);
            getField(label);
            AnchorPane pane = new AnchorPane(label);
            AnchorPane.setLeftAnchor(pane, (double) (j * (x / count)));

            this.getChildren().add(pane);

            pane.setPrefSize(x / count, y);
            setCaptchaResult(getCaptchaResult() + t);
        }
        drawings();
    }

    private void getField(Label t) {
        textRotate(t);
        fontChange(t);
        textTranslate(t);
    }

    private void fontChange(Label t) {
        t.setFont(Font.font(r.nextInt(12) + 18));
    }

    private void textRotate(Label t) {
        t.setRotate((r.nextDouble() * 50) - 25);
    }

    private void textTranslate(Label t) {
        int boundX = r.nextInt((int) ((y) - (t.getFont().getSize())));
        int boundY = (int) (r.nextInt((int) ((x / count) - (2 * t.getWidth())) - 5) + t.getWidth()) + 5;
        AnchorPane.setTopAnchor(t, (double) boundX);
        AnchorPane.setLeftAnchor(t, (double) boundY);
        t.setTextFill(Color.rgb(r.nextInt(156), r.nextInt(156), r.nextInt(156)));
    }

    private void drawings() {
        for (int i = 0; i < r.nextInt(3) + 1; i++) {
            Line line = new Line();
            line.setStartX(r.nextDouble() * x / 2);
            line.setStartY(r.nextDouble() * y);
            line.setEndY(r.nextDouble() * y);
            line.setEndX(r.nextDouble() * x / 2 + x / 2);
            line.setStroke(Color.rgb(r.nextInt(100), r.nextInt(100), r.nextInt(100)));
            this.getChildren().add(line);
            line.toFront();
        }
    }
}
