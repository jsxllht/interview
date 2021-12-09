package com.csqf.service;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.csqf.common.exception.AppException;
import com.csqf.common.result.ResponseEnum;
import com.csqf.config.SmsConfig;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SmsService {
    @Autowired
    private SmsConfig smsConfig;

    /**
     *  给指定的号码 发送验证码短信
     */
    public void sendCheckCode(String phone,String code){
        DefaultProfile profile =
                DefaultProfile.getProfile(smsConfig.getRegionId(),
                        smsConfig.getAccessKeyId(), smsConfig.getAccessSecret());
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain(smsConfig.getSysDomain());
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", smsConfig.getSignName());
        request.putQueryParameter("TemplateCode", "SMS_150181589");
        Map<String,Object> map = new HashMap<>();
        map.put("code",code);
        Gson gson = new Gson();
        String json = gson.toJson(map);
        request.putQueryParameter("TemplateParam", json);
        CommonResponse response = null;
        try {
            // 短信的结果
            response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (Exception e) {
            throw new AppException(ResponseEnum.SMS_ERROR);
        }

        // 获得结果
        Map<String,String> map1 = gson.fromJson(response.getData(),Map.class);
        String responseCode = map1.get("Code");

        if ("isv.BUSINESS_LIMIT_CONTROL".equals(responseCode)) {
            throw new AppException(ResponseEnum.SMS_SEND_ERROR_BUSINESS_LIMIT_CONTROL);
        }

        if (!"OK".equals(responseCode)) {
            throw new AppException(ResponseEnum.SMS_ERROR);
        }


    }
}
