package com.wangc.p2p.sms.service;

import java.util.Map;

/**
 * @author After拂晓
 * @date 2022年05月20日 14:33
 */
public interface SmsService {
    void send(String mobile, String templateCode, Map<String, Object> param);
}
