package m3.uf5.pt1;

import java.util.Date;

public abstract class Publicacio {
	
	protected Usuari usuari;
	protected String Text;
	protected Date data;
	
	public Publicacio(Usuari usuari, String text) {
		super();
		this.usuari = usuari;
		Text = text;
	}

	public abstract String imprimirPublicacio(int ident, int width);
	
	public Usuari getUsuari() {
		return usuari;
	}

	public void setUsuari(Usuari usuari) {
		this.usuari = usuari;
	}

	public String getText() {
		return Text;
	}

	public void setText(String text) {
		Text = text;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
}
