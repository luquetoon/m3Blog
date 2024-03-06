package m3.uf5.pt1;

import java.util.Objects;
import java.util.Queue;

public class Usuari {

	public static final int JUNIOR_LIMIT = 2;
	public static final int SENIOR_LIMIT = 5;
	private String nick;
	private String mail;
	private Queue<Publicacio> publicacions;
	
	public Usuari(String mail, String nick) {
		super();
		this.mail = mail;
		this.nick = nick;
	}
	

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Queue<Publicacio> getPublicacions() {
		return publicacions;
	}



	protected void setPublicacions(Queue<Publicacio> publicacions) {
		this.publicacions = publicacions;
	}



	void afegirPublicacio(Publicacio pub) {
	    if (pub != null) {
	    	publicacions.add(pub);
	    }
	}
	
	public String nivellUsuari() {
	    int numPublicacions = this.publicacions.size();
	    if (numPublicacions <= 2) {
	        return "Júnior";
	    } else if (numPublicacions > 2 && numPublicacions <= 5) {
	        return "Sènior";
	    } else if (numPublicacions > 5) {
	        return "Màster";
	    }
	    return "";
	}
	
	 @Override
	    public int hashCode() {
	        return Objects.hash(mail);
	    }

	    @Override
	    public boolean equals(Object obj) {
	        if (this == obj) return true;
	        if (obj == null || getClass() != obj.getClass()) return false;
	        Usuari usuari = (Usuari) obj;
	        return Objects.equals(mail, usuari.mail);
	    }
}
