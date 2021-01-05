package builder.style1;

public class BuilderB implements Builder {

    private final Production production = new Production();

    @Override
    public void buildPart1() {
        production.setPart1("BuilderB set part1");
    }

    @Override
    public void buildPart2() {
        production.setPart2("BuilderB set part2");
    }

    @Override
    public Production build() {
        System.out.println("BuilderB job done");
        return production;
    }
}
