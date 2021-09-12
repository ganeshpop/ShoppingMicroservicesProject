package com.order.persistence;


import com.order.bean.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderDaoInterface extends JpaRepository<UserOrder, Long> {

    List<UserOrder> getUserOrderByUserNameOrderByIdDesc(String userName);

    List<UserOrder> getUserOrdersByUserName(String userName);
}