import java.util.*;

/* że interfejs CompositeBlock rozszerza interfejs Block i zawiera dodatkową metodę getBlocks().
To sugeruje, że bloki mogą być zagnieżdżone.
Oznacza to, że podczas przeszukiwania bloków musimy uwzględnić możliwość zagnieżdżenia bloków wewnątrz innych bloków.
Należy użyć rekurencji, aby poprawnie przeszukać wszystkie bloki.
*/
public class Wall implements Structure {
    private List<Block> blocks;

    public Wall(List<Block> blocks) { // konstruktor klasy, potrzebny, by kod działał
        this.blocks = blocks;
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        for (Block block : blocks) {
            Optional<Block> result = findBlockByColor(block, color);
            if (result.isPresent()) {
                return result;
            }
        }
        return Optional.empty();
    }

    /*
    Prywatna metoda wyszukująca blok o podanym kolorze.
     Przeszukuje rekurencyjnie bloki i bloki zagnieżdżone (jeśli istnieją).
     */
    private Optional<Block> findBlockByColor(Block block, String color) {
        if (block.getColor().equals(color)) {
            return Optional.of(block);
        }
        if (block instanceof CompositeBlock) {
            for (Block innerBlock : ((CompositeBlock) block).getBlocks()) {
                Optional<Block> result = findBlockByColor(innerBlock, color);
                if (result.isPresent()) {
                    return result;
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        List<Block> result = new ArrayList<>();
        for (Block block : blocks) {
            findBlocksByMaterial(block, material, result);
        }
        return result;
    }
    /*
     Prywatna metoda dodająca do listy wynikowej wszystkie bloki o podanym materiale.
     Przeszukuje rekurencyjnie bloki i bloki zagnieżdżone (jeśli istnieją).
     */
    private void findBlocksByMaterial(Block block, String material, List<Block> result) {
        if (block.getMaterial().equals(material)) {
            result.add(block);
        }
        if (block instanceof CompositeBlock) {
            for (Block innerBlock : ((CompositeBlock) block).getBlocks()) {
                findBlocksByMaterial(innerBlock, material, result);
            }
        }
    }

    @Override
    public int count() {
        int count = 0;
        for (Block block : blocks) {
            count += count(block);
        }
        return count;
    }
//Prywatna metoda zliczająca wszystkie bloki w strukturze (łącznie z blokami zagnieżdżonymi, jeśli istnieją, ale nie wliczamy ich jako osobne bloki)
private int count(Block block) {
        int count = 0;
        if (block instanceof CompositeBlock) {
            for (Block innerBlock : ((CompositeBlock) block).getBlocks()) {
                count += count(innerBlock);
            }
        } else {
            count = 1;
        }
        return count;
    }
}