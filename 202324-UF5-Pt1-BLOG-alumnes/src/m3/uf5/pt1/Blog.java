package m3.uf5.pt1;


import java.util.Calendar;
import java.util.Date;	
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;

public class Blog {

	public static final int AMPLE_LEFT = 15;
	public static final int GAP = 3;
	public static final int AMPLE_CONTENT = 50;
	Set<Usuari> usuaris = new HashSet<>();
	TreeSet<Entrada> entrades = new TreeSet<>();

	public Blog() {
	}

	public Entrada cercarEntradaPerDataTitol(Date data, String titol) {
		for (Entrada entrada : entrades) {
			if (formatter(entrada.getData()).compareTo(formatter(data)) == 0 && entrada.getTitol().equals(titol)) {
				return entrada;
			}
		}
		return null;
	}
	
	private Calendar formatter(Date data) {
		Calendar calendari = Calendar.getInstance();
		calendari.setTime(data);
		calendari.set(Calendar.MILLISECOND,0);
		calendari.set(Calendar.MINUTE,0);
		calendari.set(Calendar.HOUR_OF_DAY,0);
		return calendari;
	}

	public Usuari cercarUsuariPerMail(String mail) {
		Iterator iter = usuaris.iterator();
		while (iter.hasNext()) {
			Usuari u = (Usuari) iter.next();
			if (u.getMail().equals(mail)) {
				return u;
			}
		}
		return null;
	}

	public Usuari cercarUsuariPerNick(String mail) {
		Iterator iter = usuaris.iterator();
		while (iter.hasNext()) {
			Usuari u = (Usuari) iter.next();
			if (u.getNick().equals(mail)) {
				return u;
			}
		}
		return null;
	}

	public void nouUsuari(String nick, String mail) throws Exception {
		if (cercarUsuariPerMail(mail) != null && cercarUsuariPerNick(nick) != null) {
			throw new IllegalArgumentException("Ja existeix un usuari amb aquest e-mail o nick.");
		}

		usuaris.add(new Usuari(nick, mail));
	}

	public void afegirEntrada(String mail,  String titol, String text) throws Exception {
		Entrada entradaRepetida = cercarEntradaPerDataTitol(new Date(), titol);
		if(entradaRepetida == null) {
			Usuari user = cercarUsuariPerMail(mail);
			if(user == null) {
				throw new Exception("No hi ha cap usuari amb aquest mail");
			}

			Entrada novaEntrada = new Entrada(user, titol, text);
			entrades.add(novaEntrada);
			for(Usuari usuari : usuaris) {
				if (usuari.equals(user)) {
					usuari.afegirPublicacio(novaEntrada);
				}
			}
		}else {
			throw new Exception("Ja existeix aquesta entrada");
		}

	}

	public String imprimirEntrada(Date data, String titol) {
		Entrada entrada = cercarEntradaPerDataTitol(data, titol);
		if (entrada == null) {
			throw new IllegalArgumentException("No existeix cap entrada amb aquest títol per la data indicada.");
		}
		return entrada.imprimirPublicacio(0, AMPLE_CONTENT);
	}

	public void comentarEntrada(String mail, Date data, String titol, String text, int valoracion) throws Exception {
		Entrada entradaRepetida = cercarEntradaPerDataTitol(data, titol);

        if (entradaRepetida != null) {
            Usuari usuariEscriptor = cercarUsuariPerMail(mail);

            if (usuariEscriptor == null) {
                throw new Exception("No hi ha cap usuari amb aquest mail");
            }

            for (Entrada entrada : entrades) {
                if (entrada.compareTo(entradaRepetida) == 0) {
                    entrada.afegirComentari(usuariEscriptor, text, valoracion);
                }
            }

            for (Usuari usuari : usuaris) {
                if (usuari.equals(usuariEscriptor)) {
                    usuari.afegirPublicacio(new Comentari(usuari, text, valoracion));
                }
            }
        } else {
            throw new Exception("No hi ha cap entrada per poder comentar.");
        }
	}	

	public void desarDadesBlog(String blogFilename) {
		// TODO Auto-generated method stubs

	}

	public String imprimirBlog() {
		String result = "";
		result += StringUtils.repeat("^", AMPLE_CONTENT + GAP + AMPLE_LEFT);
		result += System.lineSeparator();
		Iterator iter = entrades.iterator();
		while (iter.hasNext()) {
			Entrada e = (Entrada) iter.next();
			result += imprimirEntrada(e.getData(), e.getTitol());
		}
		return result;
	}


}