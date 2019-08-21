# CountWord
```
Java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuaiYe
 * @date 2019/8/1 19:55
 */
public class CountWord {
    public static void main(String[] args) {
        String file = "C:\\Users\\18610\\Desktop\\TestTxt\\in.txt";
        String text = readFile(file);
        Map<String, Integer> ans = countWord(text);
        printMap(ans);
    }

    public static String readFile(String file) {
        StringBuilder sb = new StringBuilder();
        try (
                BufferedReader reader = new BufferedReader(new FileReader(file));
                ) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static Map<String, Integer> countWord(String text) {
        text = text.replaceAll("\\pP", " ");
        String[] strings = text.trim().split(" ");
        Map<String, Integer> ans = new HashMap<>();
        for (String s : strings) {
            int count = ans.getOrDefault(s, 0);
            if (count == 0) {
                ans.put(s, 1);
            } else {
                ans.put(s, count + 1);
            }
        }
        return ans;
    }

    public static void printMap(Map<String, Integer> map) {
        map.forEach((key, value) -> {
            System.out.println("Word:" + key + " Count:" + value);
        });
    }
}
```

```
Go

package main

import (
	"golang.org/x/tour/wc"
	"strings"
)

func WordCount(s string) map[string]int {
	m := make(map[string]int)
	for _, v := range strings.Fields(s) {
		count, exist := m[v]
		if exist {
			m[v] = count + 1
		} else {
			m[v] = 1
		}
	}
		return m
		
}
```