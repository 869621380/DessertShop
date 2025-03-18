package org.example.dessertshopspringboot.Utils;

import org.springframework.data.redis.core.StringRedisTemplate;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class ValidateCodeUtils {

    private static final int IMAGE_WIDTH = 100;
    private static final int IMAGE_HEIGHT = 30;
    private static final int CODE_LENGTH = 4;
    private static final int LINE_COUNT = 8;
    private static final char[] WORD = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'h', 'i', 'j', 'k', 'l', 'm', 'n', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    /**
     * 生成验证码图片并将其写入输出流
     *
     * @param outputStream 输出流，用于输出验证码图片
     * @return 生成的验证码字符串
     * @throws IOException 当写入输出流时发生 I/O 错误
     */
    public static String generateValidateCodeImage(OutputStream outputStream) throws IOException {
        // 创建验证码图片
        BufferedImage image = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        Random random = new Random();

        // 绘制背景
        g.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
        g.fillRect(0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);

        // 生成验证码
        String code = getNumber();

        // 绘制验证码
        g.setColor(new Color(0, 0, 0));
        g.setFont(new Font(null, Font.ITALIC + Font.BOLD, 24));
        g.drawString(code, 5, 25);

        // 绘制干扰线
        for (int i = 0; i < LINE_COUNT; i++) {
            g.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
            g.drawLine(random.nextInt(IMAGE_WIDTH), random.nextInt(IMAGE_HEIGHT), random.nextInt(IMAGE_WIDTH), random.nextInt(IMAGE_HEIGHT));
        }

        // 将图片写入输出流
        ImageIO.write(image, "jpeg", outputStream);
        return code;
    }

    /**
     * 生成指定长度的验证码字符串
     *
     * @return 生成的验证码字符串
     */
    private static String getNumber() {
        Random r = new Random();
        StringBuilder strs = new StringBuilder();
        for (int i = 0; i < CODE_LENGTH; i++) {
            int randomNum = r.nextInt(WORD.length);
            strs.append(WORD[randomNum]);
        }
        return strs.toString();
    }

    public static String checkValidateCode(String code,String codeKey) {
        String storedCode=RedisUtil.get(codeKey);
        if(storedCode==null){
            return "验证码已过期";
        }
        else if(!code.equalsIgnoreCase(storedCode)){
            return "验证码错误";
        }
        else {
            RedisUtil.delete(code);
            return "success";
        }
    }
}