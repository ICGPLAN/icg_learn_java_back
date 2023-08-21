package jp.co.icg.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import jp.co.icg.dao.entity.ContactEntity;

@Mapper
@Repository
public interface ContactMapper extends BaseMapper<ContactEntity> {


}
