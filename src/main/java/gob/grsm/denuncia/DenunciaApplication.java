package gob.grsm.denuncia;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module.Feature;

@SpringBootApplication
public class DenunciaApplication {
	
	@Bean
	@Primary
	public ObjectMapper jsonMapper() {
	
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		// TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		TimeZone.setDefault(TimeZone.getTimeZone("GMT"));

		ObjectMapper mapper = new ObjectMapper();
		Hibernate5Module hm = new Hibernate5Module();
		hm.enable(Feature.SERIALIZE_IDENTIFIER_FOR_LAZY_NOT_LOADED_OBJECTS);

		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

		mapper.registerModule(hm);

		mapper.setSerializationInclusion(Include.NON_EMPTY);

		mapper.setDateFormat(df);

		return mapper;
	}		

	public static void main(String[] args) {
		SpringApplication.run(DenunciaApplication.class, args);
	}

}

