package jxd.bxb.test.study.collectionStudy;

import java.util.Objects;

/**
 * @ClassName Item
 * @Description TODO
 * @Author 白新报
 * @Date 2022/11/26 15:59
 * @Version 1.0
 **/
public class Item implements Comparable<Item>{

    private String descriptionl;
    private int partNumber;

    @Override
    public String toString() {
        return "Item{" +
                "descriptionl='" + descriptionl + '\'' +
                ", partNumber=" + partNumber +
                '}';
    }

    public String getDescriptionl() {
        return descriptionl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return partNumber == item.partNumber && Objects.equals(descriptionl, item.descriptionl);
    }



    @Override
    public int hashCode() {
        return Objects.hash(descriptionl, partNumber);
    }

    public void setDescriptionl(String descriptionl) {
        this.descriptionl = descriptionl;
    }

    public int getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(int partNumber) {
        this.partNumber = partNumber;
    }

    public Item(String descriptionl, int partNumber) {
        this.descriptionl = descriptionl;
        this.partNumber = partNumber;
    }

    @Override
    public int compareTo(Item o) {
        int diff = Integer.compare(partNumber , o.partNumber);
        return diff != 0 ? diff : descriptionl.compareTo(o.descriptionl);
    }
}
