# Java 基础

### switch语句

语句后不跟break的话，会继续执行后续case
```
List<Integer> list = new LinkedList<>();
        list.add(1);
        switch (list.get(0)) {
            case 1:
                System.out.println(1);
                break;
            case 2:
                System.out.println(2);
                break;
            default:
                System.out.println(0);
        }
```