package com.pmikee.nir.service;

import java.math.BigDecimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pmikee.nir.domain.OrderHead;
import com.pmikee.nir.domain.OrderPosition;

public interface OrderPositionRepository extends JpaRepository<OrderPosition, String>{
	@Query("SELECT SUM(o.totalPrice) FROM OrderPosition o WHERE o.orderHead = (?1)")
	public BigDecimal getSumByOrderHead(@Param("orderId") OrderHead orderId);
}
