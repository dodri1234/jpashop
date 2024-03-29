package jpaproject.jpashop.controller;

import jpaproject.jpashop.domain.Member;
import jpaproject.jpashop.domain.OrderStatus;
import jpaproject.jpashop.domain.item.Item;
import jpaproject.jpashop.service.ItemService;
import jpaproject.jpashop.service.MemberService;
import jpaproject.jpashop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/order")
    public String createForm(Model model){

        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();

        model.addAttribute("members",members);
        model.addAttribute("items",items);

        return "order/orderForm";
    }
}
