package stark.foe.gm.beans;

public class GM {

	private Long id;
	private String name;
	private Age age;
	private String image;
	private Short x;
	private Short y;
	private CompetenceGM competenceGM1;
	private CompetenceGM competenceGM2;
	private RecompensesGM recompenses;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Age getAge() {
		return age;
	}
	
	public void setAge(Age age) {
		this.age = age;
	}
	
	public RecompensesGM getRecompenses() {
		return recompenses;
	}

	public void setRecompenses(RecompensesGM recompenses) {
		this.recompenses = recompenses;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Short getX() {
		return x;
	}

	public void setX(Short tailleX) {
		this.x = tailleX;
	}

	public Short getY() {
		return y;
	}

	public void setY(Short tailleY) {
		this.y = tailleY;
	}

	public CompetenceGM getCompetenceGM1() {
		return competenceGM1;
	}

	public void setCompetenceGM1(CompetenceGM competenceGM1) {
		this.competenceGM1 = competenceGM1;
	}

	public CompetenceGM getCompetenceGM2() {
		return competenceGM2;
	}

	public void setCompetenceGM2(CompetenceGM competenceGM2) {
		this.competenceGM2 = competenceGM2;
	}

	public GM() {}
	
	public GM(Long id) {
		this.id = id;
	}
	
}
