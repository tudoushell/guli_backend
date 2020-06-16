package com.elliot.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.elliot.common.result.CommonResult;
import com.elliot.oss.service.OssService;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class OssServiceImpl implements OssService {

  @Value("${aliyun.oss.file.endpoint}")
  private String endpoint;

  @Value("${aliyun.oss.file.keyId}")
  private String keyId;

  @Value("${aliyun.oss.file.keySecret}")
  private String keySecret;

  @Value("${aliyun.oss.file.bucketName}")
  private String bucketName;

  @Override
  public CommonResult uploadFile(MultipartFile multipartFile) {
    log.info("开始上传文件");
    String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 5);
    String dateDirectory = new DateTime().toString("yyyy/MM/dd");
    //文件名 目录+uuid+原始文件名
    final String FILE_NAME = dateDirectory + uuid + multipartFile.getOriginalFilename();
    String resultUrl = "https://" + bucketName + "." + endpoint + "/" + FILE_NAME;
    // 创建OSSClient实例。
    OSS ossClient = null;
    try {
      ossClient = new OSSClient(endpoint, keyId, keySecret);
      // 上传内容到指定的存储空间（bucketName）并保存为指定的文件名称（objectName）。
      ossClient.putObject(bucketName, FILE_NAME, multipartFile.getInputStream());
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return CommonResult.failed("文件上传失败");
    } finally {
      // 关闭OSSClient。
      ossClient.shutdown();
    }
    log.info("上传成功");
    Map<String, String> map = new HashMap<>();
    map.put("fileUrl", resultUrl);
    return CommonResult.success(map);
  }
}
