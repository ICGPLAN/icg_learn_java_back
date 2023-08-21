package jp.co.icg.base.common;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.co.icg.base.utils.I18nUtils;

@Configuration
public class MyLocaleResolver implements LocaleResolver {

    @Autowired
    private HttpServletRequest request;

    public Locale getLocale() {
        return resolveLocale(request);
    }

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        return new Locale(I18nUtils.getLang(request));
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
    }

}
