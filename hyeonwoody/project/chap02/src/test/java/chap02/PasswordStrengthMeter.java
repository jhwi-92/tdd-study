package test.java.chap02;

public class PasswordStrengthMeter {
    public PasswordStrength meter (String s){
        if (s == null || s.isEmpty()) return PasswordStrength.INVALID;

        int metCnt = getMetCriteriaCounts(s);

        if (metCnt == 1) return PasswordStrength.WEAK;
        if (metCnt == 2) return PasswordStrength.NORMAL;
        if (metCnt == 3) return PasswordStrength.STRONG;

        return PasswordStrength.WEAK;
    }

    private int getMetCriteriaCounts(String s){
        int metCnt = 0;
        if (s.length() >= 8) metCnt++;
        if (meetsContainingNumberCriteria(s)) metCnt++;
        if (meetsContainingUppercaseCriteria(s)) metCnt++;
        return metCnt;
    }

    private boolean meetsContainingUppercaseCriteria(String s){
        for (char ch : s.toCharArray()){
            if (Character.isUpperCase(ch))
                return true;
        }
        return false;
    }


    private boolean baboMeetsContainingUppercaseCriteria(String s){
        String sUpper = s.toUpperCase();
        for (int i = 0; i < s.length(); ++i){
            if (s.charAt(i) == sUpper.charAt(i))
                return true;
        }
        return false;
    }

    private boolean meetsContainingNumberCriteria(String s){
        for (char ch : s.toCharArray()){
            if (ch >= '0' && ch <= '9')
                return true;
        }
        return false;
    }
}
