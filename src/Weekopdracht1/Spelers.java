package Weekopdracht1;

import java.util.ArrayList;

/*
 * Deze klasse wordt gebruikt om zowel de spelers als hun verzameling kaarten vast te leggen in het programma.
 */
public class Spelers {

	String naam;
	int totaal;
	ArrayList<Kaarten> hand = new ArrayList<Kaarten>();

	public static void main(String[] args) {
		}

	public Spelers() {

	}

	/*
	 * Constructor om spelers aan te maken.
	 */
	public Spelers(String naam) {

		this.naam = naam;

	}

	/*
	 * Methode om de kaarten die een speler momenteel bezit op te vragen.
	 */
	public void SpelerShowKaarten(ArrayList<Kaarten> hand) {

		System.out.printf("%11s %25s", "Kaart type", "Kaart waarde");
		System.out.println();
		System.out.println("-----------------------------------------------------------------------------");

		for (Kaarten kaarten : hand) {
			String result = "";
			result = String.format("%10s %10s %10d", kaarten.getType(), kaarten.getPlaatje(), kaarten.getWaarde());
			System.out.print(result + "\t");
			System.out.println();
			System.out.println();

		}

		String result2 = String.format("%10s", "Totaal aantal punten: " + totaal);
		System.out.println("-----------------------------------------------------------------------------");
		System.out.print(result2 + "\t");
		System.out.println();

	}

	/*
	 * Methode om de totale score van de kaarten in de hand van de speler op te vragen.
	 */
	public int berekenScore(ArrayList<Kaarten> hand) {

		totaal = 0;
		int check = 0;
		String s1 = "Aas";

		for (int i = 0; i < hand.size(); i++) {
			check += hand.get(i).getWaarde();

			if (check >= 22 && (s1.equals(hand.get(i).getPlaatje()))) {	//Checkt of de laatste kaart een Aas/'Ace' is i.v.m. flexibele waarde (1 of 11).

				hand.get(hand.size() - 1).waarde = 1;
				totaal += hand.get(i).getWaarde();
				hand.get(hand.size() - 1).waarde = 11;
			}

			else {

				totaal += hand.get(i).getWaarde();
			}
		}

		SpelerShowKaarten(hand);

		return totaal;
	}

}
