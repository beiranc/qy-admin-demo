package com.demo.qyadmindemo.controller;

import com.demo.qyadmindemo.response.ResponseModel;
import com.demo.qyadmindemo.utils.SerialNumberService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 测试接口
 */
@ApiIgnore
@Api(hidden = true)
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    SerialNumberService serialNumberService;

    @GetMapping
    public ResponseModel test() {
        return ResponseModel.ok("Hello World");
    }

    @GetMapping("/code")
    public ResponseModel getCode() {
        try {
            String code = serialNumberService.generate("21");
            return ResponseModel.ok("获取成功", code);
        } catch (Exception e) {
            return ResponseModel.error("获取失败: " + e.getLocalizedMessage());
        }
    }
}
