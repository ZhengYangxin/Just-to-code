package com.zyx.leetcode.question0355_twitter;

import java.util.*;

class Twitter {

    private HashMap<Integer, User> userMap = new HashMap<>();
    /** Initialize your data structure here. */
    public Twitter() {

    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if (!userMap.containsKey(userId)) {
            userMap.put(userId, new User(userId));
        }
        User user = userMap.get(userId);
        user.post(tweetId);

    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {

        List<Integer> res = new ArrayList<>();
        if (!userMap.containsKey(userId)) return res;

        Set<Integer> users = userMap.get(userId).followed;

        PriorityQueue<Tweet> pq = new PriorityQueue<>(users.size(), new Comparator<Tweet>() {
            @Override
            public int compare(Tweet o1, Tweet o2) {
                return o2.time - o1.time;
            }
        });

        for (Integer user : users) {
            Tweet twt = userMap.get(user).head;
            if (twt == null) continue;

            pq.add(twt);
        }

        while (!pq.isEmpty()) {
            if (res.size() == 10) {
                break;
            }

            Tweet twt = pq.poll();
            res.add(twt.id);

            if (twt.next != null) {
                pq.add(twt.next);
            }
        }
        return res;

    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (!userMap.containsKey(followerId)) {
            userMap.put(followerId, new User(followerId));
        }

        if (!userMap.containsKey(followeeId)) {
            userMap.put(followeeId, new User(followeeId));
        }
        userMap.get(followerId).follow(followeeId);

    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (userMap.containsKey(followerId)) {
            userMap.get(followerId).unfollow(followeeId);
        }
    }
}

class Tweet {
    int id;
    int time;
    Tweet next;

    public Tweet(int id, int time, Tweet next) {
        this.id = id;
        this.time = time;
        this.next = next;
    }
}

class User {
    int id;
    Set<Integer> followed;
    Tweet head;

    static int timestamp = 0;

    public User(int id) {
        this.id = id;
        followed = new HashSet<>();
        head = null;
        follow(id);
    }

    public void follow(int userId) {
        followed.add(userId);
    }

    public void unfollow(int userId) {
        if (this.id != userId) {
            followed.remove(userId);
        }
    }

    public void post(int tweetId) {
        Tweet tweet = new Tweet(tweetId, timestamp, null);
        timestamp++;
        tweet.next = head;
        head = tweet;
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */