package com.example.spring.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.spring.model.Juego;
import com.example.spring.service.CsvReader;
import com.example.spring.service.JuegoService;

@Controller
public class JuegoController {
	
	@Autowired
	JuegoService service;
	@Autowired 
	CsvReader csvReader;
	
	//-- LISTAR LOS JUEGOS DESDE LA URL "/"--
		@GetMapping("/")
		public String listJuego(Model m) {
			m.addAttribute("lista", service.findAll());
			return "Lista";
		}
		@GetMapping("/editores")
		public String listJuegoByPublisher(Model m) {
			m.addAttribute("lista", service.findOrderByPublisher());
			return "Lista";
		}
		@GetMapping("/genero")
		public String listJuegoByGenre(Model m) {
			m.addAttribute("lista", service.findAllByOrderByGenre());
			return "Lista";
		}
		@GetMapping("/siglo")
		public String listJuegoBySiglo(Model m) {
			m.addAttribute("lista", service.findBySiglo(2000));
			return "Lista";
		}
		@GetMapping("/par")
		public String listJuegoByPar(Model m) {
			m.addAttribute("lista", service.findByPar());
			return "Lista";
		}
		@GetMapping("/ventaEU")
		public String listJuegoByVentaEu(Model m) {
			m.addAttribute("lista", service.findAllByOrderByEusales());
			return "Lista";
		}
		@GetMapping("/nintendo")
		public String listJuegoByNintendo(Model m) {
			m.addAttribute("lista", service.findAllByOrderByNintendo());
			return "Lista";
		}
		
		@GetMapping("/loadData")
		public String loadData(Model m) {
			try {
				csvReader.loadData();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "Lista";
		}
		
	//-- EDITAR EL JUEGO POR SU ID A TRAVES DE LA URL "/edit" --
		@GetMapping("/edit")
		public String editJuego(@RequestParam("id") int id, Model m) {
			m.addAttribute("juego", service.findById(id));
			return "JuegoForm";
		}
		
	//-- ELIMINAR EL JUEGO POR SU ID A TRAVES DE LA URL "/delete" REDIRIGIENDO A "/" --
		@GetMapping("/delete")
		public String deleteJuego(@RequestParam("id") int id) {
			service.deleteJuego(id);
			return ("redirect:/");
		}
		
	//-- GUARDAR JUEGO A TRAVES DE LA URL "/save" REDIRIGIENDO A "/" --
		@PostMapping("/save")
		public String saveJuego(Juego juego) {
			service.save(juego);
			return ("redirect:/");
		}

	//-- Nuevo JUEGO--

	@GetMapping("/new")

	public String newJuego(Juego juego, Model m) {

		m.addAttribute("juego",juego);

		return "JuegoForm";

	}



}
