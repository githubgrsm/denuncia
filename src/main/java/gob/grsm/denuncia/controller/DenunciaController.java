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

import gob.grsm.denuncia.model.Denuncia;
import gob.grsm.denuncia.service.DenunciaService;



@RestController
@RequestMapping("/denuncia")
public class DenunciaController {
	
	@Autowired
	private DenunciaService denunciaService;
	

	
	
	
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
	
		Page<Denuncia> page = denunciaService.pagination(pagenumber, rows, sortdireccion, sortcolumn, filters);
		
		List<Denuncia> lst = page.getContent() ;
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
	public Denuncia getEdit(@RequestParam("id") String id) {
		return denunciaService.findbyid(id).get();
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public Denuncia postCreate(@RequestBody Denuncia denuncia) {
		denuncia.setIdDenuncia("");
		Denuncia DenunciaReturn = denunciaService.create(denuncia);
		return DenunciaReturn;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Denuncia putUdate(@RequestBody Denuncia denuncia) {
		
		return denunciaService.update(denuncia);
	}
	
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public void delete(@PathVariable String id) {	
		
		denunciaService.deletebyid(id);
	}
	
	@RequestMapping("/getall")
	@ResponseBody
	public List<Denuncia> getall(){
		return denunciaService.getall();
	}



}
