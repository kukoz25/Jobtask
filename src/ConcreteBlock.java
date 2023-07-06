import java.util.*;

public class ConcreteBlock implements CompositeBlock { // klasa pomocnicza pokazująca, że kod działa
    private List<Block> blocks;

    public ConcreteBlock() {
        blocks = new ArrayList<>();
        blocks.add(new Sand());
        blocks.add(new Water());
        blocks.add(new Cement());
    }

    @Override
    public String getColor() {
        return "gray";
    }

    @Override
    public String getMaterial() {
        return "concrete";
    }

    @Override
    public List<Block> getBlocks() {
        return blocks;
    }

    private static class Sand implements Block {
        @Override
        public String getColor() {
            return "yellow";
        }

        @Override
        public String getMaterial() {
            return "sand";
        }
    }

    private static class Water implements Block {
        @Override
        public String getColor() {
            return "blue";
        }

        @Override
        public String getMaterial() {
            return "water";
        }
    }

    private static class Cement implements Block {
        @Override
        public String getColor() {
            return "gray";
        }

        @Override
        public String getMaterial() {
            return "cement";
        }
    }
}