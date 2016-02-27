package com.pmikee.nir.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.pmikee.nir.domain.OrderHead;
import com.pmikee.nir.domain.OrderPosition;
import com.pmikee.nir.domain.Product;
import com.pmikee.nir.service.CustomerRepository;
import com.pmikee.nir.service.OrderHeadRepository;
import com.pmikee.nir.service.OrderPositionRepository;
import com.pmikee.nir.service.ProductRepository;
import com.pmikee.nir.utils.WebShopException;
import localhost._8080.ws.GetOrderRequest;
import localhost._8080.ws.GetOrderResponse;
import localhost._8080.ws.Positions;

@Endpoint
public class OrderEndpoint {
	private static final String NAMESPACE_URI = "http://localhost:8080/ws";

	@Autowired
	private OrderHeadRepository orderRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private OrderPositionRepository orderPositionRepository;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getOrderRequest")
	@ResponsePayload
	public GetOrderResponse getOrder(@RequestPayload GetOrderRequest request) throws Exception {
		GetOrderResponse response = new GetOrderResponse();
		List<OrderPosition> positions = new ArrayList<>();

//		for (Product p : productRepository.findAll()) {
//			LOGGER.info(p.toString());
//		}
		for (Positions p : request.getOrder().getPosition()) {
			checkProductAvailability(p);
			positions.add(createOrderPosition(p));
		}
		OrderHead order = new OrderHead(customerRepository.findCustomerById(request.getOrder().getCustomer()),
				new Date());
		orderRepository.save(order);
		for (OrderPosition op : positions) {
			op.setOrderHead(order);
			orderPositionRepository.save(op);
		}
		response.setId(order.getId());

		return response;
	}

	private OrderPosition createOrderPosition(Positions p) {
		OrderPosition position = new OrderPosition();
		Product product = productRepository.findProductById(p.getProduct());
		position.setProduct(product);
		position.setQuantity(p.getQuantity());
		position.setTotalPrice(product.getUnitPrice().multiply(p.getQuantity()));
		executeStockModifications(product, p.getQuantity());
		return position;

	}

	private void executeStockModifications(Product p, BigDecimal quantity) {
		p.setStock(p.getStock().subtract(quantity));
		productRepository.save(p);

	}

	public static XMLGregorianCalendar toXMLGregorianCalendar(Date date) {
		GregorianCalendar gCalendar = new GregorianCalendar();
		gCalendar.setTime(date);
		XMLGregorianCalendar xmlCalendar = null;
		try {
			xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar);
		} catch (DatatypeConfigurationException ex) {
		}
		return xmlCalendar;
	}

	private void checkProductAvailability(Positions pos) throws WebShopException {
		Product p = productRepository.findProductById(pos.getProduct());
		if (p.getStock().subtract(pos.getQuantity()).compareTo(BigDecimal.ZERO) < 0) {
			throw new WebShopException("Nincs elég termék raktáron!");
		}
	}

}
