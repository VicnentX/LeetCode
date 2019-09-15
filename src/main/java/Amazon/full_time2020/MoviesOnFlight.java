package Amazon.full_time2020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 8. Movies on Flight
 * You are on a flight and wanna watch two movies during this flight. You are given int[] movie_duration which includes all the movie durations. You are also given the duration of the flight which is d in minutes. Now, you need to pick two movies and the total duration of the two movies is less than or equal to (d - 30min). Find the pair of movies with the longest total duration. If multiple found, return the pair with the longest movie.
 * e.g. Input movie_duration: [90, 85, 75, 60, 120, 150, 125] d: 250
 * Output [90, 125] 90min + 125min = 215 is the maximum number within 220 (250min - 30min)
 * leetcode. 1099
 */



public class MoviesOnFlight {
    public List<Integer> findMoviePair(List<Integer> movieList, int duration) {
        final int TIME_LIMIT = duration - 30;
        List<Integer> ret = new ArrayList<>();
        int maxDuration = 0;
        int longestMovie = 0;
        int i = 0, j = movieList.size() - 1;

        Collections.sort(movieList);

        while (j > i) {
            int movie1 = movieList.get(i);
            int movie2 = movieList.get(j);
            int durationSum = movie1 + movie2;

            if (durationSum <= TIME_LIMIT) {
                if (durationSum > maxDuration) {
                    ret = Arrays.asList(movie1, movie2);
                    maxDuration = durationSum;
                    longestMovie = Math.max(movie1, movie2);
                } else if (durationSum == maxDuration
                        && Math.max(movie1, movie2) > longestMovie) {
                    ret = Arrays.asList(movie1, movie2);
                    longestMovie = Math.max(movie1, movie2);
                }
                ++i;
            } else {
                --j;
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        MoviesOnFlight moviesOnFlight = new MoviesOnFlight();
        List<Integer> ret = moviesOnFlight.findMoviePair(Arrays.asList(90, 85, 75, 60, 120, 150, 125), 250);
        for (int i: ret) {
            System.out.println(i);
        }
    }
}
