package Weekopdracht1;

import java.util.*;
import java.time.*;
import java.time.temporal.ChronoUnit;

/*
 * Blackjack: regels volgens https://nl.wikipedia.org/wiki/Blackjack
 * 
 * @author: Roelof Daanje
 * @version: 1.0
 * 
 */

public class Blackjack {

	boolean stoppen = false;

	ArrayList<Kaarten> bank = new ArrayList<Kaarten>();

	Scanner scanner = new Scanner(System.in);

	int timerBeurt = 10;
	LocalTime[] nu = new LocalTime[2];

	public static void main(String[] args) {

		new Blackjack();

	}

	/*
	 * Start het spel.
	 */
	public Blackjack() {

		Kaarten deck = new Kaarten();

		deck.kaartSpelAanmaken();

		System.out.println(
				"Welkom bij Blackjack.\n" + "U bent de eerste speler.\n" + "Vul uw naam hier in om te beginnen!");
		String input = scanner.next();

		Spelers speler1 = new Spelers(input);
		Spelers dealer = new Spelers();

		System.out.println("Welkom " + input);

		System.out.println("Wilt u het deck met kaarten controleren? (y/n)");
		String input2 = scanner.next();
		if (input2.contains("y")) {

			deck.kaartenLatenZien();
			System.out.println("De kaarten zullen nu opnieuw geschud worden!");
			deck.schudKaarten();
		} else {

			deck.schudKaarten();
		}

		System.out.println("Het spel zal nu beginnen");
		eersteRondeSpelen(speler1, dealer, deck.kaartenspel);

		while (stoppen = false) {
			keuzeNaRonde(speler1, dealer, deck.kaartenspel);
		}
	}

	/*
	 * Start de eerste rond waarbij de speler 2 kaarten krijgt en de dealer 1 kaart.
	 */
	public void eersteRondeSpelen(Spelers speler, Spelers dealer, ArrayList<Kaarten> kaartenspel) {

		System.out.println("De bank geeft u nu 2 kaarten\n" + "Deze kaarten zijn:\n");
		for (int i = 0; i < 2; i++) {

			speler.hand.add(kaartenspel.get(0));
			kaartenspel.remove(0);
		}

		speler.berekenScore(speler.hand);

		System.out.println("De dealer heeft de kaart:");
		dealer.hand.add(kaartenspel.get(0));
		kaartenspel.remove(0);
		dealer.berekenScore(dealer.hand);

		checkScore(speler, dealer, kaartenspel, speler.hand);
	}

	/*
	 * Geeft de speler 3 opties: k= kaart vragen, p = passen en q = het spel
	 * stoppen. De keuze moet gemaakt worden binnen 10 seconden.
	 */
	public void keuzeNaRonde(Spelers speler, Spelers dealer, ArrayList<Kaarten> kaartenspel) {

		String input = scanner.next();

		nu[1] = LocalTime.now();

		if (input.contains("k")) {
			if (ChronoUnit.SECONDS.between(nu[0], nu[1]) < timerBeurt) {
				speler.hand.add(kaartenspel.get(0));
				kaartenspel.remove(0);
				System.out.println("U krijg een kaart van de bank\n");
				speler.berekenScore(speler.hand);
				checkScore(speler, dealer, kaartenspel, speler.hand);
			} else {
				System.out.println("Helaas de tijd was al verstreken!");
				eindeGameKeuze(speler, dealer, kaartenspel);
			}

		} else if (input.contains("p")) {
			if (ChronoUnit.SECONDS.between(nu[0], nu[1]) < timerBeurt) {
				System.out.println("Uw kaarten waren: ");
				speler.SpelerShowKaarten(speler.hand);

				System.out.println("De dealer zal nu verder spelen");
				dealerUitspelen(speler, dealer, kaartenspel);
			} else {
				System.out.println("Helaas de tijd was al verstreken!");
				eindeGameKeuze(speler, dealer, kaartenspel);
			}

		} else if (input.contains("q")) {
			System.out.println("Het spel zal nu stoppen. Goed gespeeld!");
			stoppen = true;
			scanner.close();
		} else {
			System.out.println("Voer alstublieft een geldige input in\n"
					+ "\"Wat is uw volgende stap (k = kaart, p = pass, q = stoppen)\"");
			keuzeNaRonde(speler, dealer, kaartenspel);
		}

	}

	

	

	/*
	 * Deze methode geeft de beurt aan de dealer.
	 */
	public void dealerUitspelen(Spelers speler, Spelers dealer, ArrayList<Kaarten> kaartenspel) {

		while ((dealer.berekenScore(dealer.hand) < 17) && (speler.totaal > dealer.totaal)) {

			System.out.println("De dealer pakt een nieuwe kaart");
			dealer.hand.add(kaartenspel.get(0));
			kaartenspel.remove(0);
			dealer.berekenScore(dealer.hand);
		}
		if (dealer.berekenScore(dealer.hand) > 21) {

			System.out.println("U wint dit spelletje!");

		} else {

			System.out.println("U heeft van de dealer verloren\n");

		}
	}

	/*
	 * Deze methode checkt of de speler of de dealer heeft gewonnen/verloren.
	 */
	public void checkScore(Spelers speler, Spelers dealer, ArrayList<Kaarten> kaartenspel, ArrayList<Kaarten> hand) {

		if (speler.totaal >= 22) {
			System.out.println("Helaas u heeft verloren!");
			eindeGameKeuze(speler, dealer, kaartenspel);

		}

		else if (speler.totaal == 21) {
			System.out.println("U heeft gewonnen!");
			eindeGameKeuze(speler, dealer, kaartenspel);

		} else {

			System.out.println("Wat is uw volgende stap (k = kaart, p = pass, q = stoppen)");
			System.out.println("U heeft 10 seconden de tijd om een keuze te maken");
			nu[0] = LocalTime.now();

			keuzeNaRonde(speler, dealer, kaartenspel);

		}

	}

	/*
	 * Deze methode vraagt aan de speler of deze nog een ronde wil spelen.
	 */
	public void eindeGameKeuze(Spelers speler, Spelers dealer, ArrayList<Kaarten> kaartenspel) {

		System.out.println("Wilt u nog een keer spelen? (y/n)");

		String keuze = scanner.next();
		if (keuze.equals("y")) {

			while (!(dealer.hand.isEmpty())) {

				kaartenspel.add(dealer.hand.get(0));
				dealer.hand.remove(0);

			}

			while (!(speler.hand.isEmpty())) {

				kaartenspel.add(speler.hand.get(0));
				speler.hand.remove(0);

			}

			System.out.println("De dealer levert zijn kaarten weer in");
			System.out.println("De speler levert zijn kaarten weer in");
			Collections.shuffle(kaartenspel);
			eersteRondeSpelen(speler, dealer, kaartenspel);

		} else {
			System.out.println("Het spel zal nu stoppen. Goed gespeeld!");

			while (dealer.hand.size() > 0) {

				kaartenspel.add(dealer.hand.get(0));
				dealer.hand.remove(0);

			}

			while (speler.hand.size() > 0) {

				kaartenspel.add(speler.hand.get(0));
				speler.hand.remove(0);
			}

			stoppen = true;
			scanner.close();
		}

	}

}
