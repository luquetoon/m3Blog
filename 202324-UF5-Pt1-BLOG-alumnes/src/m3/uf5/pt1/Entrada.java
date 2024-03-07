package m3.uf5.pt1;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.Calendar;
import java.util.Date;
import java.util.Deque;
import java.util.Iterator;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;

public class Entrada extends Publicacio implements Comparable<Entrada>, Serializable {

    private static final long serialVersionUID = 1L;
    public static final String SEPARADOR = "|";
    public static final String NOT_PROVIDED = "NA";
    private String titol;
    private Deque<Comentari> comentaris = new ArrayDeque<>();

    public Entrada(Usuari usuari, String titol, String text) throws Exception {
        super(usuari, text);
        if (titol == null || "".equals(titol)) {
            throw new Exception("Cal un títol");
        }
        this.titol = titol;

    }

    protected String getTitol() {
        return titol;
    }

    protected void setTitol(String titol) {
        this.titol = titol;
    }

    protected Deque<Comentari> getComentaris() {
        return comentaris;
    }

    protected void setComentaris(Deque<Comentari> comentaris) {
        this.comentaris = comentaris;
    }

    public void afegirComentari(Usuari usuari, String text, int valoracio) throws Exception {
        if (usuari != null && text != null) {
            Comentari comentari = new Comentari(usuari, text, valoracio);
            comentaris.push(comentari);
        }
    }

    public String valoracioMitjaEntrada() {
        if (comentaris.isEmpty()) {
            return NOT_PROVIDED;
        }
        double sum = 0;
        for (Comentari comentari : comentaris) {
            sum += comentari.getValoracio();
        }
        double average = sum / comentaris.size();
        return String.format("%.1f", average);
    }

    public int totalValoracionsPerValor(int valor) {
        int count = 0;
        for (Comentari comentari : comentaris) {
            if (comentari.getValoracio() == valor) {
                count++;
            }
        }
        return count;
    }


    @Override
    public int compareTo(Entrada e) {
        if (formatter(data).compareTo(formatter(e.getData())) == 0) {
            return this.titol.compareTo(e.getTitol());
        }

        return this.data.compareTo(e.getData());
    }
    
    private Calendar formatter(Date data) {
		Calendar calendari = Calendar.getInstance();
		calendari.setTime(data);
		calendari.set(Calendar.MILLISECOND,0);
		calendari.set(Calendar.MINUTE,0);
		calendari.set(Calendar.HOUR_OF_DAY,0);
		return calendari;
	}
    
    @Override
	public int hashCode() {
		return Objects.hash(data, titol);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Entrada other = (Entrada) obj;
		return Objects.equals(data, other.data) && Objects.equals(titol, other.titol);
	}

	@Override
	public String imprimirPublicacio(int ident, int width) {
		 SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy");
	        String date = sdf.format(data);
	        String textWrapped = WordUtils.wrap(text, width);
	        String[] textSplitted = textWrapped.split("\n");

	        StringBuilder result = new StringBuilder();

	        result.append(StringUtils.rightPad(date.toUpperCase(), Blog.AMPLE_LEFT));
	        result.append(SEPARADOR);
	        result.append(StringUtils.center(titol, Blog.AMPLE_CONTENT));
	        result.append(System.lineSeparator());
	        result.append(StringUtils.leftPad(usuari.getNick() + " " + usuari.nivellUsuari(), Blog.AMPLE_LEFT));
	        result.append(SEPARADOR);
	        result.append(StringUtils.center(StringUtils.repeat("-", titol.length()), Blog.AMPLE_CONTENT));
	        result.append(System.lineSeparator());
	        result.append(StringUtils.repeat(" ", Blog.AMPLE_LEFT));
	        result.append(SEPARADOR);
	        result.append(StringUtils.repeat(" ", Blog.AMPLE_CONTENT));
	        result.append(System.lineSeparator());

	        for (int i = 0; i < textSplitted.length; i++) {
	            if (i <= 3) {
	                result.append(StringUtils.rightPad(Comentari.getTextValoracio(i) + " : " + totalValoracionsPerValor(i), Blog.AMPLE_LEFT));
	            } else if (i == 4) {
	                result.append(StringUtils.rightPad("Mitjana : " + valoracioMitjaEntrada(), Blog.AMPLE_LEFT));
	            } else {
	                result.append(StringUtils.repeat(" ", Blog.AMPLE_LEFT));
	            }
	            result.append(SEPARADOR);
	            result.append(textSplitted[i]);
	            result.append(System.lineSeparator());
	        }

	        result.append(StringUtils.repeat(" ", Blog.AMPLE_LEFT));
	        result.append(SEPARADOR);
	        result.append(StringUtils.repeat(" ", Blog.AMPLE_CONTENT));
	        result.append(System.lineSeparator());

	        Iterator<Comentari> iter = comentaris.iterator();
	        while (iter.hasNext()) {
	            Comentari c = iter.next();
	            result.append(c.imprimirPublicacio(" ", Blog.AMPLE_CONTENT));
	        }

	        return result.toString();
	}

	
}
