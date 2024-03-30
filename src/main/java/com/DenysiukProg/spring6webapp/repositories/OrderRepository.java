package com.DenysiukProg.spring6webapp.repositories;

import com.DenysiukProg.spring6webapp.domain.entity.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<UserOrder,Long> {
}
