package creational.builder.style1;

public class BuilderA implements Builder {

    private final Production production = new Production();

    @Override
    public void buildPart1() {
        production.setPart1("BuilderA set part1");
    }

    @Override
    public void buildPart2() {
        production.setPart2("BuilderA set part2");
    }

    @Override
    public Production build() {
        System.out.println("BuilderA job done");
        return production;
    }
}
