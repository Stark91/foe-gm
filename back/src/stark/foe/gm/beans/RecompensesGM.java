package stark.foe.gm.beans;

public class RecompensesGM {

	private Long id;
	private Age age;
	private int niveau;
	private int total;
	private int p1;
	private int p2;
	private int p3;
	private int p4;
	private int p5;
	private int secuP1;
	private int secuP2;
	private int secuP3;
	private int secuP4;
	private int secuP5;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Age getAge() {
		return age;
	}
	
	public void setAge(Age age) {
		this.age = age;
	}
	
	public int getNiveau() {
		return niveau;
	}
	
	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}
	
	public int getTotal() {
		return total;
	}
	
	public void setTotal(int total) {
		this.total = total;
	}

	public int getP1() {
		return p1;
	}

	public void setP1(int p1) {
		this.p1 = p1;
	}

	public int getP2() {
		return p2;
	}

	public void setP2(int p2) {
		this.p2 = p2;
	}

	public int getP3() {
		return p3;
	}

	public void setP3(int p3) {
		this.p3 = p3;
	}

	public int getP4() {
		return p4;
	}

	public void setP4(int p4) {
		this.p4 = p4;
	}

	public int getP5() {
		return p5;
	}

	public void setP5(int p5) {
		this.p5 = p5;
	}
	
	public int getSecuP1() {
		return secuP1;
	}

	public void setSecuP1(int secuP1) {
		this.secuP1 = secuP1;
	}

	public int getSecuP2() {
		return secuP2;
	}

	public void setSecuP2(int secuP2) {
		this.secuP2 = secuP2;
	}

	public int getSecuP3() {
		return secuP3;
	}

	public void setSecuP3(int secuP3) {
		this.secuP3 = secuP3;
	}

	public int getSecuP4() {
		return secuP4;
	}

	public void setSecuP4(int secuP4) {
		this.secuP4 = secuP4;
	}

	public int getSecuP5() {
		return secuP5;
	}

	public void setSecuP5(int secuP5) {
		this.secuP5 = secuP5;
	}

	public RecompensesGM() {}
	
	public RecompensesGM(Age age) {
		this.age = age;
	}
	
}
