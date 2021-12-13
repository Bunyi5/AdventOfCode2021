package advent.of.code.tasks.task12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import advent.of.code.helper.Helper;

public class CaveSystem {

    private final Map<Cave, List<Cave>> system = new HashMap<>();

    private static final Cave START_CAVE = new Cave("start");
    private static final Cave END_CAVE = new Cave("end");

    public CaveSystem(List<String> cavesList) {
        List<List<String>> edgePairs = cavesList.stream()
            .map(edges -> edges.split(Helper.DASH))
            .map(Arrays::asList)
            .collect(Collectors.toList());

        List<String> caveNames = edgePairs.stream()
            .flatMap(Collection::stream)
            .distinct()
            .collect(Collectors.toList());

        caveNames.forEach(this::addCave);
        edgePairs.forEach(edgePair -> addEdge(edgePair.get(0), edgePair.get(1)));
    }

    public Map<Cave, List<Cave>> getSystem() {
        return system;
    }

    private void addCave(String name) {
        system.put(new Cave(name), new ArrayList<>());
    }

    private void addEdge(String name1, String name2) {
        Cave cave1 = new Cave(name1);
        Cave cave2 = new Cave(name2);
        system.get(cave1).add(cave2);
        system.get(cave2).add(cave1);
    }

    public int countValidPath() {
        return countValidPathRecursively(new HashSet<>(), START_CAVE);
    }

    private int countValidPathRecursively(Set<Cave> previous, Cave current) {
        int count = 0;
        previous.add(current);
        List<Cave> edges = system.get(current);

        for (Cave edge : edges) {
            if (edge.equals(END_CAVE)) {
                count++;
            } else if (!previous.contains(edge) || edge.isBig()) {
                count += (countValidPathRecursively(previous, edge));
            }
        }

        previous.remove(current);

        return count;
    }

    public int countValidPathWithSingleSmallCaveTwice() {
        return countValidPathWithSingleSmallCaveTwiceRecursively(new ArrayList<>(), START_CAVE);
    }

    private int countValidPathWithSingleSmallCaveTwiceRecursively(List<Cave> previous, Cave current) {
        int count = 0;
        previous.add(current);
        List<Cave> edges = system.get(current).stream()
            .filter(cave -> !cave.equals(START_CAVE))
            .collect(Collectors.toList());

        boolean singleSmallCave = previous.stream()
            .filter(cave -> !Character.isUpperCase(cave.getName().charAt(0)))
            .allMatch(cave -> Collections.frequency(previous, cave) < 2);

        for (Cave edge : edges) {
            if (edge.equals(END_CAVE)) {
                count++;
            } else if (!previous.contains(edge) || edge.isBig() || singleSmallCave) {
                count += (countValidPathWithSingleSmallCaveTwiceRecursively(previous, edge));
            }
        }

        previous.remove(previous.size() - 1);

        return count;
    }
}
