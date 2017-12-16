package com.heleeos.blog.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.heleeos.blog.bean.Result;
import com.heleeos.blog.common.SessionKey;

/**
 * 图片相关的控制器.
 * 
 * @author liyu
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
        request.getSession().setAttribute(SessionKey.SESSION_CPTCHA_KEY, text);
        ImageIO.write(createImage(text), "jpg", response.getOutputStream());
        try {
            response.getOutputStream().flush();
        } finally {
            response.getOutputStream().close();
        }
        return null;
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
     * CKEditor编辑器所使用的上传图片.
     * 
     * 返回结果是一段js代码,详情查看方文档 http://docs.ckeditor.com/#!/guide/dev_file_upload
     */
    @RequestMapping(value = "/ck-upload", method = RequestMethod.POST)
    public void upload(HttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile upload) {
        response.setContentType("text/html;charset=UTF-8");
        String path = request.getParameter("path");
        String CKEditorFuncNum = request.getParameter("CKEditorFuncNum");
        StringBuffer buffer = new StringBuffer("<script type=\"text/javascript\">");
        try {
            if (StringUtils.trimToNull(path) == null) {
                path = "";
            } else {
                path = path + "/";
            }
            String dir = request.getServletContext().getRealPath("upload") + "/" + path;
            String name = UUID.randomUUID().toString().replaceAll("-", "") + "." + getExtName(upload);
            boolean bol = saveFile(dir, name, upload);
            if (bol) {
                String filepath;
                if (StringUtils.trimToNull(imageHost) == null || imageHost.equalsIgnoreCase("CONTEXT")) {
                    filepath = request.getContextPath() + "/upload/" + path + name;
                } else {
                    filepath = imageHost + "/" + path + name;
                }
                buffer.append("window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum + ",'" + filepath + "');");
            } else {
                buffer.append("window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum + ",'', '文件复制失败!');");
            }
        } catch (Exception e) {
            logger.error("上传文件失败,原因:{}", e.getMessage());
            buffer.append("window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum + ",'', '上传失败!');");
        }
        buffer.append("</script>");
        try {
            response.getWriter().write(buffer.toString());
            response.getWriter().flush();
        } catch (IOException e) {}
    }

    /**
     * 上传图片.
     * 
     * 由于使用的是MultipartFile,所以调用的页面必须是表单,并且名字必须是upload.
     * 
     * 示例如下: <form enctype="multipart/form-data" method="post">
     * <input id="upload" name="upload" type="file"> </form>
     */
    @RequestMapping(value = "/upload.json", method = RequestMethod.POST)
    public Result uploadImage(HttpServletRequest request, @RequestParam MultipartFile upload) {
        Result result = new Result();
        String path = request.getParameter("path");
        try {
            if (StringUtils.trimToNull(path) == null) {
                path = "";
            } else {
                path = path + "/";
            }
            String dir = request.getServletContext().getRealPath("upload") + "/" + path;
            String name = UUID.randomUUID().toString().replaceAll("-", "") + "." + getExtName(upload);
            boolean bol = saveFile(dir, name, upload);
            if (bol) {
                String filepath = path + name;
                result.setCode(0);
                result.putMessage("host", imageHost);
                result.putMessage("file", filepath);
            } else {
                result.setCode(0);
                result.putInfo("复制失败!");
            }
        } catch (Exception e) {
            logger.error("上传文件失败,原因:{}", e.getMessage());
            result.setCode(0);
            result.putInfo("上传失败!");
        }
        return result;
    }

    /**
     * 保存文件.
     * 
     * @param dir文件路径
     * @param name 文件名
     * @param upload 上传的文件
     */
    private boolean saveFile(String dir, String name, MultipartFile upload) {
        File target = new File(dir, name);
        target.getParentFile().mkdirs();
        try {
            Files.copy(upload.getInputStream(), target.toPath());
            return true;
        } catch (Exception e) {
            logger.error("上传文件复制失败,原因:" + e.getMessage());
            return false;
        }
    }

    /**
     * 获取文件扩展名.
     */
    private String getExtName(MultipartFile upload) {
        String extName = "jpg";
        if (upload == null) return extName;
        String original = upload.getOriginalFilename();
        try {
            if (original != null && ".".indexOf(original) != -1) {
                extName = original.split(".")[1];
            }
        } catch (Exception e) {}
        return extName;
    }
}
