package com.heleeos.blog.ajax;

import com.heleeos.blog.bean.Result;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

/**
 * 图片的数据接口
 * Created with Li Yu on 2017/12/17.
 *
 * @author liyu
 * @author kissaoe@gmail.com
 * @version 1.0.1
 */
@RestController
@RequestMapping("ajax/image")
public class ImageDataController {
    
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("#{configProperties.image_host}")
    private String imageHost;

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
        StringBuilder builder = new StringBuilder("<script type=\"text/javascript\">");
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
                builder.append("window.parent.CKEDITOR.tools.callFunction(").append(CKEditorFuncNum).append(",'").append(filepath).append("');");
            } else {
                builder.append("window.parent.CKEDITOR.tools.callFunction(").append(CKEditorFuncNum).append(",'', '文件复制失败!');");
            }
        } catch (Exception e) {
            logger.error("上传文件失败,原因:{}", e.getMessage());
            builder.append("window.parent.CKEDITOR.tools.callFunction(").append(CKEditorFuncNum).append(",'', '上传失败!');");
        }
        builder.append("</script>");
        try {
            response.getWriter().write(builder.toString());
            response.getWriter().flush();
        } catch (IOException e) {
            logger.error(String.format("图片上传失败, 原因:%s", e.getMessage()), e);
        }
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
            logger.error(String.format("图片上传失败, 原因:%s", e.getMessage()), e);
            result.setCode(0);
            result.putInfo("上传失败!");
        }
        return result;
    }

    /**
     * 保存文件.
     * 
     * @param dir 保存的目录
     * @param name 文件名
     * @param upload 上传的文件
     */
    private boolean saveFile(String dir, String name, MultipartFile upload) {
        File target = new File(dir, name);
        boolean flag = target.getParentFile().mkdirs();
        if(flag) {
            try {
                Files.copy(upload.getInputStream(), target.toPath());
                return true;
            } catch (Exception e) {
                logger.error(String.format("文件复制失败, 原因:%s", e.getMessage()), e);
            }
        } else {
            logger.error(String.format("文件复制失败, 原因:目录[%s]创建失败", dir));
        }
        return false;
    }

    /**
     * 获取文件扩展名.
     */
    private String getExtName(MultipartFile upload) {
        String extName = "jpg";
        if (upload == null) return extName;
        String original = upload.getOriginalFilename();
        try {
            if (original != null && original.contains(".")) {
                extName = original.split(".")[1];
            }
        } catch (Exception e) {}
        return extName;
    }
}
