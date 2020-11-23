package com.hrms.dao;

import com.hrms.bean.Sentence;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @auther thk
 * @date 2020/11/22 - 10:17
 */
public interface SentenceDao {
    String TABLE_NAME = "sentence";

    String INSERT_FIELDS = "sentence,author,userId";

    @Select({"select * from ",TABLE_NAME,"where id = #{id}"})
    public Sentence findNextSentence(Integer id);

    @Insert({"INSERT INTO",TABLE_NAME,"(",INSERT_FIELDS,")"+
            "VALUES(#{sentence},#{author},#{userId})"})
    public int upload(@Param("userId") Integer userId,
                      @Param("sentence") String sentence,
                      @Param("author") String author);


    @Select({"select count(*) from ",TABLE_NAME})
    public int selectCount();
}
