package org.jrw
/**
 * Solution to look-and-say problem 7.8
 */
class LookSay {
  static int atIndex(int n) {
    if (n < 0) {
      throw new IllegalArgumentException("argument must be >= 0; was ${n}")
    }

    if (n == 0) {
      return 1
    }

    StringBuilder result = new StringBuilder()
    char[] prevChars = String.valueOf(atIndex(n - 1)).toCharArray()
    int currentCount = 1
    char lastSeenChar = prevChars[0]
    for (int i = 1; i < prevChars.length; i++) {
      if (prevChars[i] == lastSeenChar) {
        currentCount++
      } else {
        appendCharCount(result, lastSeenChar, currentCount)
        lastSeenChar = prevChars[i]
        currentCount = 1
      }
    }
    appendCharCount(result, lastSeenChar, currentCount)
    Integer.valueOf(result.toString())
  }

  private static void appendCharCount(StringBuilder str, char ch, int count) {
    str.append(count).append(ch)
  }
}