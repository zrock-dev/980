package com.fake_orgasm.users_management.libs.btree;

import java.util.ArrayList;
import java.util.List;

public class SyllableSeparator {
    /**
     * Split a word in syllabus.
     *
     * @param word word to split.
     * @return syllabus.
     */
    public static List<String> split(String word) {
        char[] chars = word.toCharArray();
        List<String> syllabus = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            if (!isVocal(chars[i]) || i == 0) {
                String syllable = cutSyllable(chars, i);
                i += syllable.length() - 1;
                syllabus.add(syllable);
            }
        }
        return syllabus;
    }

    /**
     * Cut a syllable from a word.
     *
     * @param word word to cut.
     * @param index index of the word.
     * @return syllable.
     */
    public static String cutSyllable(char[] word, int index) {
        StringBuilder syllable = new StringBuilder();
        syllable.append(word[index]);
        boolean syllableFinished = false;
        boolean vocalFound = isVocal(word[index]);
        index++;
        while (!syllableFinished) {
            if (word.length <= index) {
                syllableFinished = true;
            } else if (isVocal(word[index])) {
                syllable.append(word[index]);
                vocalFound = true;
                index++;
            } else {
                if (word.length > index + 1) {
                    if (isVocal(word[index + 1]) && vocalFound) {
                        syllableFinished = true;
                    } else {
                        syllable.append(word[index]);
                        index++;
                    }
                } else {
                    syllable.append(word[index]);
                    syllableFinished = true;
                }
            }
        }
        return syllable.toString();
    }

    /**
     * Verify if a character is a vocal.
     *
     * @param character character to verify.
     * @return true if the character is a vocal, false otherwise.
     */
    private static boolean isVocal(char character) {
        return character == 'a' || character == 'e' || character == 'i' || character == 'o' || character == 'u';
    }
}
