import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Stream;

class MyStreamTest {

    @Test
    void givenListOfInteger_whenUseStreamOf_thenReturnMap() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(8);
        list.add(3);
        list.add(8);

        List<Integer> listExpect = new ArrayList<>(list);
        Map<Integer, Integer> mapExpect = new HashMap<>();
        mapExpect.put(1, 2);
        mapExpect.put(3, 8);

        Map<Integer, Integer> map = MyStream.of(list)
                .filter(integer -> integer != 8)
                .transform(integer -> integer * 3)
                .toMap(integer -> integer / 3, integer -> integer - 1);

        Assertions.assertArrayEquals(listExpect.toArray(), list.toArray());
        Assertions.assertEquals(mapExpect, map);

        System.out.println(map);
        System.out.println(list);
    }

    @Test
    void givenListOfPerson_whenUseStreamOf_thenNewMap() {
        Person p1 = new Person(30, "bbbb");

        List<Person> list = new ArrayList<>();
        list.add(new Person(20, "aaaa"));
        list.add(p1);
        list.add(new Person(40, "cccc"));
        list.add(new Person(50, "dddd"));

        List<Person> listExpect = new ArrayList<>(list);
        Map<String, Person> mapExpect = new HashMap<>();
        mapExpect.put(p1.getName(), p1);

        Map<String, Person> map = MyStream.of(list)
                .filter(person -> person.getAge() == 30)
                .transform(person -> (new Person(person.getAge() + 10, person.getName())))
                .toMap(Person::getName, person -> person);

        Assertions.assertArrayEquals(listExpect.toArray(), list.toArray());
        Assertions.assertNotEquals(mapExpect, map);

        System.out.println(map);
        System.out.println(list);
    }
}