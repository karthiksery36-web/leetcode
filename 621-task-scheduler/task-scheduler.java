class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];

        for (char t : tasks) {
            freq[t - 'A']++;
        }

        int maxFreq = 0, countMax = 0;

        for (int f : freq) {
            if (f > maxFreq) {
                maxFreq = f;
                countMax = 1;
            } else if (f == maxFreq) {
                countMax++;
            }
        }

        return Math.max(tasks.length, (maxFreq - 1) * (n + 1) + countMax);
    }
}