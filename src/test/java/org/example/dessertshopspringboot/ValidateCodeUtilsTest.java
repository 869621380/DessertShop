package org.example.dessertshopspringboot;

import org.example.dessertshopspringboot.Utils.ValidateCodeUtils;

import org.junit.jupiter.api.Test;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

import static org.junit.jupiter.api.Assertions.*;

public class ValidateCodeUtilsTest {

    @Test
    public void testGenerateValidateCodeImage() throws IOException {
        // 创建一个 ByteArrayOutputStream 来模拟输出流
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // 调用工具类的方法生成验证码图片
        String code = ValidateCodeUtils.generateValidateCodeImage(outputStream);

        // 验证生成的验证码长度是否符合预期
        assertEquals(5, code.length());

        // 验证输出流中是否包含有效的图片数据
        byte[] imageBytes = outputStream.toByteArray();
        BufferedImage image = ImageIO.read(new java.io.ByteArrayInputStream(imageBytes));
        assertNotNull(image);
    }
}