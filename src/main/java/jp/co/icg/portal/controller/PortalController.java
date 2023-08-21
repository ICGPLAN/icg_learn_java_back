package jp.co.icg.portal.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import jp.co.icg.base.common.MsgConfig;
import jp.co.icg.base.model.Contact;
import jp.co.icg.base.response.ComResponse;
import jp.co.icg.base.utils.I18nUtils;
import jp.co.icg.portal.service.PortalService;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/api")
@ResponseBody
@Slf4j
public class PortalController {
    private final PortalService portalService;
    
//    private final HttpServletRequest request;

    public PortalController(PortalService portalService) {
        this.portalService = portalService;
    }

    @GetMapping("/ping")
    public ResponseEntity<ComResponse> ping(Contact contact)
        throws GeneralSecurityException, IOException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ComResponse.builder().message("pong").result(ComResponse.SUCCESS).build());
    }

    @PostMapping("/contact")
    public ResponseEntity<ComResponse> contact(
        @Validated @RequestBody Contact contact,HttpServletRequest request) {
        log.info("contact: {}",contact.toString());
        portalService.contact(contact);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ComResponse.builder().result(ComResponse.SUCCESS)
                .message(I18nUtils.getMessage(MsgConfig.MSG0010, request.getHeader("lang"))).build());
    }
}
