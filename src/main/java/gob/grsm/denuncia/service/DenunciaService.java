package gob.grsm.denuncia.service;



import static gob.grsm.denuncia.specification.SpecificationBuilder.selectFrom;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import gob.grsm.denuncia.interfaces.IAdicseService;

import gob.grsm.denuncia.repo.IDenunciaDao;
import gob.grsm.denuncia.model.Denuncia;
import gob.grsm.denuncia.specification.ConvertObjectToFormatJson;
import gob.grsm.denuncia.specification.Filter;
import gob.grsm.denuncia.utilitarios.Idunico;




@Service
@Transactional
public class DenunciaService implements IAdicseService<Denuncia, String> {
	
	@Autowired
	private IDenunciaDao iDenunciaDao;
	
	@Autowired
	private ConvertObjectToFormatJson convertObjectToFormatJson; 
	

	
	
	@Override
	public Page<Denuncia> pagination(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter) {
		
		Sort sort = new Sort(sortdireccion.toUpperCase() == "DESC" ? Direction.DESC : Direction.ASC, sortcolumn);
		Pageable pageable =  PageRequest.of(pagenumber, rows, sort);
		
		Filter f = convertObjectToFormatJson.ConvertObjectToFormatSpecification(filter);

		Page<Denuncia> lista = selectFrom(iDenunciaDao).where(f).findPage(pageable);
	
 

		return lista;
	}

	@Override
	public List<Denuncia> getall() {
		// TODO Auto-generated method stub
		return (List<Denuncia>) iDenunciaDao.findAll();
	}

	@Override
	public List<Denuncia> getallbyid(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Denuncia create(Denuncia denuncia) {
		// TODO Auto-generated method stub
		if(denuncia.getIdDenuncia().equals("")) {
			String id = new Idunico().getIdunico();
			denuncia.setIdDenuncia(id);
		}
		return iDenunciaDao.save(denuncia);
	}

	@Override
	public Denuncia update(Denuncia denuncia) {
		// TODO Auto-generated method stub
		Denuncia denunciaUpdate = iDenunciaDao.findById(denuncia.getIdDenuncia()).get();

		BeanUtils.copyProperties(denuncia, denunciaUpdate);
		return iDenunciaDao.save(denunciaUpdate);
		
	}

	@Override
	public void delete(Denuncia denuncia) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletebyid(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean exists(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Denuncia> findbyid(String id) {
		// TODO Auto-generated method stub
		return iDenunciaDao.findById(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}


}
