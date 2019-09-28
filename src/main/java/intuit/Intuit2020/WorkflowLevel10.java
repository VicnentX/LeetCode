package intuit.Intuit2020;

import java.util.*;

/**
 * use Map hasPreAction to store this action has how many pre actions
 *  use map relation to store this action has specific post actions
 *  use set firstCandidate to store possible first actions
 *
 */

/*
Introduction
We have some input that describes the steps of a workflow. The input consists of pairs of steps, each one indicating that one step must be completed before another. For example, in sample input 1, “clean” must occur before “build” and “build” must occur before “link”. The input can occur in any order.
We want to write a function to separate a given workflow’s steps into multiple stages in such a way that all the steps in each individual stage can run at the same time. The function should return a list of lists, in which each list represents one stage. Each step should run in the earliest possible stage.
Sample input 1:
precursor_steps = [
  ["clean", "build"],
  ["metadata", "binary"],
  ["build", "link"],
  ["link", "binary"],
  ["clean", "metadata"],
  ["build", "resources"]
Sample output 1:
[
  ["clean"],
  ["build", "metadata"],
  ["resources", "link"],
  ["binary"]
Sample input 2:
precursor_steps = [
  ["boil", "serve"],
  ["chop", "boil"],
  ["stir", "boil"],
  ["set table", "serve"]
Sample output 2:
[
  ["chop", "stir", "set table"],
  ["boil"],
  ["serve"]
Clarifications
* The lists in your output should be in precedence order.
* The steps within a given list in your output can be in any order (i.e. [“chop”, “stir”] and [“stir”, “chop”] are equivalent for our purposes).
* Assume all inputs are always sanitized (input can be empty but will not be null, no null/empty/otherwise invalid steps, no circular references, etc.).

 */
public class WorkflowLevel10 {
    public List<List<String>> getOrder(String[][] pairs) {
        Map<String, Integer> hasPreAction = new HashMap<>(); // indegree
        Map<String, Set<String>> relations = new HashMap<>(); // relations
        Set<String> curLevelCandidate = new HashSet<>();
        List<List<String>> workFlowResult = new ArrayList<>();

        //fill data
        for (String[] pair: pairs) {
            if (!relations.containsKey(pair[0])) {
                relations.put(pair[0], new HashSet<>());
            }
            relations.get(pair[0]).add(pair[1]);
            hasPreAction.put(pair[1], hasPreAction.getOrDefault(pair[1], 0) + 1 );
//            if (!hasPreAction.containsKey(pair[0])) {
//                firstCandidate.add(pair[0]);
//            }
            if (!hasPreAction.containsKey(pair[0])) {
                hasPreAction.put(pair[0], 0);
            }
//            firstCandidate.remove(pair[1]);
        }

        //get first candidate
        for (String key : hasPreAction.keySet()) {
            if (hasPreAction.get(key) == 0) {
                curLevelCandidate.add(key);
            }
        }

        //search
        while (!curLevelCandidate.isEmpty()) {
            workFlowResult.add(new ArrayList<>(curLevelCandidate));
            Set<String> nextLevelCandidate = new HashSet<>();
            for (String curAction: curLevelCandidate) {
                if (relations.containsKey(curAction)) {
                    for ( String nextAction: relations.get(curAction)) {
                        hasPreAction.put(nextAction, hasPreAction.get(nextAction) - 1);
                        if (hasPreAction.get(nextAction) == 0) {
                            nextLevelCandidate.add(nextAction);
                        }
                    }
                }
            }
            curLevelCandidate = nextLevelCandidate;
        }

        return workFlowResult;

    }

    public static void main(String[] args) {
        WorkflowLevel10 workflowLevel = new WorkflowLevel10();
        List<List<String>> ret = workflowLevel.getOrder(new String[][] {
                {"boil", "serve"},
                {"chop", "boil"},
                {"stir", "boil"},
                {"set table", "serve"}
        });

        for (List<String> actionsInThisLevel: ret) {
            for (String action: actionsInThisLevel) {
                System.out.print(" " + action);
            }
            System.out.println();
        }
    }
}
