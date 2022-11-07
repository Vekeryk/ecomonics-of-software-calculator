package cocomo.data.functionalpoints;

import cocomo.data.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Control;
import javafx.scene.control.ToggleGroup;

import java.util.Arrays;
import java.util.List;

public enum EnvironmentalFactor implements Property {
    DATA_COMMUNICATIONS("Обмін даними"),
    DISTRIBUTED_FUNCTIONS("Розподілені функції"),
    PERFORMANCE("Продуктивніcть"),
    HEAVILY_USED_CONFIGURATION("Інтенсивно використовувана конфігурація"),
    TRANSACTION_RATE("Інтенсивність транзакцій"),
    ONLINE_DATA_ENTRY("Діалоговий ввід даних"),
    END_USER_EFFICIENCY("Ефективність для кінцевого користувача"),
    ONLINE_UPDATE("Оперативне оновлення"),
    COMPLEX_PROCESSING("Складність обробки даних"),
    REUSABILITY("Повторне використання"),
    INSTALLATION_EASE("Легкість установлення"),
    OPERATIONAL_EASE("Простота використання"),
    MULTIPLE_SITES("Поширюваність"),
    FACILITATE_CHANGE("Легкість зміни");

    public final ToggleGroup toggleGroup = new ToggleGroup();
    public final SimpleStringProperty name;
    public final SimpleObjectProperty<Control> zero;
    public final SimpleObjectProperty<Control> one;
    public final SimpleObjectProperty<Control> two;
    public final SimpleObjectProperty<Control> three;
    public final SimpleObjectProperty<Control> four;
    public final SimpleObjectProperty<Control> five;
    public final List<SimpleObjectProperty<Control>> rating;

    EnvironmentalFactor(String name) {
        this.name = new SimpleStringProperty(name);
        this.zero = new SimpleObjectProperty<>(buttonFactory(0));
        this.one = new SimpleObjectProperty<>(buttonFactory(1));
        this.two = new SimpleObjectProperty<>(buttonFactory(2));
        this.three = new SimpleObjectProperty<>(buttonFactory(3));
        this.four = new SimpleObjectProperty<>(buttonFactory(4));
        this.five = new SimpleObjectProperty<>(buttonFactory(5));
        rating = Arrays.asList(this.zero, this.one, this.two, this.three, this.four, this.five);
    }

    @Override
    public SimpleStringProperty getName() {
        return name;
    }

    @Override
    public List<SimpleObjectProperty<Control>> getControls() {
        return rating;
    }

    @Override
    public ToggleGroup getToggleGroup() {
        return toggleGroup;
    }

    @Override
    public Property[] getValues() {
        return EnvironmentalFactor.values();
    }

    @Override
    public String[] getNames() {
        return new String[]{"0", "1", "2", "3", "4", "5"};
    }
}
