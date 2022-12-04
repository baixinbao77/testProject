package jxd.bxb.test.study.collectionStudy;

import java.time.LocalDate;
import java.util.*;

import jxd.bxb.test.result.employees.model.po.EmployeesPo;
import org.junit.jupiter.api.Test;

/**
 * @ClassName Test
 * @Description TODO
 * @Author 白新报
 * @Date 2022/11/26 9:25
 * @Version 1.0
 **/
public class EnTest {


    public static void main(String[] args) {
        EnumerationTest enumerationTest = new EnumerationTest();
        Iterator iterator = enumerationTest.iterator();
        Map<String , String> map = new HashMap<>();
        map.put("saa", "ssasaassaas");
        System.out.println(map.put("saa", "ssasaas"));
    }


    @Test
    public void test01() {
        LinkedList<String> a = new LinkedList<String>();
        a.add("Amy");
        a.add("Cral");
        a.add("Erica");

        LinkedList<String> b = new LinkedList<String>();
        b.add("Bob");
        b.add("Doug");
        b.add("Frances");
        b.add("Gloria");

        ListIterator<String> aIter = a.listIterator();
        Iterator<String> bIter = b.iterator();

        while (bIter.hasNext()) {
            if (aIter.hasNext()) aIter.next();
            aIter.add(bIter.next());
        }
        System.out.println(a);

        bIter = b.iterator();
        while (bIter.hasNext()) {
            bIter.next();
            if (bIter.hasNext()) {
                bIter.next();
                bIter.remove();
            }
        }

        System.out.println(b);
        a.removeAll(b);
        System.out.println(a);

    }


    @Test
    public void test02() {
        HashSet<Object> objects = new HashSet<Object>(100 , 0.75F);
        objects.contains(1);
    }

    @Test
    public void TreeSetTest() {
        TreeSet<Item> items = new TreeSet<>();
        items.add(new Item("Toa", 1234));
        items.add(new Item("wit", 4562));
        items.add(new Item("moden", 9999));
        System.out.println(items);

        TreeSet<Item> items1 = new TreeSet<>(Comparator.comparing(Item::getDescriptionl).reversed());
        items1.addAll(items);
        System.out.println(items1);
        System.out.println(items1.first());
    }

    @Test
    public void PriorityQueueTest() {
        PriorityQueue<LocalDate> pq = new PriorityQueue<>();
        pq.add(LocalDate.of(1906 , 12 , 9));
        pq.add(LocalDate.of(1815 , 12 , 10));
        pq.add(LocalDate.of(1903 , 12 , 3));
        pq.add(LocalDate.of(1910 , 6 , 22));

        for (LocalDate date : pq) {
            System.out.println(date);
        }
        while (!pq.isEmpty()) {
            System.out.println(pq.remove());
        }
    }


    @Test
    public void HashMapTest() {
//        Map<String, Integer> map = new HashMap<>();
//        map.put("12", 22);
//        Integer merge = map.merge("12", 12, Integer::sum);
//        System.out.println(merge);
//        System.out.println(map.get("12"));
//        map.compute("12", (key , value) -> {
//            return 1;
//        });
//        System.out.println(map.get("12"));
        List<EmployeesPo> list = Arrays.asList(new EmployeesPo());
        List<EmployeesPo> list1 = Collections.unmodifiableList(list);
        list1.get(0).setEmployeename("saassa");
//        list1.remove(0);
//        System.out.println(list1);
        Map<String, EmployeesPo> kvSortedMap = Collections.synchronizedMap(new HashMap<String, EmployeesPo>());

        List<String> list2 = Collections.nCopies(100, "saassa");
//        System.out.println(list2);

        ArrayList<Integer> integers = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            integers.add(i);
        }
        Collections.shuffle(integers);
        List<Integer> integerList = integers.subList(0, 6);
        Collections.sort(integerList);
        System.out.println(integerList);

    }

    @Test
    public void Sieve() {
        int n = 200000;
        long start = System.currentTimeMillis();

        BitSet bitSet = new BitSet(n + 1);
        int count = 0;
        int i;
        for (i = 2; i <=n; i++) {
            bitSet.set(i);
        }
        bitSet.set(3000000);
        bitSet.get(30000000);
        i = 2;
        while (i * i <= n) {
            if (bitSet.get(i)) {
                count++;
                int k = 2 * i;
                while (k <= n) {
                    bitSet.clear();
                    k += i;
                }
            }
            i++;
        }

        while (i <= n) {
            if (bitSet.get(i)) {
                count++;
            }
            i++;
        }

        long end = System.currentTimeMillis();
        System.out.println(count);
        System.out.println(end - start);
    }



}
