package com.heleeos.blog.ajax;

import com.heleeos.blog.dto.Manager;
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
 * 主页使用到的接口
 * Created with Li Yu on 2017/12/17.
 *
 * @author liyu
 * @author kissaoe@gmail.com
 * @version 1.0.1
 */
@RestController
@RequestMapping("ajax/main/")
public class MainDataController {

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
        Manager manager = SessionUtil.getManagerFromSession(request);
        if(manager != null) {
            return Result.SUCCESS("session - " + System.currentTimeMillis());
        }

        String token = SessionUtil.getTokenFromCookie(request);
        manager = managerService.getManagerByToken(token);
        if(manager != null) {
            return Result.SUCCESS("cookie - " + System.currentTimeMillis());
        }
        return Result.FAILED("未登录!");
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
        try {
            String captcha = SessionUtil.getCaptchaFromSession(request);
            if(captcha != null && captcha.equals(request.getParameter("captcha"))) {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                Manager manager = managerService.login(username, DigestUtils.md5DigestAsHex(password.getBytes()));
                if(manager != null) {
                    String token = TokenUtil.createToken(manager);
                    managerService.updateLoginTime(manager.getId());
                    managerService.updateToken(manager.getId(), token);

                    manager.setLoginToken(token);
                    SessionUtil.saveManagerToSession(request, manager);
                    return Result.SUCCESS();
                } else {
                    return Result.FAILED("用户名或密码错误!");
                }
            } else {
                return Result.FAILED("验证码错误!");
            }
        } catch (Exception e) {
            logger.error("登录失败，失败原因：" + e.getMessage(), e);
            return Result.FAILED("用户名或密码错误!");
        }
    }

    /**
     * 退出管理端.
     */
    @RequestMapping(value = "logout.json")
    public Result logout(HttpServletRequest request, HttpServletResponse response){
        SessionUtil.removeSessionManager(request);
        SessionUtil.removeCookieToken(response);

        return Result.SUCCESS();
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
        /* 获取当前登陆的管理员 */
        Manager manager = SessionUtil.getManagerFromSession(request);
        if(manager == null){
            return Result.AUTHOR_ERROR();
        }

        String userName = request.getParameter("userName");
        String realName = request.getParameter("realName");
        String passWord = request.getParameter("passWord");
        String picture = request.getParameter("picture");

        if(StringUtils.trimToNull(userName) == null) {
            return Result.PARAMETER_ERROR("用户名不能为空!");
        }

        if(StringUtils.trimToNull(realName) == null) {
            return Result.PARAMETER_ERROR("真实姓名不能为空!");
        }

        if(StringUtils.trimToNull(passWord) != null) {
            manager.setPassWord(DigestUtils.md5DigestAsHex(passWord.getBytes()));
        }

        manager.setUserName(userName);
        manager.setRealName(realName);
        manager.setManagerPicture(picture);

        boolean bol = managerService.save(manager);
        return Result.of(bol);
    }

    @RequestMapping(value = "version.json")
    public Result version() {
        return Result.SUCCESS(systemService.getBeanVersion());
    }

    @RequestMapping(value = "systemInfo.json")
    public Result systemInfo() {
        return Result.SUCCESS(systemService.getSystemInfo());
    }
}
