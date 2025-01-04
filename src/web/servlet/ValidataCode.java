package web.servlet;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class ValidataCode extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
    {
        BufferedImage image = new BufferedImage(100, 30, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        Random random = new Random();
        g.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
        g.fillRect(0, 0, 100, 30);
        String number = getNumber();
        HttpSession session = request.getSession();
        session.setAttribute("code", number);
        g.setColor(new Color(0,0,0));
        g.setFont(new Font(null, Font.ITALIC+Font.BOLD, 24));
        g.drawString(number, 5, 25);
        for (int i = 0; i < 8; i++) {
            g.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
            g.drawLine(random.nextInt(100), random.nextInt(30), random.nextInt(100), random.nextInt(30));
        }

        response.setContentType("image/jpeg");
        OutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpeg", out);
        out.close();
    }

    public String getNumber(){
        char[] word = {'A','B','C','D','E','F','G','h','i','j','k','l','m','n','1','2','3','4','5','6','7','8','9'};
        String strs = "";
        Random r = new Random();
        for (int i = 0; i < 5; i++) {
            int randomNum = r.nextInt(word.length);
            strs += word[randomNum];
        }
        return strs;
    }
}
