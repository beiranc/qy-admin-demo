package com.demo.qyadmindemo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service("serialNumberService")
public class SerialNumberServiceImpl implements SerialNumberService {
    @Autowired
    RedisUtils redisUtils;

    @Override
    public String generate(String bizCode) throws Exception {
        /** 检查业务码 */
        boolean isLegal = isLegal(bizCode);

        if (!isLegal) {
            throw new Exception("bizCode參数不合法");
        }

        /** 获取今天的日期:yyyyMMdd */
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
//        String date = "20210520";

        /** 构造redis的key */
        String key = SERIAL_NUMBER + date;

        /** 自增 */
        long sequence = redisUtils.incr(key);

        String seq = CustomStringUtil.getSequence(sequence);

        StringBuilder sb = new StringBuilder();

        sb.append(date).append(bizCode).append(seq);

        String serial = sb.toString();

        return serial;
    }
}
