package m3.uf5.pt1;

import java.util.Objects;
import java.util.LinkedList;
import java.util.Queue;

public class Usuari {

    public static final int JUNIOR_LIMIT = 2;
    public static final int SENIOR_LIMIT = 5;
    private String nick;
    private String mail;
    private Queue<Publicacio> publicacions; 

    public Usuari(String mail, String nick) throws Exception {
        super();
        if (nick == null || "".equals(nick)) {
            throw new Exception("Cal indicar el nick");
        }
        if (mail == null || "".equals(mail)) {
            throw new Exception("Cal indicar el mail");
        }
        this.mail = mail;
        this.nick = nick;
        this.publicacions = new LinkedList<>(); 
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

    public void setPublicacions(Queue<Publicacio> publicacions) {
        this.publicacions = publicacions;
    }

    public void afegirPublicacio(Publicacio pub) {
        if (pub != null) {
            publicacions.add(pub);
        }
    }

    public String nivellUsuari() {
        int numPublicacions = this.publicacions.size();
        if (numPublicacions <= JUNIOR_LIMIT) {
            return "Júnior";
        } else if (numPublicacions <= SENIOR_LIMIT) { // Corrijo la comparación
            return "Sènior";
        } else {
            return "Màster";
        }
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
