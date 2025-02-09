package com.lamnguyen.stationery_kimi.controller;

import com.lamnguyen.stationery_kimi.dto.ApiResponse;
import com.lamnguyen.stationery_kimi.dto.BillDisplay;
import com.lamnguyen.stationery_kimi.dto.BillStatusDTO;
import com.lamnguyen.stationery_kimi.dto.UserDTO;
import com.lamnguyen.stationery_kimi.service.IBillService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bill")
public class BillRestController {
    @Autowired
    private IBillService iBillService;

    @GetMapping("/{status}")
    public ApiResponse<List<BillDisplay>> getBill(HttpSession session, @PathVariable("status") String status) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        List<BillDisplay> billDisplayList = iBillService.getBillByStatusAndUserId(user.getId(), status);
        return ApiResponse.<List<BillDisplay>>builder()
                .code(200)
                .message("Update profile successfully")
                .data(billDisplayList)
                .build();
    }

    @GetMapping("/cancel/{billId}")
    public ApiResponse<BillStatusDTO> cancelBill(HttpSession session, @PathVariable("billId") Long billId) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        BillStatusDTO billStatusDTO = iBillService.cancelBill(user.getId(), billId);
        return ApiResponse.<BillStatusDTO>builder()
                .code(200)
                .message("Update profile successfully")
                .data(billStatusDTO)
                .build();
    }
}
