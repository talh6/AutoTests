import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BasicActions {
    private Robot robot;
    private Map spacialChars;

    public BasicActions(){
        try{
            robot = new Robot();
            spacialChars = new HashMap();
            spacialChars.put("_","VK_UNDERSCORE");
            spacialChars.put("/","VK_SLASH");
            spacialChars.put(";","VK_SEMICOLON");
            spacialChars.put("=","VK_EQUALS");
            spacialChars.put(")","VK_RIGHT_PARENTHESIS");
            spacialChars.put("(","VK_LEFT_PARENTHESIS");
            spacialChars.put("+","VK_PLUS");
            spacialChars.put(".","VK_PERIOD");
            spacialChars.put("[","VK_OPEN_BRACKET");
            spacialChars.put("]","VK_CLOSE_BRACKET");
            spacialChars.put("#","VK_NUMBER_SIGN");
            spacialChars.put("-","VK_MINUS");
            spacialChars.put("!","VK_EXCLAMATION_MARK");
            spacialChars.put("$","VK_DOLLAR");
            spacialChars.put(",","VK_COMMA");
            spacialChars.put(":","VK_COLON");
            spacialChars.put("^","VK_CIRCUMFLEX");
            //spacialChars.put("\","VK_BACK_SLASH"");
            spacialChars.put("@","VK_AT");
        }catch (Exception e){

        }
    }

    public void takeScreenShot(String name) {
        try {
            String format = ".jpg";
            String fileName = name + format;

            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
            ImageIO.write(screenFullImage, format, new File(fileName));

            System.out.println("A full screenshot saved!");

        } catch (Exception e) {
            System.err.println(e);
        }

    }

    public void sendText(int row,int col,String text){
        try{
            robot.mouseMove(row, col);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.delay(200);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            robot.delay(500);
            type(text);

        }
        catch (Exception e){

        }
    }

    public void mouseLeftClick(int row,int col){
        try{
            robot.mouseMove(row, col);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        }
        catch (Exception e){

        }
    }

    public void mouseDoubleClickint(int row,int col){
        try{
            robot.mouseMove(row, col);
            // first click
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            // second click
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        }
        catch (Exception e){

        }
    }

    public void mouseRightClick(int row,int col){
        try{
            robot.mouseMove(row, col);
            robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
        }
        catch (Exception e){

        }
    }

    private void typeCharacter(String s){
        try {
            boolean upperCase = Character.isUpperCase( s.charAt(0) );
            String variableName;
            String alfabeticPattern="^[a-zA-Z0-9_.-]*$";
            Pattern r = Pattern.compile(alfabeticPattern);
            Matcher m = r.matcher(s);
            boolean isAlfabetic = m.find();

            if(!isAlfabetic){
                if(spacialChars.containsKey(s)){
                    variableName = (String) spacialChars.get(s);
                }
                else{
                    variableName ="Not implementeed yet";
                }
            }
            else{
                variableName = "VK_" + s.toUpperCase();
            }


            Class clazz = KeyEvent.class;
            Field field = clazz.getField( variableName );
            System.out.println(variableName);
            int keyCode = field.getInt(null);


            if (upperCase) robot.keyPress( KeyEvent.VK_SHIFT );

            robot.keyPress( keyCode );
            robot.keyRelease( keyCode );

            if (upperCase) robot.keyRelease( KeyEvent.VK_SHIFT );
            }
        catch(Exception e) {
        System.out.println(e);
        }
    }

    private void type(String s)
    {
        try {
            for (int i=0 ;i<s.length();i++){
                String charecter = s.substring(i,i+1);
                typeCharacter(charecter);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
}
