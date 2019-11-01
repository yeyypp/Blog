# Lambda
[Lambda](https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html)
```
public static void printPersons(
            List<Person> list,
            Predicate<Person> predicate,
            Function<Person, String> function,
            Consumer<String> consumer
    ) {
        for (Person p : list) {
            if (predicate.test(p)) {
                String data = function.apply(p);
                consumer.accept(data);
            }
        }
    }

Person.printPersons(
                list,
                p -> p.getGender() == Person.Sex.MALE
                && p.getAge() >= 18,
                p -> p.toString(),
                data -> System.out.println(data)
        );

list
                .stream()
                .filter(
                        p -> p.getGender() == Person.Sex.MALE
                ).map(
                        p -> p.toString()
                ).forEach(
                        data -> System.out.println(data)
        );


```

- filter match predicate
- map match function
- forEach match consumer