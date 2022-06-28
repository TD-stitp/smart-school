package com.td.smartschool.customer.controller;


import com.td.smartschool.customer.common.utils.Education;
import com.td.smartschool.customer.common.utils.Electricity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

/**
 * @author K8lyN
 * @version v1.0
 * @date 2021年9月22日 17:01:43
 */

@RequestMapping("/api/electricity")
@RestController
public class ElectricityController {

    @GetMapping("/toLogin")
    public boolean toLogin() {
        Session session = SecurityUtils.getSubject().getSession();
        return session.getAttribute("account") != null;
    }

    @GetMapping("/getValidateCode")
    public void query(HttpServletResponse response, @RequestParam(name = "time", required = false) String time) throws IOException {
        response.setContentType("image/png");
        //设置响应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");
        //设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);

        OutputStream outputStream = response.getOutputStream();
        Electricity ele = new Electricity();
        BufferedImage bufferedImage = ImageIO.read(ele.getValidateCode(time));
        ImageIO.write(bufferedImage, "PNG", outputStream);
    }

    @GetMapping("/getRSAKey")
    public String getRSAKey() {
        Electricity ele = new Electricity();
        return ele.getRSAKey().toString();
    }

    @RequestMapping("/login")
    public String login(@RequestParam("key") String key, @RequestParam(name = "code") String code, @RequestParam("username") String username, @RequestParam("password") String password) {
        Electricity ele = new Electricity();
        JSONObject result = ele.login(key, code, username, password);
        if (result.getString("code").equals(Education.SUCCESS_CODE)) {
            System.out.println(new Date() + "  " + SecurityUtils.getSubject().getSession().getAttribute("username") + "登录了电力系统");
        }
        return result.toString();
    }

    @GetMapping("/updatePassword")
    public String updatePassword(@RequestParam("key") String key, @RequestParam(name = "account", required = false) String account, @RequestParam("password") String password, @RequestParam("oldPassword") String oldPassword) {
        Electricity ele = new Electricity();
        return ele.updatePassword(key, account, password, oldPassword).toString();
    }

    @GetMapping("/queryBettery")
    public String queryBattery(@RequestParam("roomNumber") String roomNumber) {
        Electricity ele = new Electricity();
        return ele.queryBattery(roomNumber).toString();
    }

    @GetMapping("/queryFlow")
    public String queryFlow(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate, @RequestParam(name = "account", required = false) String account) {
        Electricity ele = new Electricity();
        return ele.queryFlow(startDate, endDate, account).toString();
    }

    @GetMapping("/queryLostFlag")
    public String queryLostFlag() {
        Electricity ele = new Electricity();
        return ele.cardFlag().toString();
    }

    @GetMapping("/queryBalance")
    public String queryBalance(@RequestParam(name = "account", required = false) String account) {
        Electricity ele = new Electricity();
        return ele.cardBalance(account).toString();
    }

    @GetMapping("/lostCard")
    public String lostCard(@RequestParam(name = "account", required = false) String account, @RequestParam("password") String password, @RequestParam("flag") String flag) {
        Electricity ele = new Electricity();
        return ele.lostCard(account, password, Boolean.parseBoolean(flag)).toString();
    }

    @PostMapping("/recharge")
    public String recharge(@RequestParam(name = "account", required = false) String account, @RequestParam("tranamt") double tranamt, @RequestParam("password") String password) {
        Electricity ele = new Electricity();
        return ele.cardRecharge(account, tranamt, password).toString();
    }

    @GetMapping("/buyElectricity")
    public String buyEle(@RequestParam(name = "account", required = false) String account, @RequestParam("tran") double tran, @RequestParam("roomID") String roomID) {
        Electricity ele = new Electricity();
        return ele.buyElectricity(account, tran, roomID).toString();
    }


}
