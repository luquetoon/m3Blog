package m3.uf5.pt1;

import java.util.ArrayDeque;
import java.util.Deque;
import org.apache.commons.text.WordUtils;

public class Entrada extends Publicacio implements Comparable<Entrada>{

		public static final String SEPARADOR = "|";
		public static final String NOT_PROVIDED = "NA";
		private String titol;
		private Deque<Comentari> comentaris = new ArrayDeque<>();
		
		public Entrada(Usuari usuari, String text, String titol) {
			super(usuari, text);
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
		
		//arreglar
		public void afegirComentari(Usuari usuari, String text, int valoracio) {
		    if (usuari != null && text != null && comentaris.contains(valoracio)) {
		        Comentari comentari = new Comentari(usuari, text, valoracio);
		        comentaris.add(comentari);
		    }
		}
		
		public String valoracioMitjaEntrada() {
		    if (comentaris.isEmpty()) {
		        return Entrada.NOT_PROVIDED;
		    }
		    int sum = 0;
		    int count = 0;
		    for (Comentari comentari : comentaris) {
		        sum += comentari.getValoracio();
		        count++;
		    }
		    double average = (double) sum / count;
		    return String.format("%.1f", average);
		}
		
		
		//arreglar
		public int totalValoracionsPerValor(int valor) {
		    if (!comentaris.contains(valor)) {
		        throw new IllegalArgumentException("La valoració indicada no és vàlida.");
		    }
		    int count = 0;
		    for (Comentari comentari : comentaris) {
		        if (comentari.getValoracio() == valor) {
		            count++;
		        }
		    }

		    return count;
		}

		@Override
		public String imprimirPublicacio(int ident, int width) {
//		    StringBuilder sb = new StringBuilder();
//
//		    String dateString = WordUtils.wrap(this.data.toString(), width);
//		    String authorString = WordUtils.wrap(this.autor.getNom(), width);
//		    String ratingsString = WordUtils.wrap("Valoracions: " + this.valoracioTotal(), width);
//
//		    int col1Width = (width - ident) / 3;
//		    int col2Width = 2 * col1Width;
//
//		    String titleString = WordUtils.wrap(this.titol, col2Width);
//
//		    sb.append(String.format("%-" + col1Width + "s| %-" + col1Width + "s| %-" + col1Width + "s|\n", dateString, authorString, ratingsString));
//		    sb.append(String.format("%-" + col1Width + "s| %-" + col2Width + "s|\n", "", titleString));
//
//		    for (String line : WordUtils.wrap(this.text, width - ident).split("\n")) {
//		        sb.append(String.format("%-" + col1Width + "s| %-" + col2Width + "s|\n", "", line));
//		    }
//
//		    return sb.toString();
			return null;
		}

		@Override
		public int compareTo(Entrada o) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		
		
}
