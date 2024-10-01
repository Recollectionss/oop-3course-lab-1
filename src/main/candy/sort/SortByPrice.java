package main.candy.sort;

import main.candy.model.Candy;

import java.util.ArrayList;
import java.util.Comparator;

public class SortByPrice implements main.candy.interfaces.CandySortStrategyInterface {
    @Override
    public void sort(ArrayList<Candy> candies) {
        candies.sort(Comparator.comparing(Candy::getPrice));
    }
}
