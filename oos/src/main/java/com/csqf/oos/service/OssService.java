package com.csqf.oos.service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.csqf.common.config.OssConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OssService {

    @Autowired
    private OssConfig ossConfig;

    public void delFile(String url) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = ossConfig.getEndpoint();
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = ossConfig.getKeyid();
        String accessKeySecret = ossConfig.getKeysecret();
        String bucketName = ossConfig.getBucketname();

        String strReplace = "https://"+bucketName+"."+endpoint+"/";
//        https://csqf001.oss-cn-shenzhen.aliyuncs.com/avatar/default.jpg
        String objectName = url.replace(strReplace,"");

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 删除文件。如需删除文件夹，请将ObjectName设置为对应的文件夹名称。如果文件夹非空，则需要将文件夹下的所有object删除后才能删除该文件夹。
        ossClient.deleteObject(bucketName, objectName);

        // 关闭OSSClient。
        ossClient.shutdown();
    }
}
