package com.hrms.dao;

import com.hrms.bean.Review;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @auther thk
 * @date 2020/11/22 - 18:46
 */
public interface ReviewDao {

    String TABLE_NAME_REVIEW = "review";

    String INSERT_FIELDS_REVIEW = "userId,review";

    @Insert({"INSERT INTO",TABLE_NAME_REVIEW,"(",INSERT_FIELDS_REVIEW,")"+
            "VALUES(#{userId},#{review})"})
    public int review(@Param("userId") Integer userId, @Param("review") String review);

    @Select({"select * from ",TABLE_NAME_REVIEW})
    @Results({
        @Result(id = true,property = "id",column = "id"),
        @Result(property = "review",column = "review"),
        @Result(property = "username",column = "userId",javaType = java.lang.String.class,one = @One(select = "com.hrms.dao.UserDao.findNameById")),
    })
    public List<Review> getReview();
}
