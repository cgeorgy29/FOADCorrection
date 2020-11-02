package representation;

public class Representant
{
	private final int numero;
	
	private final String nom;
	
	private final String prenom;
	
	private String adresse;
	
	private float salaireFixe;
	
	// Les chiffres d'affaire sont initialisés à 0
	private final float[] CAMensuel = new float[12];
	
	private ZoneGeographique secteur;

	public Representant(int numero, String nom, String prenom, ZoneGeographique secteur) {
		this.numero = numero;
		this.nom = nom;
		this.prenom = prenom;
		// La vérification du paramètre (non null) est faite dans la méthode setSecteur
		setSecteur(secteur);
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

	
	
	public void enregistrerCA( int mois, float montant ) {
		// vérifier les paramètres
		if (mois < 0 || mois > 11)
			throw new IllegalArgumentException("Le mois doit être compris entre 0 et 11");
		if (montant < 0)
			throw new IllegalArgumentException("Le montant doit être positif ou null");
		// Implémenter la méthode
		CAMensuel[mois] = montant;
		
	}
	
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
		result += CAMensuel[mois] * pourcentage;
		// On ajoute l'indemnité repas de la zone
		result += secteur.getIndemniteRepas();
		
		return result;	
	}

	@Override
	public String toString() {
		return "Representant{" + "numero=" + getNumero() + ", nom=" + getNom() + ", prenom=" + getPrenom() + '}';
	}
	
	
}
