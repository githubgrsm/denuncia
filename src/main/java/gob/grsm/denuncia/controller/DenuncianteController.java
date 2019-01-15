package gob.grsm.denuncia.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import gob.grsm.denuncia.model.Denunciante;
import gob.grsm.denuncia.service.DenuncianteService;



@RestController
@RequestMapping("/denunciante")
public class DenuncianteController {
	
	@Autowired
	private DenuncianteService denuncianteService;
	

	
	
	
	@RequestMapping("/pagination")
	@ResponseBody
	public  Map<String,Object>  pagination(
			@RequestParam("pagenumber") Integer pagenumber,
			@RequestParam("rows") Integer rows,
			@RequestParam("sortdireccion") String sortdireccion,
			@RequestParam("sortcolumn") String sortcolumn,
			@RequestParam("filters")  Object filters		
			){
	
		Map<String,Object> response = new HashMap<String, Object>();
	
		Page<Denunciante> page = denuncianteService.pagination(pagenumber, rows, sortdireccion, sortcolumn, filters);
		
		List<Denunciante> lst = page.getContent() ;
		if(lst.size() == 0 ) {
			 lst = new ArrayList<>();
		}
		
		response.put("data", lst);
		response.put("totalCount", page.getTotalElements());
		response.put("success", true);
		
		return response;
	
				
	}	
	
	@RequestMapping("/edit")
	@ResponseBody
	public Denunciante getEdit(@RequestParam("id") Long id) {
		return denuncianteService.findbyid(id).get();
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public Denunciante postCreate(@RequestBody Denunciante denunciante) {
		denunciante.setIdDenunciante(0L);
		Denunciante DenuncianteReturn = denuncianteService.create(denunciante);
		return DenuncianteReturn;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Denunciante putUdate(@RequestBody Denunciante denunciante) {
		
		return denuncianteService.update(denunciante);
	}
	
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public void delete(@PathVariable Long id) {	
		
		denuncianteService.deletebyid(id);
	}
	
	@RequestMapping("/getall")
	@ResponseBody
	public List<Denunciante> getall(){
		return denuncianteService.getall();
	}



}
