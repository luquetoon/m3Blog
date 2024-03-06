package m3.uf5.pt1;

import java.util.ArrayList;
import java.util.Date;	
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

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
			if (entrada.getData().equals(data) && entrada.getTitol().equals(titol)) {
				return entrada;
			}
		}
		return null;
	}

	public Usuari cercarUsuariPerMail(String mail) {
		Optional<Usuari> usuariOptional = usuaris.stream().filter(u -> u.getMail().equals(mail)).findFirst();
		return usuariOptional.orElse(null);
	}

	public Usuari cercarUsuariPerNick(String nick) {
		Optional<Usuari> usuariOptional = usuaris.stream().filter(u -> u.getNick().equals(nick)).findFirst();
		return usuariOptional.orElse(null);
	}

	public void nouUsuari(String nick, String mail) {
		if (usuaris.stream().anyMatch(u -> u.getMail().equals(mail) || u.getNick().equals(nick))) {
			throw new IllegalArgumentException("Ja existeix un usuari amb aquest e-mail o nick.");
		}
		Usuari usuari = new Usuari(nick, mail);
		usuaris.add(usuari);
	}

	public void afegirEntrada(String mail, String text, String titol) {
		Usuari usuari = cercarUsuariPerMail(mail);
		if (usuari == null) {
			throw new IllegalArgumentException("No existeix cap usuari amb aquest e-mail.");
		}

		Date currentDate = new Date();
		Entrada entrada = new Entrada(usuari, text, titol);
		Entrada existingEntrada = entrades.stream()
				.filter(e -> e.getData().equals(currentDate) && e.getTitol().equals(titol))
				.findFirst()
				.orElse(null);
		if (existingEntrada != null) {
			throw new IllegalArgumentException("Ja existeix una entrada amb aquest títol per la data d'avui.");
		}

		entrada.setData(currentDate);
		entrades.add(entrada);
		
	}

	public String imprimirEntrada(Date data, String titol) {
		Entrada entrada = cercarEntradaPerDataTitol(data, titol);
		if (entrada == null) {
			throw new IllegalArgumentException("No existeix cap entrada amb aquest títol per la data indicada.");
		}

		return entrada.imprimirPublicacio(0, AMPLE_CONTENT);
	}


	public void comentarEntrada(String mail, Date data, String titol, String text, int valoracion) throws Exception {
		if(cercarEntradaPerDataTitol(data, titol) == null){
			throw new Exception("No existeix cap entrada");
		}
		
		Usuari usuari = cercarUsuariPerMail(mail);
		usuari.afegirPublicacio(new Comentari(usuari, text, valoracion));
	}

	public void desarDadesBlog(String blogFilename) {
		// TODO Auto-generated method stubs

	}

	public String imprimirBlog() {
		return null;
	}


}