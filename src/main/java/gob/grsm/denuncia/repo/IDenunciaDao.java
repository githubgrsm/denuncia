package gob.grsm.denuncia.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import gob.grsm.denuncia.model.Denuncia;

public interface IDenunciaDao extends CrudRepository<Denuncia, String>,JpaRepository<Denuncia,String>, 
PagingAndSortingRepository<Denuncia, String>, 
JpaSpecificationExecutor<Denuncia> {
	
	@Query("SELECT max(p.idDenuncia) FROM Denuncia p")
	Integer getMax();

}
