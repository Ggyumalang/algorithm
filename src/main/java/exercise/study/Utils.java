/**
 * Util 클래스에는 보통 s 를 붙인다! 기억!
 * ex) Collections
 */

package exercise.study;

import org.springframework.util.StringUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Utils {

    public static void main(String[] args) {
        List<Integer> listA = new ArrayList<>(List.of(1,2,3));
        // ex1) Collection 에 대한 Util Class 인 Collections 클래스
        Collections.addAll(listA, 4,5,6,7,8,9,10);
        System.out.println("listA = " + listA);

        // ex2) StringUtils
        String a = StringUtils.capitalize("a");
        System.out.println("a = " + a);
    }
}
