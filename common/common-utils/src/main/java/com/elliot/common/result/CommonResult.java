package com.elliot.common.result;

import com.elliot.common.constant.IErrorCode;
import com.elliot.common.constant.ResultCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 响应码和响应信息
 *
 * @author happy
 */
@Data
@AllArgsConstructor
public class CommonResult<T> {

  @ApiModelProperty("响应码")
  private long code;

  @ApiModelProperty("响应信息")
  private String message;

  @ApiModelProperty("返回的数据")
  private T data;

  private CommonResult() {
  }

  /**
   * 成功返回结果
   *
   * @param data 获取的数据
   */
  public static <T> CommonResult<T> success(T data) {
    return new CommonResult<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
  }

  /**
   * 成功返回的结果
   *
   * @param data    获取的数据
   * @param message 提示信息
   */
  public static <T> CommonResult<T> success(T data, String message) {
    return new CommonResult<T>(ResultCode.SUCCESS.getCode(), message, data);
  }

  /**
   * 失败返回结果
   *
   * @param errorCode 错误码
   * @return
   */
  public static <T> CommonResult<T> failed(IErrorCode errorCode) {
    return new CommonResult<>(errorCode.getCode(), errorCode.getMessage(), null);
  }

  /**
   * 失败返回结果
   *
   * @param errorCode 错误码
   * @param message   错误信息
   */
  public static <T> CommonResult<T> failed(IErrorCode errorCode, String message) {
    return new CommonResult<>(errorCode.getCode(), errorCode.getMessage(), null);
  }

  /**
   * 失败返回结果
   *
   * @param message 提示信息
   */
  public static <T> CommonResult<T> failed(String message) {
    return new CommonResult<>(ResultCode.FAILED.getCode(), message, null);
  }

  /**
   * 失败返回结果
   */
  public static <T> CommonResult<T> failed() {
    return failed(ResultCode.FAILED);
  }
  
}
