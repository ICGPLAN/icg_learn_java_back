package jp.co.icg.base.common;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import jp.co.icg.base.response.ComResponse;
import jp.co.icg.base.utils.I18nUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ValidatedExceptionHandler {

    @Autowired
    private HttpServletRequest request;
    
    static final ThreadLocal<ResponseEntity<ComResponse>> responseHolder = new ThreadLocal<>();

//    private String lang;
//
//    public ValidatedExceptionHandler() {
//        lang = I18nUtils.getLang(request);
//    }

    /**
     * 处理@Validated参数校验失败异常
     * @param exception 异常类
     * @return 响应
     */
    @ResponseBody
//    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ComResponse> validExceptionHandler(MethodArgumentNotValidException exception){
        log.info("ValidatedException");
        BindingResult result = exception.getBindingResult();
        List<String> errList = new ArrayList<String>();
        String lang = I18nUtils.getLang(request);
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            if (errors != null) {
                errors.forEach(p -> {
                    FieldError fieldError = (FieldError) p;
                    String message = I18nUtils.getMessage(fieldError.getDefaultMessage(), lang);
                    log.warn("Bad Request Parameters: dto entity [{}],field [{}],message [{}]",
                            fieldError.getObjectName(),
                            fieldError.getField(),
                            message);
                    errList.add(message);
                });
            }
        }
        // 检证失败的时候会把缓存中的ip地址删除。
        this.ipRemove();
        
        // 检证失败的时候会把缓存中的ip地址删除。
        ResponseEntity<ComResponse> response = ComResponse.validatedException(errList);
//        responseHolder.set(response);
        
        return response;
    }
    
    @ResponseBody
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ComResponse> exceptionHandler(Exception exception){
      log.info("exceptionHandler");
      // 检证失败的时候会把缓存中的ip地址删除。
      this.ipRemove();

      return ComResponse.validatedException(
              List.of(I18nUtils.getMessage(MsgConfig.ERR0000, I18nUtils.getLang(request))));
  }
  
  private void ipRemove() {
      String remoteAddr = IPRequestLimiter.getClientIp(request);
      if(IPRequestLimiter.containsAddr(remoteAddr)) {
          log.info("ip remove: {}", remoteAddr);
          IPRequestLimiter.removeIpAddr(remoteAddr);
      }
  }
}
