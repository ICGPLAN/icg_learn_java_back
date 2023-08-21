package jp.co.icg.portal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.icg.base.common.EnumData;
import jp.co.icg.base.model.Contact;
import jp.co.icg.base.utils.DateUtils;
import jp.co.icg.dao.ContactMapper;
import jp.co.icg.dao.entity.ContactEntity;
import jp.co.icg.portal.service.PortalService;

@Service
@Transactional
public class PortalServiceImpl implements PortalService {

    @Autowired
    private final ContactMapper contactMapper;

    public PortalServiceImpl(ContactMapper contactMapper) {
        this.contactMapper = contactMapper;
    }

    @Override
    public void contact(Contact contact) {
        contactMapper.insert(
            ContactEntity.builder()
                .contactId(DateUtils.getYMDHms())
                .name(contact.getName())
                .inquirySubject(contact.getInquirySubject())
                .email(contact.getEmail())
                .content(contact.getContent())
                .creationTime(DateUtils.getCurrentDateTime())
                .deletedFlag(EnumData.Status.ACTIVE.getStatus())
                .readFlag(EnumData.Status.READ.getStatus())
                .build());
    }

}
