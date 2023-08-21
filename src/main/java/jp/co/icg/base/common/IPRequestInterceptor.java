package jp.co.icg.base.common;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.HandlerInterceptor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.co.icg.base.response.ComResponse;
import jp.co.icg.base.utils.I18nUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * 缓存ip地址防止连续发Contact
 *
 */
@Slf4j
public class IPRequestInterceptor implements HandlerInterceptor {
//    private IPRequestLimiter limiter = IPRequestLimiter.getInstance();
//    private String idAddr = "";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();
        log.info("ip check preHandle start. requestURL: {} , method: {}",request.getRequestURI(), method);
        if(method.equals("OPTIONS")) {
            return true;
        }
        try {
            // 调用IPRequestLimiter.isRequestAllowed方法，根据返回值决定是否继续执行请求
            // get client ip address
            String clientIp = IPRequestLimiter.getClientIp(request);
            
            boolean isAllowed = IPRequestLimiter.isRequestAllowed(clientIp);
            log.info("remote address: {}, isAllowed: {}", clientIp, isAllowed);
            if (!isAllowed) {
                ResponseEntity<ComResponse> errorResponse = ResponseEntity.status(HttpStatus.OK)
                        .body(ComResponse.builder().result(ComResponse.FAILURE)
                        .errors(new ArrayList<String>() {{ 
                            add(I18nUtils.getMessage(MsgConfig.ERR0009, I18nUtils.getLang(request))); }})
                        .build());
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonResponse = objectMapper.writeValueAsString(errorResponse.getBody());
                response.setContentType("application/json");
                response.setCharacterEncoding(StandardCharsets.UTF_8.name());
//                response.setHeader("Access-Control-Allow-Origin", "*");
                response.getWriter().write(jsonResponse);
                // 返回false表示不继续执行请求
                response.setStatus(HttpServletResponse.SC_OK);
                return false;
            } 
//            else {
//                idAddr = clientIp;
//            }
        } catch(JsonProcessingException e) {
            e.printStackTrace();
        }

        // 返回true表示继续执行请求
        return true;
    }

//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        // 在控制器执行之后，渲染视图之前执行的操作
//    }

//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//
//    }
}
