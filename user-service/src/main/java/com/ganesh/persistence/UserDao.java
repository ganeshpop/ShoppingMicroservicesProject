package com.ganesh.persistence;

import com.ganesh.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE User SET name =:name WHERE id =:id")
    int updateUserName(@Param("id") int id, @Param("name") String name);

    @Transactional
    @Modifying
    @Query("UPDATE User SET password =:password WHERE id =:id")
    int updateUserPassword(@Param("id") int id, @Param("password") String password);

    User getUserByName(String userName);
}