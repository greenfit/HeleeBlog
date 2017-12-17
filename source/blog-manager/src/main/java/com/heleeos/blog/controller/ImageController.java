package com.heleeos.blog.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heleeos.blog.util.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 图片相关的控制器
 * Created with Li Yu on 2017/12/17.
 *
 * @author liyu
 * @author kissaoe@gmail.com
 * @version 1.0.1
 */
@RestController
@RequestMapping("image")
public class ImageController {
    
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("#{configProperties.image_host}")
    private String imageHost;
    
    /**
     * 获取验证码
     * 
     */
    @RequestMapping("/code.jpg")
    public ModelAndView getImageCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        String text = createCaptcha();
        SessionUtil.saveCaptchaToSession(request, text);
        ImageIO.write(createImage(text), "jpg", response.getOutputStream());
        try {
            response.getOutputStream().flush();
        } finally {
            response.getOutputStream().close();
        }
        return null;
    }

    /**
     * 上传图片的界面.
     *
     * 参数: path 存放upload下的二级目录
     */
    @RequestMapping(value = "add.html")
    public ModelAndView addImage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("image/add");
        String path = request.getParameter("path");
        if ("advertise".equals(path) || "profile".equals(path)) {
            modelAndView.addObject("path", path);
        } else {
            modelAndView.addObject("path", "unsafe");
        }
        return modelAndView;
    }

    /**
     * 生成随机数
     */
    private String createCaptcha() {
        Random random = new Random();
        return random.nextInt(10) + "" + random.nextInt(10) + "" + random.nextInt(10) + "" + random.nextInt(10);
    }

    /**
     * 生成验证码图像
     */
    private BufferedImage createImage(String text) {
        int width = 100;
        int height = 35;

        BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = buffImg.createGraphics();
        Random random = new Random();
        graphics.setColor(new Color(220, 220, 220));
        graphics.fillRect(0, 0, width, height);
        graphics.setColor(new Color(50, 120, 204));
        for (int i = 0; i < 30; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(width / 2);
            int yl = random.nextInt(height / 2);
            graphics.drawLine(x, y, x + xl, y + yl);
        }

        Font font = new Font("Segoe Print", Font.PLAIN, 35);
        graphics.setFont(font);
        graphics.setColor(new Color(50, 120, 204));
        for (int i = 0; i < text.length(); i++) {
            graphics.drawString(text.charAt(i) + "", i * 25, 30);
        }
        return buffImg;
    }



}
