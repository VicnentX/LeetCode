package leetcode.stream;

/*
Given the array restaurants where  restaurants[i] = [idi, ratingi, veganFriendlyi, pricei, distancei]. You have to filter the restaurants using three filters.

The veganFriendly filter will be either true (meaning you should only include restaurants with veganFriendlyi set to true) or false (meaning you can include any restaurant). In addition, you have the filters maxPrice and maxDistance which are the maximum value for price and distance of restaurants you should consider respectively.

Return the array of restaurant IDs after filtering, ordered by rating from highest to lowest. For restaurants with the same rating, order them by id from highest to lowest. For simplicity veganFriendlyi and veganFriendly take value 1 when it is true, and 0 when it is false.



Example 1:

Input: restaurants = [[1,4,1,40,10],[2,8,0,50,5],[3,8,1,30,4],[4,10,0,10,3],[5,1,1,15,1]], veganFriendly = 1, maxPrice = 50, maxDistance = 10
Output: [3,1,5]
Explanation:
The restaurants are:
Restaurant 1 [id=1, rating=4, veganFriendly=1, price=40, distance=10]
Restaurant 2 [id=2, rating=8, veganFriendly=0, price=50, distance=5]
Restaurant 3 [id=3, rating=8, veganFriendly=1, price=30, distance=4]
Restaurant 4 [id=4, rating=10, veganFriendly=0, price=10, distance=3]
Restaurant 5 [id=5, rating=1, veganFriendly=1, price=15, distance=1]
After filter restaurants with veganFriendly = 1, maxPrice = 50 and maxDistance = 10 we have restaurant 3, restaurant 1 and restaurant 5 (ordered by rating from highest to lowest).
Example 2:

Input: restaurants = [[1,4,1,40,10],[2,8,0,50,5],[3,8,1,30,4],[4,10,0,10,3],[5,1,1,15,1]], veganFriendly = 0, maxPrice = 50, maxDistance = 10
Output: [4,3,2,1,5]
Explanation: The restaurants are the same as in example 1, but in this case the filter veganFriendly = 0, therefore all restaurants are considered.
Example 3:

Input: restaurants = [[1,4,1,40,10],[2,8,0,50,5],[3,8,1,30,4],[4,10,0,10,3],[5,1,1,15,1]], veganFriendly = 0, maxPrice = 30, maxDistance = 3
Output: [4,5]


Constraints:

1 <= restaurants.length <= 10^4
restaurants[i].length == 5
1 <= idi, ratingi, pricei, distancei <= 10^5
1 <= maxPrice, maxDistance <= 10^5
veganFriendlyi and veganFriendly are 0 or 1.
All idi are distinct.
 */


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Yes, you can do this by creating a DoubleStream from the array, filtering out the negatives, and converting the stream back to an array. Here is an example:
 *
 * double[] d = {8, 7, -6, 5, -4};
 * d = Arrays.stream(d).filter(x -> x > 0).toArray();
 * //d => [8, 7, 5]
 * If you want to filter a reference array that is not an Object[] you will need to use the toArray method which takes an IntFunction to get an array of the original type as the result:
 *
 * String[] a = { "s", "", "1", "", "" };
 * a = Arrays.stream(a).filter(s -> !s.isEmpty()).toArray(String[]::new);
 */


/*
List<Product> filterSortedProdycts =
    products.stream()
            .filter(p -> p.getType() == ProductType.BANANA)  // only keep BANANA
            .sorted(Comparator.comparing(Product::getPrice)) // sort by price
            .collect(Collectors.toList());

 */

/*
Stream<Product> results = products.stream()
            .filter(p -> p.getType() == ProductType.BANANA)
            .sorted((l, r) -> l.getPrice().compareTo(r.getPrice()));
 */


/*
List<Integer> doubles = new ArrayList<Integer>();
    List<Integer> triples = new ArrayList<>();

    Arrays.stream(numbers)
    .boxed()
    .forEach(n -> {
        doubles.add(n*2);
        triples.add(n*3);
        }
    );
 */

public class FilterRestaurantsbyVeganFriendlyPriceandDistance1333 {
    public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
        List<Integer> ret = new ArrayList<>();
        //[id=1, rating=4, veganFriendly=1, price=40, distance=10]
        //sort by rating and id
        Stream<int[]> stream = Arrays.stream(restaurants)
                .filter(restaurant -> restaurant[2] >= veganFriendly && restaurant[3] <= maxPrice && restaurant[4] <= maxDistance)
                .sorted((a,b) -> a[1] == b[1] ? b[0] - a[0] : b[1] - a[1]);
        stream.forEach(element -> ret.add(element[0]));
        return ret;
    }

    public static void main(String[] args) {
        FilterRestaurantsbyVeganFriendlyPriceandDistance1333 fr = new FilterRestaurantsbyVeganFriendlyPriceandDistance1333();
        List<Integer> ret = fr.filterRestaurants(new int[][] {{1,4,1,40,10},{2,8,0,50,5},{3,8,1,30,4},{4,10,0,10,3},{5,1,1,15,1}}, 1, 50, 10);
        System.out.println("size = " +  ret.size());
        for (int a: ret) {
            System.out.println(a);
        }
    }
}
