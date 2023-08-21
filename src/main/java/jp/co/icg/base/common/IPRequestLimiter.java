package jp.co.icg.base.common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IPRequestLimiter {

    // 並行問題解消するため、ConcurrentHashMapを使う
    private static Map<String, Long> ipRequests = new ConcurrentHashMap<>();
    
    private static IPRequestLimiter instance;

    public static synchronized IPRequestLimiter getInstance() {
        if (instance == null) {
            instance = new IPRequestLimiter();
        }
        return instance;
    }

    public static boolean isRequestAllowed(String ipAddress) {
        long currentTime = System.currentTimeMillis();

        if (!ipRequests.containsKey(ipAddress)) {
            // IP リクエストレコードが存在しない場合、リクエストを許可し、現在のタイムスタンプを記録する。
            ipRequests.put(ipAddress, currentTime);
            return true;
        } else {
            long lastRequestTime = ipRequests.get(ipAddress);
            long timeDiff = currentTime - lastRequestTime;

            if (timeDiff >= 300000) {
                // 時間差が5分以上経過した場合、リクエストを許可し、タイムスタンプを更新する。
                ipRequests.put(ipAddress, currentTime);
                log.info("isRequestAllowed: true,ipAddress: {}, timeDiff: {}",ipAddress, timeDiff);
                return true;
            } else {
                log.info("isRequestAllowed: false,ipAddress: {}, timeDiff: {}",ipAddress, timeDiff);
                // 時間差が5分以内の場合、リクエストを拒否する
                return false;
            }
        }
    }

    public static Boolean containsAddr(String ipAddress) {
        return ipRequests.containsKey(ipAddress);
    }

    public static void removeIpAddr(String ipAddress) {
        ipRequests.remove(ipAddress);
    }

    public static String getClientIp(HttpServletRequest request) {
        String xForwardedForHeader = request.getHeader("X-Forwarded-For");
        if (xForwardedForHeader != null && !xForwardedForHeader.isEmpty()) {
            // X-Forwarded-For头部信息的格式是："client, proxy1, proxy2, ..."
            return xForwardedForHeader.split(",")[0].trim();
        }
        log.info("X-Forwarded-For is empty");
        return request.getRemoteAddr();
    }
}
