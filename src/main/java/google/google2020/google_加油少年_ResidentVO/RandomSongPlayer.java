package google.google2020.google_加油少年_ResidentVO;

/*
Design a class that can play random songs from a playlist following these rules.
You can assume that your API is initialized with an array of songs, and some integer K.
Each time it's time to play a new song, a function on your API will be called to return a valid song to play.
e.g.
k = 2
from [A, B, C, D] play B
from [A, B, C, D]  play C
from [A, B, C, D]  play D
from [A, B, C, D]  play B
 */

import java.util.List;
import java.util.Random;

public class RandomSongPlayer {
    private int[] songs;
    int index = 0;
    Random random = new Random();

    public RandomSongPlayer(int[] songs) {
        this.songs = songs;
    }

    public int playARandomSong() {
        if (index == songs.length) {
            index = 0;
        }

        int randomIndex = index + random.nextInt(songs.length - index);
        swap(songs, index, randomIndex);
        return songs[index++];
    }

    private void swap(int[] songs, int i, int j) {
        int temp = songs[i];
        songs[i] = songs[j];
        songs[j] = temp;
    }

    public static void main(String[] args) {
        RandomSongPlayer randomSongPlayer = new RandomSongPlayer(new int[] {2,4,6,8});
        for (int i = 0; i < 8; ++i) {
            System.out.println(randomSongPlayer.playARandomSong());
        }

    }

}
