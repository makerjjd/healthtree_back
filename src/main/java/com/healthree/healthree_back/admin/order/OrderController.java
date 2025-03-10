package com.healthree.healthree_back.admin.order;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthree.healthree_back.admin.order.model.dto.AdminOrderDetailDto;
import com.healthree.healthree_back.admin.order.model.dto.GetOrdersResposneDto;
import com.healthree.healthree_back.admin.order.model.dto.UpdateOrderRequestDto;
import com.healthree.healthree_back.common.dto.PageRequestDto;
import com.healthree.healthree_back.common.model.ApiResponseMessage;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "어드민 주문 도메인", description = "어드민 주문 도메인")
@Slf4j
@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("")
    public ResponseEntity<?> getOrders(PageRequestDto pageRequestDto) {
        GetOrdersResposneDto getOrdersResposneDto = orderService.getOrders(pageRequestDto);
        ApiResponseMessage message = ApiResponseMessage.successWithData("", getOrdersResposneDto);
        return new ResponseEntity<ApiResponseMessage>(message, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrder(@PathVariable Long id) {
        AdminOrderDetailDto adminOrderDetailDto = orderService.getOrder(id);
        ApiResponseMessage message = ApiResponseMessage.successWithData("", adminOrderDetailDto);
        return new ResponseEntity<ApiResponseMessage>(message, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable Long id,
            @RequestBody UpdateOrderRequestDto updateOrderRequestDto) {
        orderService.updateOrder(id, updateOrderRequestDto);
        ApiResponseMessage message = ApiResponseMessage.success();
        return new ResponseEntity<ApiResponseMessage>(message, HttpStatus.OK);
    }

}
