//You are given two integer arrays, skill and mana, of length n and m, 
//respectively. 
//
// In a laboratory, n wizards must brew m potions in order. Each potion has a 
//mana capacity mana[j] and must pass through all the wizards sequentially to be 
//brewed properly. The time taken by the iᵗʰ wizard on the jᵗʰ potion is timeij = 
//skill[i] * mana[j]. 
//
// Since the brewing process is delicate, a potion must be passed to the next 
//wizard immediately after the current wizard completes their work. This means the 
//timing must be synchronized so that each wizard begins working on a potion 
//exactly when it arrives. 
//
// Return the minimum amount of time required for the potions to be brewed 
//properly. 
//
// 
// Example 1: 
//
// 
// Input: skill = [1,5,2,4], mana = [5,1,4,2] 
// 
//
// Output: 110 
//
// Explanation: 
//
// 
// 
// 
// Potion Number 
// Start time 
// Wizard 0 done by 
// Wizard 1 done by 
// Wizard 2 done by 
// Wizard 3 done by 
// 
// 
// 0 
// 0 
// 5 
// 30 
// 40 
// 60 
// 
// 
// 1 
// 52 
// 53 
// 58 
// 60 
// 64 
// 
// 
// 2 
// 54 
// 58 
// 78 
// 86 
// 102 
// 
// 
// 3 
// 86 
// 88 
// 98 
// 102 
// 110 
// 
// 
// 
//
// As an example for why wizard 0 cannot start working on the 1ˢᵗ potion before 
//time t = 52, consider the case where the wizards started preparing the 1ˢᵗ 
//potion at time t = 50. At time t = 58, wizard 2 is done with the 1ˢᵗ potion, but 
//wizard 3 will still be working on the 0ᵗʰ potion till time t = 60. 
//
// Example 2: 
//
// 
// Input: skill = [1,1,1], mana = [1,1,1] 
// 
//
// Output: 5 
//
// Explanation: 
//
// 
// Preparation of the 0ᵗʰ potion begins at time t = 0, and is completed by time 
//t = 3. 
// Preparation of the 1ˢᵗ potion begins at time t = 1, and is completed by time 
//t = 4. 
// Preparation of the 2ⁿᵈ potion begins at time t = 2, and is completed by time 
//t = 5. 
// 
//
// Example 3: 
//
// 
// Input: skill = [1,2,3,4], mana = [1,2] 
// 
//
// Output: 21 
//
// 
// Constraints: 
//
// 
// n == skill.length 
// m == mana.length 
// 1 <= n, m <= 5000 
// 1 <= mana[i], skill[i] <= 5000 
// 
//
// Related Topics Array Simulation Prefix Sum 👍 56 👎 17

package com.kshitijpatil.leetcode.editor.en;

public class FindTheMinimumAmountOfTimeToBrewPotions {
    public static void main(String[] args) {
        Solution solution = new FindTheMinimumAmountOfTimeToBrewPotions().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public long minTime(int[] skill, int[] mana) {
        int n = skill.length, m = mana.length;
        long[] done = new long[n+1];
        for (int potion : mana) {
            for (int i=0; i<n; i++) {
                done[i+1] = Math.max(done[i+1], done[i]) + (long) potion * skill[i];
            }
            for (int i=n-1; i>0; i--) {
                done[i] = done[i+1] - (long) potion * skill[i];
            }
        }
        return done[n];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
/*

psn1=8
psn=12

dtLast=60
dt0=5
manaNext=1

st0=max(dt0, dtLast-(manaNext*psn1))


st0=max(5,52)
dt0=st0+skill[0]*manaNext
dt0=53
dtLast=dtLast+(psn*manaNext)


        0       1       2       3       4
                1       5       2       4
5       0       5      30      40      60
1      52      53      58      60      64
4      54      58      78      86      102
2


-2
 */