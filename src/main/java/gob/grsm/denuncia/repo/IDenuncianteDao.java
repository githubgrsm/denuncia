package gob.grsm.denuncia.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import gob.grsm.denuncia.model.Denunciante;

public interface IDenuncianteDao extends CrudRepository<Denunciante, Long>,JpaRepository<Denunciante,Long>, 
PagingAndSortingRepository<Denunciante, Long>, 
JpaSpecificationExecutor<Denunciante> {
	
	@Query("SELECT max(p.idDenunciante) FROM Denunciante p")
	Integer getMax();

}
