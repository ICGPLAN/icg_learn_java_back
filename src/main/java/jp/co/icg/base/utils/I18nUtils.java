package jp.co.icg.base.utils;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jp.co.icg.base.common.MyLocaleResolver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class I18nUtils {

    @Value("${spring.messages.basename}")
    private String basename;

    private final MyLocaleResolver resolver;

    private static MyLocaleResolver customLocaleResolver;

    private static String path;

    public I18nUtils(MyLocaleResolver resolver) {
        this.resolver = resolver;
    }

    @PostConstruct
    public void init() {
        setBasename(basename);
        setCustomLocaleResolver(resolver);
    }
    
    public static String getMessage(String code) {
        Locale locale = customLocaleResolver.getLocale();
        return getMessage(code, null, code, locale);
    }
    
    public static String getMessage(String code, String lang) {
        Locale locale;
        if(StringUtils.isEmpty(lang)) {
            locale = Locale.JAPAN;
        } else {
            if (lang.contains("-")) lang = lang.split("-")[0];
            try {
                locale = new Locale(lang);
            } catch(Exception e) {
                locale = Locale.JAPAN;
                e.printStackTrace();
            }
        }
        return getMessage(code, null, code, locale);
    }
    
    public static String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.toString());
        messageSource.setBasename(path);
        String content;
        try {
            content = messageSource.getMessage(code, args, locale);
        } catch(Exception e) {
            log.error("i18n parameter error: {}, {}", e.getMessage(), e);
            content = defaultMessage;
        }
        return content;
    }
    
    public static void setBasename(String basename) {
        I18nUtils.path = basename;
    }
    
    public static void setCustomLocaleResolver(MyLocaleResolver resolver) {
        I18nUtils.customLocaleResolver = resolver;
    }

    public static String getLang(HttpServletRequest request) {
        String language = request.getHeader("lang");
        log.info("language setting: {}", language);
        if(!StringUtils.isEmpty(language)) {
            return language;
        }
        return Locale.getDefault().getLanguage();
    }
}
