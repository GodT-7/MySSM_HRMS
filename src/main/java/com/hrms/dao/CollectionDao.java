package com.hrms.dao;

import com.hrms.bean.Collection;
import com.hrms.bean.Sentence;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @auther thk
 * @date 2020/11/22 - 22:02
 */
public interface CollectionDao {
    String TABLE_NAME_COLLECTION = "collection";

    String INSERT_FIELDS_COLLECTION = "userId,sentenceId";

    String TABLE_NAME_SENTENCE = "sentence";


    @Insert({"INSERT INTO",TABLE_NAME_COLLECTION,"(",INSERT_FIELDS_COLLECTION,")"+
            "VALUES(#{userId},#{sentenceId})"})
    public int collect(@Param("sentenceId") Integer sentenceId, @Param("userId") Integer userId);

    @Select({"select * from ",TABLE_NAME_SENTENCE,"where id in (select sentenceId from ",
            TABLE_NAME_COLLECTION," where userId = #{userId})"})
    public List<Sentence> getCollection(Integer userId);

    @Select({"select * from ",TABLE_NAME_COLLECTION,"where sentenceId = #{sentenceId} and userId = #{userId}"})
    public Collection collected(@Param("sentenceId") Integer sentenceId,@Param("userId") Integer userId);

    @Delete({"delete from  ",TABLE_NAME_COLLECTION," where sentenceId = #{sentenceId} and userId = #{userId}"})
    public int deletedCollect(@Param("sentenceId") Integer sentenceId,@Param("userId") Integer userId);
}
