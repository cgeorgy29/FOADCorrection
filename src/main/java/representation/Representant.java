package representation;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Representant
{
	private final int numero;
	
	private final String nom;
	
	private final String prenom;
	
	private String adresse;
	
	private float salaireFixe;
	
	// Les chiffres d'affaire ne sont pas initialisés
	private final Map<Integer, Float> CAMensuel = new HashMap<>();
	
	private ZoneGeographique secteur;

	public Representant(int numero, String nom, String prenom, ZoneGeographique secteur) {
		this.numero = numero;
		this.nom = nom;
		this.prenom = prenom;
		// La vérification du paramètre (non null) est faite dans la méthode setSecteur
		setSecteur(secteur);
		// Il faut initialiser tous les CA dans la map
		for (int key = 0; key < 12; key++)
			CAMensuel.put(key, 0f);
		// Ca peut s'écrire en utilisant les Streams et les lambda-expressions
		// https://dzone.com/articles/a-guide-to-streams-in-java-8-in-depth-tutorial-wit
		// https://www.w3schools.com/java/java_lambda.asp
		IntStream.range(0, 12).forEach(key -> CAMensuel.put(key, 0f));
	}

	public int getNumero() {
		return numero;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public float getSalaireFixe() {
		return salaireFixe;
	}

	public void setSalaireFixe(float salaireFixe) {
		this.salaireFixe = salaireFixe;
	}

	public ZoneGeographique getSecteur() {
		return secteur;
	}

	public void setSecteur(ZoneGeographique secteur) {
		// Assurer la cardinalité 1
		if (null == secteur)
			throw new IllegalArgumentException("Secteur est null");
		
		this.secteur = secteur;
	}
	/**
	 * Enregistre le CA de ce représentant pour un mois donné. 
	 * @param mois le numéro du mois (de 0 à 11)
	 * @param montant le CA réalisé pour ce mois (>= 0)
	 **/
	public void enregistrerCA( int mois, float montant ) {
		// vérifier les paramètres
		if (mois < 0 || mois > 11)
			throw new IllegalArgumentException("Le mois doit être compris entre 0 et 11");
		if (montant < 0)
			throw new IllegalArgumentException("Le montant doit être positif ou zéro");
		// Implémenter la méthode
		CAMensuel.put(mois, montant);
		
	}
	/**
	 * Calcule le salaire mensuel de ce répresentant pour un mois donné
	 * @param mois le numéro du mois (de 0 à 11)
	 * @param pourcentage le pourcentage (>= 0 ) à appliquer sur le CA réalisé pour ce mois
	 * @return le salaire pour ce mois, tenant compte du salaire fixe, de l'indemnité repas, et du pourcentage sur CA
	 */	
	public float salaireMensuel( int mois, float pourcentage ) {
		// vérifier les paramètres
		if (mois < 0 || mois > 11)
			throw new IllegalArgumentException("Le mois doit être compris entre 0 et 11");
		if (pourcentage < 0)
			throw new IllegalArgumentException("Le pourcentage doit être positif ou null");
		// Implémenter la méthode
		// On prend le salaire fixe
		float result = getSalaireFixe();
		// On ajoute le pourcentage sur chiffre d'affaire
		result += CAMensuel.get(mois) * pourcentage;
		// On ajoute l'indemnité repas de la zone
		result += secteur.getIndemniteRepas();
		
		return result;	
	}

	@Override
	public String toString() {
		return "Representant{" + "numero=" + getNumero() + ", nom=" + getNom() + ", prenom=" + getPrenom() + '}';
	}
	
	
}
