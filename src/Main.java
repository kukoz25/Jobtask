import java.util.*;


interface Structure {
    // zwraca dowolny element o podanym kolorze
    Optional<Block> findBlockByColor(String color);

    // zwraca wszystkie elementy z danego materiału
    List<Block> findBlocksByMaterial(String material);

    //zwraca liczbę wszystkich elementów tworzących strukturę
    int count();
}

interface Block {
    String getColor();

    String getMaterial();
}

interface CompositeBlock extends Block {
    List<Block> getBlocks();

}


public class Main {
    public static void main(String[] args) {
        // Tworzenie obiektów Brick i ConcreteBlock
        Brick brick = new Brick();
        ConcreteBlock concreteBlock = new ConcreteBlock();

        // Dodawanie bloków do listy
        List<Block> blockList = new ArrayList<>();
        blockList.add(brick);
        blockList.add(brick);
        blockList.add(concreteBlock);

        // Tworzenie ściany
        Wall wall = new Wall(blockList);

        // Wyszukiwanie bloków na podstawie koloru
        Optional<Block> orangeBlock = wall.findBlockByColor("orange");
        Optional<Block> yellowBlock = wall.findBlockByColor("yellow");
    // Wyszukiwanie bloków na podstawie materiału
        List<Block> bricks = wall.findBlocksByMaterial("clay");

        // Wyświetlanie wyników
        System.out.println("Orange block is made of " + orangeBlock.get().getMaterial());
        System.out.println("Yellow block is made of " + yellowBlock.get().getMaterial());
        System.out.println("Found " + bricks.size() + " bricks");
        System.out.println("Wall consists of " +wall.count()+ " elements");
    }
}