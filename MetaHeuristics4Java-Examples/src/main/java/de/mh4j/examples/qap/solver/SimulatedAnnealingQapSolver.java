package de.mh4j.examples.qap.solver;

import java.util.List;

import de.mh4j.examples.qap.model.Qap;
import de.mh4j.solver.simulatedAnnealing.AbstractSimulatedAnnealingSolver;
import de.mh4j.solver.termination.StagnationTermination;
import de.mh4j.solver.termination.StepCountTermination;

public class SimulatedAnnealingQapSolver extends AbstractSimulatedAnnealingSolver<Qap> {

    private final List<String> initialSolution;

	public SimulatedAnnealingQapSolver(List<String> initialSolution) {
		super(new QapCoolingScheme());
        this.initialSolution = initialSolution;

		addTerminationCondition(new StepCountTermination(this, 50));
		addTerminationCondition(new StagnationTermination(this, 5));
	}

    @Override
    protected Qap createInitialSolution() {
        return new Qap(initialSolution);

    }

	@Override
	protected Qap createRandomNeighbor() {
		Qap neighbor = new Qap(currentSolution);
		return createNeighborFromSwap(neighbor);
	}

	private Qap createNeighborFromSwap(Qap neighbor) {
        int neighborSolutionSize = neighbor.solution.size();

        int randomIndex1 = randomizer.nextInt(neighborSolutionSize - 1);
		int randomIndex2 = randomizer.nextInt(neighborSolutionSize - 1);
		while (randomIndex1 == randomIndex2) {
			randomIndex2 = randomizer.nextInt(neighborSolutionSize - 1);
		}

		String element1 = neighbor.solution.get(randomIndex1);
		String element2 = neighbor.solution.get(randomIndex2);

		neighbor.solution.remove(randomIndex1);
		neighbor.solution.add(randomIndex1, element2);
		neighbor.solution.remove(randomIndex2);
		neighbor.solution.add(randomIndex2, element1);

		log.trace("Created neighbor from SWAP :");
		for (int i = 0; i < neighbor.solution.size(); i++) {
			log.trace(neighbor.solution.get(i));
		}

		neighbor.calculateCosts(neighbor.solution);

		return neighbor;
	}
}
