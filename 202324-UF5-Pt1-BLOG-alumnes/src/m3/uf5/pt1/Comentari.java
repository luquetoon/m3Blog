package m3.uf5.pt1;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;

public class Comentari extends Publicacio implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int IDENT_COMMENT = 5;
	public static final int IDENT_INC = 2;
	protected static Map<Integer, String> valoracions = new TreeMap<>();
	static {
	    valoracions.put(0, "0-Stars");
	    valoracions.put(1, "1-Star");
	    valoracions.put(2, "2-Stars");
	    valoracions.put(3, "3-Stars");
	}
	private int valoracio;
	
	
	public Comentari() {
	}

	public Comentari(Usuari usuari, String text, int valoracio) throws Exception {
		super(usuari, text);
		this.setValoracio(valoracio);
	}
	
	public Map<Integer, String> getValoracions() {
		return valoracions;
	}

	public void setValoracions(Map<Integer, String> valoracions) {
		Comentari.valoracions = valoracions;
	}

	public int getValoracio() {
		return valoracio;
	}

	public void setValoracio(int valoracio) {
		this.valoracio = valoracio;
	}
	
	public static boolean containsValoracio(int key) {
	    return valoracions.containsKey(key);
	}
	
	public static String getTextValoracio(int key) {
	    if (valoracions.containsKey(key)) {
	        return valoracions.get(key);
	    }
	    return null;
	}


	@Override
	public String imprimirPublicacio(int ident, int width) {
		String date = data.toString();
	    String textWrapped = WordUtils.wrap(text, Blog.AMPLE_CONTENT);
	    String[] textSplitted = textWrapped.split("\n");

	    StringBuilder result = new StringBuilder();
	    for (int i = 0; i < textSplitted.length; i++) {
	        result.append(StringUtils.repeat(" ", Blog.AMPLE_LEFT));
	        result.append(Entrada.SEPARADOR);
	        result.append(textSplitted[i]);
	        result.append(System.lineSeparator());
	    }

	    return result.toString();
	}


	

	
}

