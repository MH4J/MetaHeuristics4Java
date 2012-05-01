package de.mh4j.solver.genetic;


public class Couple {
	private final Genome parent1;
	private final Genome parent2;

	public Couple(Genome parent1, Genome parent2) {
		this.parent1 = parent1;
		this.parent2 = parent2;
	}

	public Genome getParent1() {
		return parent1;
	}

	public Genome getParent2() {
		return parent2;
	}

	@Override
	public String toString() {
		return "<" + parent1.toString() + ", " + parent2.toString() + ">";
	}
}
