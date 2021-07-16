package com.example.demo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.PageRequestDTO;
import com.example.demo.dto.PageResultDTO;
import com.example.demo.service.GuestbookService;

@Controller
@RequestMapping("/guestbook")
@Log4j2
@RequiredArgsConstructor
public class GuestbookController {

	private final GuestbookService service;
	
	/*
	 * @GetMapping({"/", "/list"}) public String list() {
	 * 
	 * log.info("list.................");
	 * 
	 * return "/guestbook/list"; }
	 */
	
	@GetMapping("/")
	public String index() {
		
		return "redirect:/guestbook/list";
	}
	
	@GetMapping("/list")
	public void list(PageRequestDTO pageRequestDTO, Model model) {
		
		log.info("list...................." + pageRequestDTO);
		
		model.addAttribute("result", service.getList(pageRequestDTO));
	}


}
