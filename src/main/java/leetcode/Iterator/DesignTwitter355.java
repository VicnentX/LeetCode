package leetcode.Iterator;

/*
Design a simplified version of Twitter where users can post tweets, follow/unfollow another user and is able to see the 10 most recent tweets in the user's news feed. Your design should support the following methods:

postTweet(userId, tweetId): Compose a new tweet.
getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
follow(followerId, followeeId): Follower follows a followee.
unfollow(followerId, followeeId): Follower unfollows a followee.
Example:

Twitter twitter = new Twitter();

// User 1 posts a new tweet (id = 5).
twitter.postTweet(1, 5);

// User 1's news feed should return a list with 1 tweet id -> [5].
twitter.getNewsFeed(1);

// User 1 follows user 2.
twitter.follow(1, 2);

// User 2 posts a new tweet (id = 6).
twitter.postTweet(2, 6);

// User 1's news feed should return a list with 2 tweet ids -> [6, 5].
// Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
twitter.getNewsFeed(1);

// User 1 unfollows user 2.
twitter.unfollow(1, 2);

// User 1's news feed should return a list with 1 tweet id -> [5],
// since user 1 is no longer following user 2.
twitter.getNewsFeed(1);
Seen this question in a real interview before?

 */

import java.util.*;

public class DesignTwitter355 {
    Map<Integer , Set<Integer>> map; // followeeId , list of followerId
    List<int[]> list;

    /** Initialize your data structure here. */
    public DesignTwitter355() {
        map = new HashMap<>();
        list = new ArrayList<>();
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        list.add(new int[] {userId , tweetId});
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        if (!map.containsKey(userId)) {
            map.put(userId , new HashSet<>());
            map.get(userId).add(userId);
        }
        int cnt = 0;
        List<Integer> ret = new ArrayList<>();
        for (int i = list.size() - 1 ; i >= 0 ; --i) {
            int[] cur = list.get(i);
            if (!map.containsKey(cur[0])) {
                map.put(cur[0] , new HashSet<>());
                map.get(cur[0]).add(cur[0]);
            }
            if (map.get(cur[0]).contains(userId)) {
                ret.add(cur[1]);
                if (++cnt == 10) {
                    return ret;
                }
            }
        }
        return ret;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (followeeId == followerId) return;
        if (!map.containsKey(followeeId)) {
            map.put(followeeId , new HashSet<>());
            map.get(followeeId).add(followeeId);
        }
        map.get(followeeId).add(followerId);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (followeeId == followerId) return;
        if (!map.containsKey(followeeId)) {
            map.put(followeeId , new HashSet<>());
            map.get(followeeId).add(followeeId);
        }
        map.get(followeeId).remove(followerId);
    }

    public static void main (String[] args) {
        DesignTwitter355 dt = new DesignTwitter355();
        dt.postTweet(1,5);
        dt.getNewsFeed(1);
        dt.follow(1,2);
        dt.postTweet(2,6);
        dt.getNewsFeed(1);
        dt.unfollow(1,2);
        dt.getNewsFeed(1);
    }
}
