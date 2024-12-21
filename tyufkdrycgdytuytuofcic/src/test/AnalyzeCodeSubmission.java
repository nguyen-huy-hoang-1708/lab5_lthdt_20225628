package test;

import java.util.*;
import java.io.*;

@SuppressWarnings({"unchecked", "deprecation"})
public class AnalyzeCodeSubmission { 
	private static int timeToSeconds(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        int seconds = Integer.parseInt(parts[2]);
        return hours * 3600 + minutes * 60 + seconds;
    }
	
  public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      List<String[]> submissions = new ArrayList<>();
      Map<String, Integer> userTotalPoints = new HashMap<>();
      Map<String, Integer> userErrorCounts = new HashMap<>();
      TreeMap<Integer, Integer> timeOrderedSubmissions = new TreeMap<>();
      int totalSubmissions = 0;
      int errorSubmissions = 0; 

      // Read submissions
      String line;
      while (!(line = br.readLine()).equals("#")) {
          String[] parts = line.split(" ");
          submissions.add(parts);

          int timeInSeconds = timeToSeconds(parts[2]);
          timeOrderedSubmissions.put(timeInSeconds, timeOrderedSubmissions.getOrDefault(timeInSeconds, 0) + 1);
          
          // Update statistics
          totalSubmissions++;
          if (parts[3].equals("ERR")) {
        	  errorSubmissions++;
              userErrorCounts.put(parts[0], userErrorCounts.getOrDefault(parts[0], 0) + 1);
          }
      }

      TreeMap<Integer, Integer> prefixSum = new TreeMap<>();
      int runningSum = 0;
      for (Map.Entry<Integer, Integer> entry : timeOrderedSubmissions.entrySet()) {
          runningSum += entry.getValue();
          prefixSum.put(entry.getKey(), runningSum);
      }
      
      // Calculate total points per user
      Map<String, Map<String, Integer>> userProblemMaxPoints = new HashMap<>();
      for (String[] submission : submissions) {
          String userId = submission[0];
          String problemId = submission[1];
          int points = Integer.parseInt(submission[4]);

          userProblemMaxPoints.putIfAbsent(userId, new HashMap<>());
          Map<String, Integer> problemPoints = userProblemMaxPoints.get(userId);

          problemPoints.put(problemId, Math.max(problemPoints.getOrDefault(problemId, 0), points));
      }

      for (Map.Entry<String, Map<String, Integer>> entry : userProblemMaxPoints.entrySet()) {
          int totalPoints = entry.getValue().values().stream().mapToInt(Integer::intValue).sum();
          userTotalPoints.put(entry.getKey(), totalPoints);
      }

      // Process queries
      while (!(line = br.readLine()).equals("#")) {
          String[] query = line.split(" ");
          switch (query[0]) {
              case "?total_number_submissions":
                  System.out.println(totalSubmissions);
                  break;
              case "?number_error_submision":
                  System.out.println(errorSubmissions);
                  break;
              case "?number_error_submision_of_user":
                  String userId = query[1];
                  System.out.println(userErrorCounts.getOrDefault(userId, 0));
                  break;
              case "?total_point_of_user":
                  userId = query[1];
                  System.out.println(userTotalPoints.getOrDefault(userId, 0));
                  break;
              case "?number_submission_period":
            	  int fromTime = timeToSeconds(query[1]);
                  int toTime = timeToSeconds(query[2]);
                  int countInPeriod = 0;
                  
                  Integer sumToTime = prefixSum.floorEntry(toTime) != null ? prefixSum.floorEntry(toTime).getValue() : 0;
                  Integer sumFromTime = prefixSum.floorEntry(fromTime - 1) != null ? prefixSum.floorEntry(fromTime - 1).getValue() : 0;

                  countInPeriod = sumToTime - sumFromTime;
                  System.out.println(countInPeriod);
                  break;
              default:
                  System.out.println("Unknown query: " + query[0]);
          }
      }
  }
}