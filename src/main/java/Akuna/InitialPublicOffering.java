package Akuna;

import java.util.*;

/**
 * input is int[][] = [bid[0],bid[1]...]
 * bid[i] = [user id, number of shares, bidding price, timestamp]
 * output a list containing all the user's id who has no share.
 */

public class InitialPublicOffering {
    public List<Integer> getUnallottedUsers(int[][] bids, int totalShares) {
        Set<Integer> userHasShares = new HashSet<>();
        List<Integer> unallottedUsersList = new ArrayList<>();
        Arrays.sort(bids, (a,b) -> a[2] == b[2] ? a[3] - b[3] : b[2] - a[2]);
        int i = 0;

        OUT:
        while (i < bids.length && totalShares > 0) {
            int j = i;
            int totalBidsSharesForSamePrice = 0;
            Set<Integer> idListforSamePrice = new HashSet<>();
            while (j < bids.length && bids[j][2] == bids[i][2]) {
                totalBidsSharesForSamePrice += bids[j][1];
                idListforSamePrice.add(bids[j][0]);
                ++j;
            }
            if (totalShares >= totalBidsSharesForSamePrice) {
                totalShares -= totalBidsSharesForSamePrice;
                userHasShares.addAll(idListforSamePrice);
                i = j;
            } else {
                if (totalShares >= (j - i)) {
                    userHasShares.addAll(idListforSamePrice);
                    break OUT;
                } else {
                    while(totalShares > 0) {
                        userHasShares.add(bids[i][0]);
                        i++;
                        totalShares--;
                    }
                }
            }
        }

        for (int id = 1; id <= bids.length; ++id) {
            if (!userHasShares.contains(id)) unallottedUsersList.add(id);
        }

        return unallottedUsersList;
    }

    public static void main(String[] args) {
        InitialPublicOffering initialPublicOffering = new InitialPublicOffering();
        for (int i: initialPublicOffering.getUnallottedUsers(new int[][]{{1,2,5,0}, {2,1,4,2}, {3,5,4,6}}, 3)) {
            System.out.println(i);
        }
    }
}
