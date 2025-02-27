package com.healthree.healthree_back.order;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.healthree.healthree_back.common.dto.PageRequestDto;
import com.healthree.healthree_back.common.model.ApiResponseMessage;
import com.healthree.healthree_back.common.utils.AuthUtil;
import com.healthree.healthree_back.order.model.dto.OrderItemDetailDto;
import com.healthree.healthree_back.order.model.dto.OrderItemDto;
import com.healthree.healthree_back.order.model.dto.OrderRequestDto;
import com.healthree.healthree_back.order.model.dto.OrdersResponseDto;
import com.healthree.healthree_back.user.model.entity.UserEntity;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "구매정보 도메인", description = "구매정보 도메인")
@Slf4j
@RestController
@RequestMapping("/app/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("")
    @Parameter(name = "pageRequestDto", hidden = true)
    @Parameter(name = "page", description = "페이지 값", required = false)
    @Parameter(name = "search", description = "검색어", required = false)
    public ResponseEntity<?> home(Authentication authentication, PageRequestDto pageRequestDto) {
        UserEntity userEntity = AuthUtil.getUserEntity(authentication);
        OrdersResponseDto ordersResponseDto = orderService.getOrderList(userEntity, pageRequestDto);
        ApiResponseMessage message = ApiResponseMessage.successWithData("", ordersResponseDto);
        return new ResponseEntity<ApiResponseMessage>(message, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detail(Authentication authentication, @PathVariable Long id) {
        UserEntity userEntity = AuthUtil.getUserEntity(authentication);
        OrderItemDetailDto orderItemDto = orderService.getOrderDetail(userEntity, id);
        ApiResponseMessage message = ApiResponseMessage.successWithData("", orderItemDto);
        return new ResponseEntity<ApiResponseMessage>(message, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancel(Authentication authentication, @PathVariable Long id) {
        UserEntity userEntity = AuthUtil.getUserEntity(authentication);
        orderService.cancelOrder(userEntity, id);
        ApiResponseMessage message = ApiResponseMessage.success();
        return new ResponseEntity<ApiResponseMessage>(message, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> order(Authentication authentication, @RequestBody OrderRequestDto orderRequestDto) {
        UserEntity userEntity = AuthUtil.getUserEntity(authentication);
        orderService.order(userEntity, orderRequestDto);
        ApiResponseMessage message = ApiResponseMessage.success();
        return new ResponseEntity<ApiResponseMessage>(message, HttpStatus.OK);
    }

}
