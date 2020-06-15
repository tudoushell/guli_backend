package com.elliot.oss.service;

import com.elliot.common.result.CommonResult;
import org.springframework.web.multipart.MultipartFile;

public interface OssService {
  /**
   * 文件上传
   *
   * @param multipartFile
   */
  CommonResult uploadFile(MultipartFile multipartFile);
}
