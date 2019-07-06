package Weekopdracht1;

import java.util.ArrayList;
import java.util.Collections;

public class Kaarten {

	String type; // Het type kaart (Schoppen, harten, klaveren en ruiten
	String plaatje; // Het plaatje wat op de kaart staat (Boer, Vrouw, Heer, Aas)
	int waarde; // De waarde van de kaart

	ArrayList<Kaarten> kaartenspel = new ArrayList<Kaarten>();

	public static void main(String[] args) {

	}

	/*
	 * Methode om het spel met kaarten aan te maken. In totaal worden er 52 kaarten
	 * aangemaakt.
	 */
	public void kaartSpelAanmaken() {
		waarde = 11;

		for (int j = 1; j < 10; j++) {

			kaartenspel.add(new Kaarten("Schoppen", (j + 1)));

		}

		kaartenspel.add(new Kaarten("Schoppen", "Boer", 10));
		kaartenspel.add(new Kaarten("Schoppen", "Vrouw", 10));
		kaartenspel.add(new Kaarten("Schoppen", "Heer", 10));
		kaartenspel.add(new Kaarten("Schoppen", "Aas", waarde));

		for (int j = 1; j < 10; j++) {

			kaartenspel.add(new Kaarten("Harten", (j + 1)));
		}

		kaartenspel.add(new Kaarten("Harten", "Boer", 10));
		kaartenspel.add(new Kaarten("Harten", "Vrouw", 10));
		kaartenspel.add(new Kaarten("Harten", "Heer", 10));
		kaartenspel.add(new Kaarten("Harten", "Aas", waarde));

		for (int j = 1; j < 10; j++) {

			kaartenspel.add(new Kaarten("Klaveren", (j + 1)));
		}

		kaartenspel.add(new Kaarten("Klaveren", "Boer", 10));
		kaartenspel.add(new Kaarten("Klaveren", "Vrouw", 10));
		kaartenspel.add(new Kaarten("Klaveren", "Heer", 10));
		kaartenspel.add(new Kaarten("Klaveren", "Aas", waarde));

		for (int j = 1; j < 10; j++) {

			kaartenspel.add(new Kaarten("Ruiten", (j + 1)));
		}

		kaartenspel.add(new Kaarten("Ruiten", "Boer", 10));
		kaartenspel.add(new Kaarten("Ruiten", "Vrouw", 10));
		kaartenspel.add(new Kaarten("Ruiten", "Heer", 10));
		kaartenspel.add(new Kaarten("Ruiten", "Aas", waarde));

	}

	/*
	 * Methode om de kaarten te laten zien in het terminal venster.
	 */
	public void kaartenLatenZien() {
		System.out.printf("%11s %25s", "Kaart type", "Kaart waarde");
		System.out.println();
		System.out.println("-----------------------------------------------------------------------------");
		for (Kaarten kaarten : kaartenspel) {
			String result = String.format("%10s %10s %10d", kaarten.getType(), kaarten.getPlaatje(),
					kaarten.getWaarde());
			System.out.print(result + "\t");

			System.out.println();
		}
	}

	/*
	 * Methode om de kaarten te schudden.
	 */
	public void schudKaarten() {
		Collections.shuffle(kaartenspel);
	}

	public Kaarten() {
	}

	/*
	 * Constructor om kaarten aan te maken.
	 */
	public Kaarten(String type, int waarde) {
		this.type = type;
		this.waarde = waarde;
	}

	/*
	 * Constructor om de 'Aces'/Aas aan te maken met variabele waarde.
	 */
	public Kaarten(String type, String plaatje, int waarde) {
		this.type = type;
		this.plaatje = plaatje;
		this.waarde = waarde;
	}

	/*
	 * Accessormethode om het type kaart op te vragen
	 */
	public String getType() {
		return type;
	}

	/*
	 * Accessormethode om het plaatje van de kaart op te vragen
	 */
	public String getPlaatje() {
		String s = "";
		if (plaatje != null) {
			return s = plaatje;
		} else
			return s;
	}

	/*
	 * Accessormethode om de waarde van de kaart op te vragen.
	 */
	public int getWaarde() {
		return waarde;
	}
}
