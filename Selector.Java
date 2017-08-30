import java.util.Arrays;

/**
* Defines a library of selection methods
* on arrays of ints.
*
* @author   Blaze Cruce (bac0051@auburn.edu)
* @author   Dean Hendrix (dh@auburn.edu)
* @version  2017-08-26
*
*/
public final class Selector {

   /**
    * Can't instantiate this class.
    *
    * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
    *
    */
   private Selector() { }
   
   
   /**
    * Returns lowest value in array or throws exception if illegial arguments.
    * @param a integer array passed through method
    * @return lowest value
    */
   public static int min(int[] a) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      int lowest = a[0];
      for (int i = 0; i < a.length; i++) {
         if (a[i] < lowest) {
            lowest = a[i];
         }
      }
      return lowest;
   }


   /**
    * Returns highest value in array or throws exception if illegial arguments.
    * @param a integer array passed through method
    * @return highest value
    */
   public static int max(int[] a) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      int highest = a[0];
      for (int i = 0; i < a.length; i++) {
         if (a[i] > highest) {
            highest = a[i];
         }
      }
      return highest;
   }


   /**
    * Returns k'th minimum value in a or throws exception if illegal arguments.
    * @param a integer array passed through method
    * @param k a magic number
    * @return the k'th lowest value in a
    */
   public static int kmin(int[] a, int k) {
      if (k < 1) {
         throw new IllegalArgumentException();
      }
      int newMinimum = Selector.min(a);
      for (int i = 1; i < k; i++) {
         int[] newRange = Selector.range(a, newMinimum + 1, Selector.max(a));
         newMinimum = Selector.min(newRange);
      }
      return newMinimum;
   }


   /**
    * Returns k'th maximum value in a or throws exception if illegal arguments.
    * @param a integer array passed through method
    * @param k a magic number
    * @return the k'th highest value in a
    */
   public static int kmax(int[] a, int k) {
      if (k < 1) {
         throw new IllegalArgumentException();
      }
      int newMaximum = Selector.max(a);
      for (int i = 1; i < k; i++) {
         int[] newRange = Selector.range(a, Selector.min(a), newMaximum - 1);
         newMaximum = Selector.max(newRange);
      }
      return newMaximum;
   }


   /**
    * Returns an array containing all values within the specified range
    * or throws exception if illegal arguments.
    * ----A bit messy because I couldn't think of a more efficient solution----
    * @param a integer array passed through method
    * @param low lowest possible value in range
    * @param high highest possible value in range
    * @return values in range
    */
   public static int[] range(int[] a, int low, int high) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      int valuesCount = 0;
      for (int i = 0; i < a.length; i++) {
         if (a[i] >= low && a[i] <= high) {
            valuesCount++;
         }
      }
      int[] values = new int[valuesCount];
      int j = 0;
      for (int i = 0; i < a.length; i++) {
         if (a[i] >= low && a[i] <= high) {
            values[j] = a[i];
            j++;
         }
      }
      return values;
   }


   /**
    * Returns smallest value in a >= key or throws exception if illegal
    * arguments or no solution.
    * @param a integer array passed through method
    * @param key lowest possible value for ceiling
    * @return minimum within specified range
    */
   public static int ceiling(int[] a, int key) {
      int[] satisfies = Selector.range(a, key, Selector.max(a));
      return Selector.min(satisfies);
   }


   /**
    * Returns largest value in a <= key or throws exception if illegal
    * arguments or no solution.
    * @param a integer array passed through method
    * @param key highest possible value for floor
    * @return maximum within specified range
    */
   public static int floor(int[] a, int key) {
      int[] satisfies = Selector.range(a, Selector.min(a), key);
      return Selector.max(satisfies);
   }

}
