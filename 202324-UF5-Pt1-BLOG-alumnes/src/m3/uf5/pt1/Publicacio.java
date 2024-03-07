package m3.uf5.pt1;

import java.io.Serializable;
import java.util.Date;

public abstract class Publicacio implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Usuari usuari;
	protected String text;
	protected Date data;
	
	public Publicacio() {
	}
	
	public Publicacio(Usuari usuari, String text) throws Exception {
		super();
		if (usuari == null) {
			throw new Exception("Cal indicar usuari");
		}
		if (text == null || "".equals(text)) {
			throw new Exception("Cal escriure text");
		}
		this.usuari = usuari;
		this.text = text;
		data = new Date();
	}

	public abstract String imprimirPublicacio(int ident, int width);
	
	public Usuari getUsuari() {
		return usuari;
	}

	public void setUsuari(Usuari usuari) {
		this.usuari = usuari;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String imprimirPublicacio(String ident, int width) {
		// TODO Auto-generated method stub
		return null;
	}
}
