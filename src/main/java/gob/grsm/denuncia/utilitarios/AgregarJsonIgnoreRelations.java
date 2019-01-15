package gob.grsm.denuncia.utilitarios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AgregarJsonIgnoreRelations {
	static List<String> archivosSeleccionado = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		
		
		String rutaBase = "D:/proyectos/backend/eleccion/src/main/java/com/adicse/eleccion/model";
		
		
		archivosSeleccionado.add("Plantilla002.java");
		archivosSeleccionado.add("Voto002.java");
		
		
		List<String> archivosModel = getFiles(rutaBase);
		for(String row:archivosModel) {
			System.out.println(row);
			List<TextoLine> lstTextoLine = escribirInList(rutaBase + "/" +row);

			List<TextoLine> lstTextoLineCopiaModificado = agregarJsonIgnoreBeforeManyToOne(lstTextoLine);
			
			//Grabamos el archivo
			File file = new File(rutaBase + "/" +row);
			writeFile(lstTextoLineCopiaModificado,file);
		}
		
	}
	
	
	
	public static List<String> getFiles(String ruta){
		
		
		List<String> lstFile = new ArrayList<>();
		File folderFile = new File(ruta);

		if ((folderFile.exists())) {
			File[] files = folderFile.listFiles( new FilenameFilter() {

				@Override
				public boolean accept(File dir, String name) {
					// TODO Auto-generated method stub
					boolean rtn = false;
					if( existeInList(name)){
						rtn = true;
					}			
					return rtn;
				}
				
			});
			for (File file : files) {
				// boolean isFolder = file.isDirectory();
				// System.out.println((isFolder ? "FOLDER: " : " FILE: ") + file.getName());
				lstFile.add(file.getName());
			}
		}else {
			System.out.println("Folder no existe");
		}

		return lstFile;		
		
		

	}
	
	
	public static boolean existeInList(String name) {
		
		boolean rtn = false;
		for(String row : archivosSeleccionado) {
			if(row.equals(name)) {
				return true;
			}
		}
		return rtn;
		
	}
	
	
	private static List<TextoLine> escribirInList(String sFile) {
		BufferedReader br;
		TextoLine textoLine = null;
		List<TextoLine> lstTextoLine = new ArrayList<>();
		Integer cnt = 1;
		try {
			br = new BufferedReader(new FileReader(sFile));

			String line = "";
			while ((line = br.readLine()) != null) {
				textoLine = new TextoLine();
				textoLine.setNrolinea(cnt);
				
				textoLine.setTexto(line);
				
				lstTextoLine.add(textoLine);
				
				
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lstTextoLine;

	}	

	
	private static List<TextoLine> agregarJsonIgnoreBeforeManyToOne(List<TextoLine> archivoLst) {
		
		List<TextoLine> lstTextoLineUpdate = new ArrayList<>();
		Integer cntFila = 1;
		TextoLine _textoLine ;
		Boolean existeManyToOne = false;
		TextoLine textoLineOld = null;

		for(TextoLine row:archivoLst) {
			Integer existe = row.getTexto().indexOf("@ManyToOne");
			if(existe != -1) {
				
				existeManyToOne = true;
				
				//verificamos si la linea anterior no existe @JsonIgnore
				Integer existeAnt = -1;
				if(textoLineOld != null) {
					existeAnt = textoLineOld.getTexto().indexOf("@JsonIgnore");
				}
				
				if(existeAnt == -1) {
					_textoLine = new TextoLine();
					_textoLine.setNrolinea(cntFila);
					_textoLine.setTexto("\t@JsonIgnore");
					lstTextoLineUpdate.add(_textoLine);
					cntFila ++;					
				}

				
			}
			row.setNrolinea(cntFila);
			lstTextoLineUpdate.add(row);
			textoLineOld = new TextoLine(row.getNrolinea(),row.getTexto());

			cntFila ++;
		}
		
		//Buscamos el paquete jackson ignore.... si no esta lo agregamos.
		Boolean existePackage = false;
		
		if(existeManyToOne) {
			for(TextoLine rowPackage:lstTextoLineUpdate) {
				Integer existe = rowPackage.getTexto().indexOf("import com.fasterxml.jackson.annotation.JsonIgnore;");
				if(existe != -1) {
					existePackage = true;
					break;
				}

			}		
			
		}
		
		List<TextoLine> lstTextoLineUpdatePackage = new ArrayList<>();
		if(!existePackage && existeManyToOne) {
			
			cntFila = 1;

			for(TextoLine rowUpdate:lstTextoLineUpdate) {

				if(cntFila.equals(3) ) {
					_textoLine = new TextoLine();
					_textoLine.setNrolinea(cntFila);
					_textoLine.setTexto("import com.fasterxml.jackson.annotation.JsonIgnore;");
					lstTextoLineUpdatePackage.add(_textoLine);
					cntFila ++;
					
				}
				rowUpdate.setNrolinea(cntFila);
				lstTextoLineUpdatePackage.add(rowUpdate);
				cntFila ++;
			}			
			return lstTextoLineUpdatePackage;
		}
		
		return lstTextoLineUpdate;
		
		
	}

	@SuppressWarnings("unused")
	private static void writeFile(List<TextoLine> lstTextoLine,File file) {
		FileWriter fw=null;
		try {
			
			fw = new FileWriter(file);
			String line = "";
			for(TextoLine row:lstTextoLine) {
				line = row.getTexto();
				fw.write(line);
				line = "\n";
				fw.write(line);
			}
			
			
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
