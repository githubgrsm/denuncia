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

import gob.grsm.denuncia.repo.IDenuncianteDao;
import gob.grsm.denuncia.model.Denunciante;
import gob.grsm.denuncia.specification.ConvertObjectToFormatJson;
import gob.grsm.denuncia.specification.Filter;




@Service
@Transactional
public class DenuncianteService implements IAdicseService<Denunciante, Long> {
	
	@Autowired
	private IDenuncianteDao iDenuncianteDao;
	
	@Autowired
	private ConvertObjectToFormatJson convertObjectToFormatJson; 
	

	
	
	@Override
	public Page<Denunciante> pagination(Integer pagenumber, Integer rows, String sortdireccion, String sortcolumn,
			Object filter) {
		
		Sort sort = new Sort(sortdireccion.toUpperCase() == "DESC" ? Direction.DESC : Direction.ASC, sortcolumn);
		Pageable pageable =  PageRequest.of(pagenumber, rows, sort);
		
		Filter f = convertObjectToFormatJson.ConvertObjectToFormatSpecification(filter);

		Page<Denunciante> lista = selectFrom(iDenuncianteDao).where(f).findPage(pageable);
	
 

		return lista;
	}

	@Override
	public List<Denunciante> getall() {
		// TODO Auto-generated method stub
		return (List<Denunciante>) iDenuncianteDao.findAll();
	}

	@Override
	public List<Denunciante> getallbyid(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Denunciante create(Denunciante denunciante) {
		// TODO Auto-generated method stub
		if(denunciante.getIdDenunciante() == 0) {
			Long id = iDenuncianteDao.getMax()==null?1L:iDenuncianteDao.getMax()+1;
			denunciante.setIdDenunciante(id);
		}
		return iDenuncianteDao.save(denunciante);
	}

	@Override
	public Denunciante update(Denunciante denunciante) {
		// TODO Auto-generated method stub
		Denunciante denuncianteUpdate = iDenuncianteDao.findById(denunciante.getIdDenunciante()   ).get();

		BeanUtils.copyProperties(denunciante, denuncianteUpdate);
		return iDenuncianteDao.save(denuncianteUpdate);
		
	}

	@Override
	public void delete(Denunciante denunciante) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletebyid(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean exists(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Denunciante> findbyid(Long id) {
		// TODO Auto-generated method stub
		return iDenuncianteDao.findById(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}


}
