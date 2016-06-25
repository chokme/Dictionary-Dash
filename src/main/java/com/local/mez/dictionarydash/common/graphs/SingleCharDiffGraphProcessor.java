package com.local.mez.dictionarydash.common.graphs;

import org.apache.commons.lang.StringUtils;

public class SingleCharDiffGraphProcessor implements GraphProcessor<String> {

    public boolean linkVertices(String s1, String s2) {
        if(StringUtils.isBlank(s1) || StringUtils.isBlank(s2) || s1.length() != s2.length()) {
            return false;
        }
        String lowerCaseS1 = s1.toLowerCase();
        String lowerCaseS2 = s2.toLowerCase();
        char[] charArray1 = lowerCaseS1.toCharArray();
        char[] charArray2 = lowerCaseS2.toCharArray();
        int numberofDifferentChars = 0;
        for (int i=0; i < charArray1.length; i++ ){
            if(charArray1[i] != charArray2[i]) {
                numberofDifferentChars += 1;
                if(numberofDifferentChars > 1) {
                    return false;
                }
            }
        }

        return true;
    }

}
