package com.demo.qyadmindemo.controller;

import com.demo.qyadmindemo.entity.*;
import com.demo.qyadmindemo.response.ResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAccessor;
import java.util.*;

/**
 * 企业版后端接口地址: http://lantiandun.com:85
 */

@Slf4j
@RestController
public class BizController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String API_URL = "http://lantiandun.com:85";

    /**
     * 获取 UUID
     * @return
     */
    @GetMapping("/uuid")
    public ResponseModel getUUID() {
        return ResponseModel.ok(UUID.randomUUID());
    }

    // TODO 获取验证码 LoginController -> GET /kaptchaImage
    @GetMapping("/verify_img")
    public ResponseModel getVerifyImage(@RequestParam String uuid) {
        byte[] result = restTemplate.getForObject(String.format(API_URL + "/kaptchaImage?uuid=%s", uuid), byte[].class);
        return ResponseModel.ok("获取成功", result);
    }

    // TODO 登录 LoginController -> POST /login
    // username password vrifyCode uuid
    @PostMapping("/system_login")
    public ResponseModel systemLogin(@RequestBody @Valid LoginEntity loginEntity) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> valueMap = new LinkedMultiValueMap<>();
        valueMap.add("username", loginEntity.getUsername());
        valueMap.add("password", loginEntity.getPassword());
        valueMap.add("vrifyCode", loginEntity.getVerifyCode());
        valueMap.add("uuid", loginEntity.getUuid());
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(valueMap, headers);
        LoginResponseEntity result = restTemplate.postForObject(API_URL + "/login", request, LoginResponseEntity.class);

        log.info("用户信息: " + result);
        log.info("Token: " + result.getData().getToken());
        return ResponseModel.ok("登录成功", result.getData());
    }

    // TODO 登出 LoginController -> GET /logout/{id}
    @DeleteMapping("/system_logout")
    public ResponseModel systemLogout(@RequestParam String userId) {
        restTemplate.getForObject(String.format(API_URL + "/logout/%s", userId), void.class);
        return ResponseModel.ok("登出成功");
    }

    /*=================================== 以下为需要 Token 访问的接口 ========================================*/

    // TODO 获取所有企业版用户 UserController -> GET /user
    // 查询参数 pageNum pageSize username mobile
    @GetMapping("/users")
    public ResponseModel getUsers(@RequestParam String token) {
        HttpHeaders headers = getAuthenticationHeader(token);
        String pageNum = "1";
        String pageSize = "5";
        HttpEntity<HttpHeaders> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<Object> result = restTemplate.exchange(String.format(API_URL + "/user?pageNum=%s&pageSize=%s&username=15677399832", pageNum, pageSize), HttpMethod.GET, httpEntity, Object.class);
        return ResponseModel.ok(result.getBody());
    }

    // TODO 修改用户信息(主要是开启试用) UserController -> PUT /user/profile
    @PutMapping("/user_expire")
    public ResponseModel updateUserExpireTime(@RequestBody UserUpdateVo userUpdateVo) throws ParseException {
        HttpHeaders updateUserExpireTimeHeader = new HttpHeaders();
        updateUserExpireTimeHeader.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        updateUserExpireTimeHeader.set("Authentication", userUpdateVo.getToken());

        log.info("请求头: " + updateUserExpireTimeHeader.values());

        Calendar instance = Calendar.getInstance(TimeZone.getTimeZone("Asia/Shanghai"), Locale.CHINA);
        instance.setTime(userUpdateVo.getExpireTime());

        log.info("过期时间: " + instance.getTime().toInstant().atZone(ZoneId.of("UTC+8")));
        log.info("上一次登录时间: " + userUpdateVo.getLastLoginTime());
        log.info("创建时间: " + userUpdateVo.getCreateTime());

        MultiValueMap<String, Object> valueMap = new LinkedMultiValueMap<>();
        valueMap.add("userId", userUpdateVo.getUserId());
        valueMap.add("username", userUpdateVo.getUsername());
        valueMap.add("nname", userUpdateVo.getNickName());
        valueMap.add("password", userUpdateVo.getPassword());
        valueMap.add("companyId", userUpdateVo.getCompanyId());
        valueMap.add("email", userUpdateVo.getEmail());
        valueMap.add("mobile", userUpdateVo.getMobile());
        valueMap.add("status", userUpdateVo.getStatus());
        valueMap.add("createTime", userUpdateVo.getCreateTime());
        valueMap.add("lastLoginTime", userUpdateVo.getLastLoginTime());
        valueMap.add("expireTime", userUpdateVo.getExpireTime());
        valueMap.add("ssex", userUpdateVo.getSex());
        valueMap.add("description", userUpdateVo.getDescription());
        valueMap.add("pay", userUpdateVo.getPay());
        valueMap.add("roleId", userUpdateVo.getRoleId());
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(valueMap, updateUserExpireTimeHeader);

        log.info("请求: " + request);

        restTemplate.put(API_URL + "/user", request);

        return ResponseModel.ok("修改成功");
    }

    // TODO 获取指定 ID 优惠券 TicketController -> GET /ticket
    // 查询参数 id
    @GetMapping("/ticket")
    public ResponseModel getSpecTicket(@RequestParam String ticketId, @RequestParam String token) {
        HttpHeaders headers = getAuthenticationHeader(token);
        HttpEntity<HttpHeaders> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<TicketResponseEntity> result = restTemplate.exchange(String.format(API_URL + "/ticket?id=%s", ticketId), HttpMethod.GET, httpEntity, TicketResponseEntity.class);
        return ResponseModel.ok(result.getBody());
    }

    // TODO 新增优惠券 TicketController -> POST /ticket
    @PostMapping("/ticket")
    public ResponseModel createTicket(@RequestBody TicketCreateVo ticketCreateVo) {
        HttpHeaders createTicketHeader = new HttpHeaders();
        createTicketHeader.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        createTicketHeader.set("Authentication", ticketCreateVo.getToken());
        MultiValueMap<String, Object> valueMap = new LinkedMultiValueMap<>();
        valueMap.add("amount", ticketCreateVo.getAmount().toPlainString());
        valueMap.add("createUser", ticketCreateVo.getCreateUser());
        valueMap.add("createMobile", ticketCreateVo.getCreateMobile());
        valueMap.add("endTime", ticketCreateVo.getEndTime());
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(valueMap, createTicketHeader);
        ResponseEntity<Object> result = restTemplate.exchange(API_URL + "/ticket", HttpMethod.POST, request, Object.class);

        log.info("优惠券: " + result.getBody());
        return ResponseModel.ok("创建成功", result.getBody());
    }

    // TODO 删除优惠券 TicketController -> DELETE /ticket
    @DeleteMapping("/ticket")
    public ResponseModel deleteTicket(@RequestParam String token, @RequestParam String ticketId) {
        HttpHeaders deleteTicketHeader = getAuthenticationHeader(token);
        HttpEntity<List<String>> request = new HttpEntity<>(Arrays.asList(ticketId), deleteTicketHeader);
        ResponseEntity<Integer> result = restTemplate.exchange(API_URL + "/ticket", HttpMethod.DELETE, request, Integer.class);
        if (result.getBody().compareTo(1) == 0) {
            return ResponseModel.ok("删除成功");
        } else {
            return ResponseModel.error("删除失败", result.getStatusCode().getReasonPhrase());
        }
    }

    // TODO 获取 Redis 信息 RedisController -> GET /redis/info
    @GetMapping("/redis_info")
    public ResponseModel getRedisInfo(@RequestParam String token) {
        HttpHeaders headers = getAuthenticationHeader(token);
        HttpEntity<HttpHeaders> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<Object> result = restTemplate.exchange(API_URL + "/redis/info", HttpMethod.GET, httpEntity, Object.class);
        return ResponseModel.ok(result.getBody());
    }

    // TODO 获取 Redis 内存信息 RedisController -> GET /redis/memoryInfo
    @GetMapping("/redis_mem_info")
    public ResponseModel getRedisMemoryInfo(@RequestParam String token) {
        HttpHeaders headers = getAuthenticationHeader(token);
        HttpEntity<HttpHeaders> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<Object> result = restTemplate.exchange(API_URL + "/redis/memoryInfo", HttpMethod.GET, httpEntity, Object.class);
        return ResponseModel.ok(result.getBody());
    }

    private HttpHeaders getAuthenticationHeader(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authentication", token);
        return headers;
    }
}
