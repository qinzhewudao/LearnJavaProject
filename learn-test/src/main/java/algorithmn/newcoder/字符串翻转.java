package algorithmn.newcoder;

/**
 * @author sheyang
 * @description
 * @date 2019/8/11
 * @time 上午11:29
 */
public class 字符串翻转 {

    public String LeftRotateString(String str, int n) {
        char[] c = str.toCharArray();
        if (c.length < n) {
            return "";
        }
        reverse(c, 0, n - 1);
        reverse(c, n, c.length - 1);
        reverse(c, 0, c.length - 1);
        return new String(c);

    }

    public void reverse(char[] c, int low, int high) {
        while (low < high) {
            char temp = c[low];
            c[low] = c[high];
            c[high] = temp;
            low++;
            high--;
        }
    }

    public String LeftRotateString2(String str,int n) {
        if(null == str || str.length() <= 1){
            return str;
        }
        n %= str.length();
        char[] result = new char[str.length()];
        int j = 0;
        for(int i = n;i < str.length();i++,j++){
            result[j] = str.charAt(i);
        }
        for(int i=0;i<n;i++,j++){
            result[j] = str.charAt(i);
        }
        return new String(result);
    }

    public static void main(String[] args) {
        字符串翻转 solution = new 字符串翻转();
        System.out.println(solution.LeftRotateString("abcXYZdef", 3));
    }


}
