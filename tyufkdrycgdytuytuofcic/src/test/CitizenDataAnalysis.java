package test;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class CitizenDataAnalysis {

    static class Citizen {
        String code;
        String dateOfBirth;
        String fatherCode;
        String motherCode;
        char isAlive;
        String regionCode;

        Citizen(String[] data) {
            this.code = data[0];
            this.dateOfBirth = data[1];
            this.fatherCode = data[2];
            this.motherCode = data[3];
            this.isAlive = data[4].charAt(0);
            this.regionCode = data[5];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Citizen> database = new ArrayList<>();
        Map<String, Citizen> citizenMap = new HashMap<>();
        Map<String, List<String>> parentMap = new HashMap<>();
        List<String> queries = new ArrayList<>();
        boolean processingQueries = false;

        String line;
        while ((line = br.readLine()) != null) {
            if (line.equals("*")) {
                processingQueries = true;
                continue;
            } else if (line.equals("***")) {
                break;
            }

            if (!processingQueries) {
                String[] parts = line.split(" ");
                Citizen citizen = new Citizen(parts);
                database.add(citizen);
                citizenMap.put(citizen.code, citizen);
                parentMap.putIfAbsent(citizen.fatherCode, new ArrayList<>());
                parentMap.get(citizen.fatherCode).add(citizen.code);
                parentMap.putIfAbsent(citizen.motherCode, new ArrayList<>());
                parentMap.get(citizen.motherCode).add(citizen.code);
            } else {
                queries.add(line);
            }
        }

        List<Citizen> sortedCitizens = new ArrayList<>(database);
        sortedCitizens.sort(Comparator.comparing(c -> c.dateOfBirth));
        
        NavigableMap<String, Integer> cumulativeCount = new TreeMap<>();
        int count = 0;
        for (Citizen citizen : sortedCitizens) {
            count++;
            cumulativeCount.put(citizen.dateOfBirth, count);
        }
        Map<String, Long> birthDateCount = database.stream()
                .collect(Collectors.groupingBy(c -> c.dateOfBirth, Collectors.counting()));

        for (String query : queries) {
            String[] parts = query.split(" ");
            switch (parts[0]) {
                case "NUMBER_PEOPLE":
                    System.out.println(database.size());
                    break;

                case "NUMBER_PEOPLE_BORN_AT":
                    String date = parts[1];
                    System.out.println(birthDateCount.getOrDefault(date, 0L));
                    break;

                case "NUMBER_PEOPLE_BORN_BETWEEN":
                    String fromDate = parts[1];
                    String toDate = parts[2];
                    
                    // Get the cumulative count up to toDate
                    int toCount = cumulativeCount.getOrDefault(toDate, cumulativeCount.floorEntry(toDate) != null 
                        ? cumulativeCount.floorEntry(toDate).getValue() : 0);
                    
                    // Get the cumulative count up to fromDate (exclude fromDate itself if not included)
                    int fromCount = cumulativeCount.getOrDefault(fromDate, cumulativeCount.floorEntry(fromDate) != null 
                        ? cumulativeCount.floorEntry(fromDate).getValue() : 0);
                    
                    // The difference will give the number of people born between the two dates
                    System.out.println(toCount - fromCount);
                    break;


                case "MOST_ALIVE_ANCESTOR":
                    String code = parts[1];
                    System.out.println(findMostAliveAncestor(code, citizenMap));
                    break;

                case "MAX_UNRELATED_PEOPLE":
                    System.out.println(findMaxUnrelatedPeople(database, parentMap));
                    break;

                default:
                    break;
            }
        }
    }

    private static int findMostAliveAncestor(String code, Map<String, Citizen> citizenMap) {
        int generation = 0;
        while (true) {
            Citizen citizen = citizenMap.get(code);
            if (citizen == null || citizen.fatherCode.equals("0000000") && citizen.motherCode.equals("0000000")) {
                break;
            }
            code = citizen.fatherCode.equals("0000000") ? citizen.motherCode : citizen.fatherCode;
            generation++;
        }
        return generation;
    }

    private static int findMaxUnrelatedPeople(List<Citizen> database, Map<String, List<String>> parentMap) {
        Set<String> independentSet = new HashSet<>();
        Set<String> visited = new HashSet<>();

        for (Citizen citizen : database) {
            // If this citizen or their relatives are already visited, skip
            if (visited.contains(citizen.code)) continue;

            // Add the citizen to the independent set
            independentSet.add(citizen.code);
            visited.add(citizen.code);

            // Mark parents and children as visited
            if (!citizen.fatherCode.equals("0000000")) visited.add(citizen.fatherCode);
            if (!citizen.motherCode.equals("0000000")) visited.add(citizen.motherCode);

            List<String> children = parentMap.getOrDefault(citizen.code, new ArrayList<>());
            visited.addAll(children);
        }

        return independentSet.size();
    }


    private static void dfsMarkFamily(String code, Map<String, List<String>> parentMap, Set<String> visited) {
        if (code.equals("0000000") || visited.contains(code)) return;
        visited.add(code);

        // Mark children as visited
        List<String> children = parentMap.getOrDefault(code, new ArrayList<>());
        for (String child : children) {
            dfsMarkFamily(child, parentMap, visited);
        }

        // Find parents and mark them
        for (Map.Entry<String, List<String>> entry : parentMap.entrySet()) {
            if (entry.getValue().contains(code)) {
                dfsMarkFamily(entry.getKey(), parentMap, visited);
            }
        }
    }

}

