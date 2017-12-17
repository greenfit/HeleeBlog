package com.heleeos.blog.ajax;

import com.heleeos.blog.bean.Manager;
import com.heleeos.blog.bean.Result;
import com.heleeos.blog.service.ManagerService;
import com.heleeos.blog.service.SystemService;
import com.heleeos.blog.util.SessionUtil;
import com.heleeos.blog.util.TokenUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 主页使用到的所有接口内容
 * Created by liyu on 17/12/2017.
 */
@RestController
@RequestMapping("ajax/main/")
public class MainInfoController {

    @Autowired
    private ManagerService managerService;

    @Autowired
    private SystemService systemService;

    private Logger logger = Logger.getLogger(getClass());

    /**
     * 检测是否登陆理端.
     */
    @RequestMapping(value = "isLogin.json")
    public Result isLogin(HttpServletRequest request){
        Result result = new Result();
        Manager manager = SessionUtil.getManagerFromSession(request);
        if(manager != null) {
            result.setCode(200);
            result.putInfo("session:" + System.currentTimeMillis());
            return result;
        }

        String token = SessionUtil.getTokenFromCookie(request);
        manager = managerService.getManagerByToken(token);
        if(manager != null) {
            result.setCode(200);
            result.putInfo("cookie:" + System.currentTimeMillis());
            return result;
        }

        result.setCode(400);
        result.putInfo("未登录");
        return result;
    }

    /**
     * 登陆管理端.
     *
     * 参数:
     * username 用户名
     * password 密码
     */
    @RequestMapping(value = "login.json")
    public Result login(HttpServletRequest request, HttpServletResponse response){
        Result result = new Result();
        try {
            String captcha = SessionUtil.getCaptchaFromSession(request);
            if(captcha != null && captcha.equals(request.getParameter("captcha"))) {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                Manager manager = managerService.login(username, DigestUtils.md5DigestAsHex(password.getBytes()));
                if(manager != null) {
                    String token = TokenUtil.grentorToken(manager);
                    managerService.updateLoginTime(manager.getId());
                    managerService.updateToken(manager.getId(), token);

                    manager.setLoginToken(token);
                    SessionUtil.saveManagerToSession(request, manager);
                    result.setCode(200);
                    result.putInfo("");
                } else{
                    result.setCode(400);
                    result.putInfo("用户名或密码错误!");
                }
            } else {
                result.setCode(400);
                result.putInfo("验证码错误!");
            }
        } catch (Exception e) {
            logger.error("登录失败，失败原因：" + e.getMessage(), e);
            result.setCode(403);
            result.putInfo("请勿非法调用!");
        }
        return result;
    }

    /**
     * 退出管理端.
     */
    @RequestMapping(value = "logout.json")
    public Result logout(HttpServletRequest request, HttpServletResponse response){
        SessionUtil.removeSessionManager(request);
        SessionUtil.saveTokenToCookie(response, "");

        Result result = new Result();
        result.setCode(200);
        result.putInfo("");
        return result;
    }

    /**
     * 修改管理员信息.
     *
     * 参数:
     * username 用户名
     * realname 姓名
     * password 密码
     * image    头像
     */
    @RequestMapping(value = "update_manager.json")
    public Result update(HttpServletRequest request){
        Result result = new Result();

        /* 获取当前登陆的管理员 */
        Manager manager = SessionUtil.getManagerFromSession(request);
        if(manager == null){
            result.setCode(-1);
            result.putInfo("登陆失效,请刷新页面!");
            return result;
        }

        String username = request.getParameter("username");
        String realname = request.getParameter("realname");
        String password = request.getParameter("password");
        String picture = request.getParameter("picture");

        if(StringUtils.trimToNull(username) == null){
            result.setCode(-1);
            result.putInfo("用户名不能为空!");
            return result;
        }

        if(StringUtils.trimToNull(realname) == null){
            result.setCode(-1);
            result.putInfo("真实姓名不能为空!");
            return result;
        }

        if(StringUtils.trimToNull(password) != null){
            manager.setPassWord(DigestUtils.md5DigestAsHex(password.getBytes()));
        }

        manager.setUserName(username);
        manager.setRealName(realname);
        manager.setManagerPicture(picture);

        boolean bol = managerService.save(manager);
        if(bol){
            result.setCode(200);
            result.putInfo("");
        }else{
            result.setCode(400);
            result.putInfo("提交失败");
        }
        return result;
    }

    @RequestMapping(value = "version.json")
    public Result version() {
        Result result = new Result();
        result.setCode(200);
        result.putMessage("version", systemService.getBeanVersion());
        return result;
    }

    @RequestMapping(value = "systemInfo.json")
    public Result systemInfo() {
        Result result = new Result();
        result.setCode(200);
        result.putBean(systemService.getSystemInfo());
        return result;
    }
}
